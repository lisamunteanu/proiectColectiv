package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.repository.MovieRepository;
import grupa235.proiectColectiv.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Integer id){
        return movieRepository.findById(id);
    }


    @Override
    public List<Movie> FilterMoviesByGenres(@PathVariable String genre) {
        List<Movie> allMovies = movieRepository.findAll();
        for (Movie m : allMovies) {
            if(m.getGenres().contains(genre)) {
                allMovies.add(m);
            }
        }
        return allMovies;
    }

    public List<Movie> SortMoviesByAddedDate() {
        List<Movie> allMovies = movieRepository.findAll();
        Comparator<Movie> compareByAddedDate = Comparator.comparing(Movie :: getAddedDate);
        Collections.sort(allMovies, compareByAddedDate);
        //Collections.sort(allMovies, (p1, p2) -> Long.valueOf(p1.getAddedDate().getTime()).compareTo(p2.getAddedDate().getTime()));
        return allMovies;
    }

    @GetMapping(value = "/movies")
    public List<Movie> SortMoviesByRating() {
        List<Movie> allMovies = movieRepository.findAll();
        Comparator<Movie> compareByRating = Comparator.comparing(Movie::getRating);
        Collections.sort(allMovies, compareByRating);
        return  allMovies;
    }

    @GetMapping(value = "/movies")
    public List<Movie> SortMoviesByReleaseYear() {
        List<Movie> allMovies = movieRepository.findAll();
        Comparator<Movie> compareByReleaseYear = Comparator.comparing(Movie::getReleaseYear);
        Collections.sort(allMovies, compareByReleaseYear);
        return  allMovies;
    }
    @GetMapping(value = "/movies")
    public List<Movie> SortMoviesByName() {
        List<Movie> allMovies = movieRepository.findAll();
        Comparator<Movie> compareByName = Comparator.comparing(Movie::getName);
        Collections.sort(allMovies, compareByName);
        return  allMovies;
    }

}
