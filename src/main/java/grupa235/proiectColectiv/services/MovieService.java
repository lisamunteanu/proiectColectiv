package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.MovieDetails;
import grupa235.proiectColectiv.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();
    Optional<Movie> findById(Integer id);
    MovieDetails getDetailsForAMovie(String movieName);
    List<Movie> FilterMoviesByGenres(String genre);

    List<Movie> SortMoviesByAddedDate();

    List<Movie> SortMoviesByRating();

    List<Movie> SortMoviesByReleaseYear();

    List<Movie> SortMoviesByName();
}
