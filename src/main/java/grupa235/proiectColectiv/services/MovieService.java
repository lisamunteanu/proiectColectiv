package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.WatchLaterMovies;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();
    Optional<Movie> findById(Integer id);
    List<Movie> findAllWatchLaterForUser(RepoUser user);
    WatchLaterMovies addWatchLaterMovie(RepoUser user, Integer movieId);
    void deleteWatchLaterMovie(RepoUser user, Integer movieId);
}
