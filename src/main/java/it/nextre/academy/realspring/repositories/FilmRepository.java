package it.nextre.academy.realspring.repositories;

import it.nextre.academy.realspring.entities.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
    Film findByTitolo(String titolo); //è già una query, è implementata nella crud repository di spring
    Film findByTitoloOrOrderByTitoloAsc(String titolo);
}//end class
