package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.MovieDetails;
import grupa235.proiectColectiv.frontendModel.MovieModel;
import grupa235.proiectColectiv.model.Movie;

import java.util.List;

public interface MovieService {
    List<MovieModel> findAllMovies();

    MovieModel findById(Integer id);

    List<Movie> findAllWatchLaterForUser(String username) throws Exception;

    Boolean watchLaterMovie(String username, Integer movieId) throws Exception;

    MovieDetails getDetailsForAMovie(String movieName);

    List<Movie> findAllMovieHistoryForUser(String username) throws Exception;

    Boolean movieHistory(String username, Integer movieId) throws Exception;

    Boolean setMovieRating(String username, Integer movieId, Integer rating) throws Exception;
}
