package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.MovieDetails;
import grupa235.proiectColectiv.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();

    Optional<Movie> findById(Integer id);

    List<Movie> findAllWatchLaterForUser(String username);

    void addWatchLaterMovie(String username, Integer movieId);

    void deleteWatchLaterMovie(String username, Integer movieId);

    Boolean watchLaterMovie(String username, Integer movieId);

    MovieDetails getDetailsForAMovie(String movieName);
}
