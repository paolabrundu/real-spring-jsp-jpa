package it.nextre.academy.realspring.services;

import it.nextre.academy.realspring.models.Film;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private List<Film> videoteca= new ArrayList<>();
    {
        videoteca.add(new Film(1, "300", "Zack Snyder", 2007));
        videoteca.add(new Film(2, "Pacific Rim", "Guillermo Del Toro", 2013));
        videoteca.add(new Film(3, "Dunkirk", "Christopher Nolan", 2017));
        videoteca.add(new Film(4, "Saw", "James Wan", 2004));
        videoteca.add(new Film(5, "Il Padrino", "Francis Ford Coppola", 1972));
        videoteca.add(new Film(6, "Arancia Meccanica", "Stanley Kubrik", 1971));
        videoteca.add(new Film(7, "Shining", "Stanley Kubrik", 1980));
        videoteca.add(new Film(8, "Italiano Medio", "Marcello Macchia", 2015));
    }

    Logger logg = Logger.getLogger(FilmService.class);

    public List<Film> getAll(){
       // List<Film> tmp = new ArrayList<>();
        logg.debug("FileService -> getAll()");
        return videoteca;
    }

    public Film findById(long id) {
        logg.debug("FileService -> findById() with id");
        return videoteca.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Film> findByRegista (String regista){
        logg.debug("FileService -> findByRegista() with regista");
        return videoteca.stream()
                .filter(f->f.getRegista().toLowerCase().contains(regista.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Film> findByTitolo (String titolo){
        logg.debug("FileService -> findByTitolo() with titolo");
        return videoteca.stream()
                .filter(f->f.getTitolo().toLowerCase()
                .contains(titolo.toLowerCase()))
                .collect(Collectors.toList());
    }

//    public List<Film> findByAnno (int anno){
//        logg.debug("FileService -> findByAnno() with anno");
//        return videoteca.stream()
//                .filter(f->f.getAnno())
//                .collect(Collectors.toList());
//    }


    public Film add(Film film)throws Exception{
        logg.debug("FileService -> add() with film");
        if(film!=null && film.getId()==0
                && film.getTitolo()!=null
                && film.getTitolo().length()>0){
            long id = videoteca.stream()
                    .max((f1, f2)-> (int) (f1.getId()-f2.getId()))
                    .get()
                    .getId();

        film.setId(++id);
            logg.debug(film);
        videoteca.add(film);
        return film;
    }
        else {
            throw new Exception("Malformed film data");
        }
    }

    public Film save(Film film)throws Exception{
        logg.debug("FileService -> save() called with film");
        if(film!=null && film.getId()>0
                && film.getTitolo()!=null
                && film.getTitolo().length()>0){
            Optional<Film> v = videoteca.stream()
                    .filter(f->f.getId()==f.getId())
                    .findFirst();
            if (v.isPresent()){
                v.get().setTitolo(film.getTitolo());
                v.get().setRegista(film.getRegista());
                v.get().setAnno(film.getAnno());
                return v.get();
            } else{
                throw new Exception("Malformed film data");
            }
        }
        return new Film();

    }

    public boolean delete(Film film)throws Exception{
        logg.debug("FileService -> delete() called with film");
        if (film!=null && film.getId()>0){
            return videoteca.removeIf(f->f.getId()==film.getId());

        }
        else{
            throw new Exception("Malformed film data");
            }
            //return false;
    }
}//end class
