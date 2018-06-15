package it.nextre.academy.realspring.repositories;

import it.nextre.academy.realspring.entities.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
    Film findByTitoloLike(String titolo); //è già una query, è implementata nella crud repository di spring
    Film findByTitoloLikeOrderByTitoloAsc(String titolo);
}//end class
