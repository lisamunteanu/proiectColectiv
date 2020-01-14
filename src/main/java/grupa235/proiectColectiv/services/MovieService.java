package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.MovieDetails;
import grupa235.proiectColectiv.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();

    Optional<Movie> findById(Integer id);

    List<Movie> findAllWatchLaterForUser(String username);

    Boolean watchLaterMovie(String username, Integer movieId);

    MovieDetails getDetailsForAMovie(String movieName);

    List<Movie> findAllMovieHistoryForUser(String username);

    Boolean movieHistory(String username, Integer movieId);

    Boolean setMovieRating(String username, Integer movieId, Integer rating);
}
