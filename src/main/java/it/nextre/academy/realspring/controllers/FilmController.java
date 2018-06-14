package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.entities.Film;
import it.nextre.academy.realspring.services.FilmService;
import it.nextre.academy.realspring.utils.ResponseHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController  //per estrarre dati in formato json
@RequestMapping("/film")
public class FilmController {

   //per cercare quello che gli specifico nella libreria di spring, se il bean non esiste lo crea
   @Autowired
   @Qualifier("mockfilm")
   FilmService filmService;

    @Autowired
    ResponseHelper responseHelper;

    Logger logg = Logger.getLogger(FilmController.class);


    @GetMapping("/")
    public ResponseEntity getFilms() {
        logg.debug("getFilms() called");
        return responseHelper.ok(filmService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity getFilmById(@PathVariable("id") Long id) {
        logg.debug("getFilmById() called");
        if (id != null) {
            return responseHelper.ok(filmService.findById(id));

        } else {
            return responseHelper.badRequest("Invalid input data");
        }
    }

    @GetMapping("/search/regista/{regista}")
    public ResponseEntity getFilmByRegista(@PathVariable("regista") String regista) {
        logg.debug("getFilmByRegista() called");
        if (!regista.equals(null)
                && regista.length() > 0) {
            return responseHelper.ok(filmService.findByRegista(regista));
        } else {
            return responseHelper.badRequest("Invalid input data");
        }
    }

    @GetMapping("search/titolo/{titolo}")
    public ResponseEntity getFilmByTitolo(@PathVariable("titolo") String titolo) {
        logg.debug("getFilmByTitolo() called");
        if (!titolo.equals(null)
                && titolo.length() > 0) {
            return responseHelper.ok(filmService.findByTitolo(titolo));
        } else {
            return responseHelper.badRequest("Invalid input data");
        }
    }

    //per indicare che quello che  inserisco va messo del corpo del json
    @PostMapping("/")
    public ResponseEntity addFilm(@RequestBody Film f1) {
        logg.debug("addFilm() called with film");
        try {
            return responseHelper.ok(filmService.add(f1));
        } catch (Exception e){
            return responseHelper.badRequest(e.getMessage());
        }
    }


    @PutMapping("/{idFilm}")
    public ResponseEntity updateFilm(@RequestBody Film f1, @PathVariable("idFilm") long id) {
        logg.debug("updateFilm() called with film: " + f1 + " and id " + id);
        if (f1.getId()==id){
            try {
                return responseHelper.ok(filmService.save(f1));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        } else {
            return responseHelper.badRequest("Invalid input data");
        }

    }

    @DeleteMapping("/{idFilm}")
    public ResponseEntity deleteFilm(@RequestBody Film f1, @PathVariable ("idFilm") long id){
        logg.debug("deleteFilm() called with film: " + f1 + " and id " + id);
        if (f1.getId()==id){
            try {
                return responseHelper.ok(filmService.delete(f1));
            } catch (Exception e) {
                return responseHelper.badRequest(e.getMessage());
            }
        } else {
            return responseHelper.badRequest("Invalid input data");
        }}
}//end class
