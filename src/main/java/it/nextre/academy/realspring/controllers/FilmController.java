package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.models.Film;
import it.nextre.academy.realspring.services.FilmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //per estrarre dati in formato json
@RequestMapping("/film")
public class FilmController {

    @Autowired //per cercare quello che gli specifico nella libreria di spring, se il bean non esiste lo crea
            FilmService filmService;

    Logger logg = Logger.getLogger(FilmController.class);


    @GetMapping("/")
    public List<Film> getFilms() {
        logg.debug("getFilms() called");
        return filmService.getAll();
    }


    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable("id") Long id) {
        logg.debug("getFilmById() called");
        if (id != null) {
            return filmService.findById(id);

        } else {
            return null;
        }
    }

    @GetMapping("/search/regista/{regista}")
    public List<Film> getFilmByRegista(@PathVariable("regista") String regista) {
        logg.debug("getFilmByRegista() called");
        if (!regista.equals(null)
                && regista.length() > 0) {
            return filmService.findByRegista(regista);
        } else {
            return null;
        }
    }

    @GetMapping("search/titolo/{titolo}")
    public List<Film> getFilmByTitolo(@PathVariable("titolo") String titolo) {
        logg.debug("getFilmByTitolo() called");
        if (!titolo.equals(null)
                && titolo.length() > 0) {
            return filmService.findByTitolo(titolo);
        } else {
            return null;
        }
    }

//    @GetMapping("search/anno/{anno}")
//    public List<Film> getFilmByAnno (@PathVariable("anno")int anno){
//        logg.debug("getFilmByAnno() called");
//        if (anno>=1950){
//            return filmService.
//        }
//    }

    @PostMapping("/")
    public Film addFilm(@RequestBody Film f1) {  //per indicare che quello che  inserisco va messo del corpo del json
        logg.debug("addFilm() called with film");
        return filmService.add(f1);
    }

    @PostMapping("/")
    public List<Film> addFilm(@RequestBody List<Film> f1) {
        logg.debug("addFilm() called with film: " + f1);
        return f1;
    }
}//end class
