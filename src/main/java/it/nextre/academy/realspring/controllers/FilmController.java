package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.models.Film;
import it.nextre.academy.realspring.services.FilmService;
import it.nextre.academy.realspring.utils.ResponseHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //per estrarre dati in formato json
@RequestMapping("/film")
public class FilmController {

   //per cercare quello che gli specifico nella libreria di spring, se il bean non esiste lo crea
   @Autowired
   FilmService filmService;

    @Autowired
    ResponseHelper responseHelper;

    Logger logg = Logger.getLogger(FilmController.class);


    @GetMapping("/")
    public ResponseEntity getFilms() {
        logg.debug("getFilms() called");
        return new ResponseEntity(filmService.getAll(), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity getFilmById(@PathVariable("id") Long id) {
        logg.debug("getFilmById() called");
        if (id != null) {
            return new ResponseEntity(filmService.findById(id), HttpStatus.OK);

        } else {
            return new ResponseEntity(filmService.findById(id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/regista/{regista}")
    public ResponseEntity getFilmByRegista(@PathVariable("regista") String regista) {
        logg.debug("getFilmByRegista() called");
        if (!regista.equals(null)
                && regista.length() > 0) {
            return new ResponseEntity(filmService.findByRegista(regista), HttpStatus.OK);
        } else {
            return new ResponseEntity(filmService.findByRegista(regista), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("search/titolo/{titolo}")
    public ResponseEntity getFilmByTitolo(@PathVariable("titolo") String titolo) {
        logg.debug("getFilmByTitolo() called");
        if (!titolo.equals(null)
                && titolo.length() > 0) {
            return new ResponseEntity(filmService.findByTitolo(titolo), HttpStatus.OK);
        } else {
            return new ResponseEntity(filmService.findByTitolo(titolo), HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("search/anno/{anno}")
//    public List<Film> getFilmByAnno (@PathVariable("anno")int anno){
//        logg.debug("getFilmByAnno() called");
//        if (anno>=1950){
//            return filmService.
//        }
//    }

    //per indicare che quello che  inserisco va messo del corpo del json
    @PostMapping("/")
    public Film addFilm(@RequestBody Film f1) {
        logg.debug("addFilm() called with film");
        return filmService.add(f1);
    }


    @PutMapping("/{idFilm}")
    public Film updateFilm(@RequestBody Film f1, @PathVariable("idFilm") long id) {
        logg.debug("updateFilm() called with film: " + f1 + " and id " + id);
        if (f1.getId()==id){
            return filmService.save(f1);
        } else {
            return new Film();
        }

    }

    @DeleteMapping("/{idFilm}")
    public boolean deleteFilm(@RequestBody Film f1, @PathVariable ("idFilm") long id){
        logg.debug("deleteFilm() called with film: " + f1 + " and id " + id);
        if (f1.getId()==id){
            return filmService.delete(f1);
        } else {
            return false;
        }}
}//end class
