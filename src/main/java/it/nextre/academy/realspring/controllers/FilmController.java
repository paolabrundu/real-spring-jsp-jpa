package it.nextre.academy.realspring.controllers;

import it.nextre.academy.realspring.models.Film;
import it.nextre.academy.realspring.services.FilmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //per estrarre dati in formato json
@RequestMapping("/film")
public class FilmController {

    @Autowired //per cercare quello che gli specifico nella libreria di spring, se il bean non esiste lo crea
    FilmService filmService;

    Logger logg = Logger.getLogger(FilmController.class);

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable("id") Long id){
        logg.debug("getFilmById() called");
        if (id!=null){
            return filmService.findById(id);

        } else {
            return null;
        }
    }

    @GetMapping("/search/regista/{regista}")
    public List<Film> getFilmByRegista(@PathVariable("regista")String regista){
        logg.debug("getFilmByRegista() called");
        if (!regista.equals(null)
                && regista.length()>0){
            return filmService.findByRegista(regista);
        } else {
            return null;
        }
    }
}//end class
