package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.identities.WatchLaterMovieId;
import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.WatchLaterMovies;
import grupa235.proiectColectiv.repository.MovieRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.repository.WatchLaterMoviesRepository;
import grupa235.proiectColectiv.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final WatchLaterMoviesRepository watchLaterMoviesRepository;
    private final MovieRepository movieRepository;
    private final UserRepository  userRepository;

    @Autowired
    public MovieServiceImpl(UserRepository  userRepository, WatchLaterMoviesRepository watchLaterMoviesRepository, MovieRepository movieRepository) {
        this.watchLaterMoviesRepository = watchLaterMoviesRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Integer id){
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAllWatchLaterForUser(String username) {
        RepoUser user = userRepository.findByUsername(username);
        List<WatchLaterMovies> moviesId =  watchLaterMoviesRepository.getAllMoviesByUser(user);
        List<Movie> movies= new ArrayList<>();
        moviesId.forEach(movieId -> {
                movies.add(movieId.getWatchLaterMovieId().getMovie());
        });
        return movies;
    }

    @Override
    public void addWatchLaterMovie(String username, Integer movieId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()){
            WatchLaterMovies watchLaterMovies = new WatchLaterMovies(new WatchLaterMovieId(user, movie.get()), LocalDateTime.now());
            watchLaterMoviesRepository.save(watchLaterMovies);
        }
    }

    @Override
    public void deleteWatchLaterMovie(String username, Integer movieId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
        WatchLaterMovies watchLaterMovies = watchLaterMoviesRepository.findById(new WatchLaterMovieId(user,movie.get())).get();
        watchLaterMoviesRepository.delete(watchLaterMovies);
        }
    }

    @Override
    public Boolean watchLaterMovie(String username, Integer movieId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
            Optional<WatchLaterMovies> watchLaterMovieExists = watchLaterMoviesRepository.findById(new WatchLaterMovieId(user,movie.get()));
            if(watchLaterMovieExists.isPresent()) {
                watchLaterMoviesRepository.delete(watchLaterMovieExists.get());
                return false;
            }
            else {
                WatchLaterMovies watchLaterMovies = new WatchLaterMovies(new WatchLaterMovieId(user, movie.get()), LocalDateTime.now());
                watchLaterMoviesRepository.save(watchLaterMovies);
                return true;
            }
        }
        return false;
    }
}
