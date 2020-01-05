package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();
    Optional<Movie> findById(Integer id);

    List<Movie> FilterMoviesByGenres(String genre);

    List<Movie> SortMoviesByAddedDate();

    List<Movie> SortMoviesByRating();

    List<Movie> SortMoviesByReleaseYear();

    List<Movie> SortMoviesByName();


}
