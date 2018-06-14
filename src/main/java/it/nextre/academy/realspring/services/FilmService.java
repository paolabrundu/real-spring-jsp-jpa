package it.nextre.academy.realspring.services;

import it.nextre.academy.realspring.entities.Film;
import java.util.List;

public interface FilmService {

    List<Film> getAll();
    Film findById(long id);
    List<Film> findByRegista (String regista);
    List<Film> findByTitolo (String titolo);
    Film add(Film film) throws Exception;
    Film save(Film film) throws Exception;
    boolean delete(Film film) throws Exception;
}
