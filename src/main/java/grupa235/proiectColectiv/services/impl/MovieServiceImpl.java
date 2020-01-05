package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.identities.WatchLaterMovieId;
import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.WatchLaterMovies;
import grupa235.proiectColectiv.repository.MovieRepository;
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

    @Autowired
    public MovieServiceImpl(WatchLaterMoviesRepository watchLaterMoviesRepository, MovieRepository movieRepository) {
        this.watchLaterMoviesRepository = watchLaterMoviesRepository;
        this.movieRepository = movieRepository;
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
    public List<Movie> findAllWatchLaterForUser(RepoUser user) {
        List<Movie> moviesId =  watchLaterMoviesRepository.getAllMoviesByUser(user);
        List<Movie> movies= new ArrayList<>();
//        moviesId.forEach(movieId -> {
//                Optional<Movie> movie = movieRepository.findById(movieId);
//            movie.ifPresent(movies::add);
//        });
        return movies;
    }

    @Override
    public WatchLaterMovies addWatchLaterMovie(RepoUser user, Integer movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()){
            WatchLaterMovies watchLaterMovies = new WatchLaterMovies(new WatchLaterMovieId(user, movie.get()), LocalDateTime.now());
            watchLaterMoviesRepository.save(watchLaterMovies);
            return watchLaterMovies;
        }
        return null;
    }

    @Override
    public void deleteWatchLaterMovie(RepoUser user, Integer movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
        WatchLaterMovies watchLaterMovies = watchLaterMoviesRepository.findById(new WatchLaterMovieId(user,movie.get())).get();
        watchLaterMoviesRepository.delete(watchLaterMovies);
        }
    }
}
