package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.MovieDetails;
import grupa235.proiectColectiv.identities.UserMovieId;
import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.UserMovies;
import grupa235.proiectColectiv.repository.MovieRepository;
import grupa235.proiectColectiv.repository.UserMovieRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final UserMovieRepository userMovieRepository;
    private final MovieRepository movieRepository;
    private final UserRepository  userRepository;

    @Autowired
    public MovieServiceImpl(UserRepository userRepository, UserMovieRepository userMovieRepository, MovieRepository movieRepository) {
        this.userMovieRepository = userMovieRepository;
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
        List<UserMovies> moviesId =  userMovieRepository.getAllWatchLaterMoviesByUser(user);
        List<Movie> movies= new ArrayList<>();
        moviesId.forEach(movieId -> {
                movies.add(movieId.getUserMovieId().getMovie());
        });
        return movies;
    }

    @Override
    public Boolean watchLaterMovie(String username, Integer movieId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
            Optional<UserMovies> watchLaterMovieExists = userMovieRepository.findById(new UserMovieId(user,movie.get()));
            if(watchLaterMovieExists.isPresent()) {
                UserMovies userMovies = watchLaterMovieExists.get();
                if(userMovies.getWatchLater()) {
                    userMovies.setWatchLater(false);
                    userMovies.setAddedDate(null);
                }
                else {
                    userMovies.setWatchLater(true);
                    userMovies.setAddedDate(LocalDateTime.now());
                }
                this.userMovieRepository.save(userMovies);
                return userMovies.getWatchLater();
            }
            else {
                UserMovies userMovies = new UserMovies(new UserMovieId(user, movie.get()), LocalDateTime.now(), true, null, false, 0);
                userMovieRepository.save(userMovies);
                return true;
            }
        }
        return false;
    }

    @Override
    public MovieDetails getDetailsForAMovie(String movieName) {
        Optional<Movie> movie = this.movieRepository.getMovieByName(movieName);
        MovieDetails movieDetails;
        if (movie.isPresent()){
            movieDetails = ConvertData.convertMovieToMovieDetails(movie.get());
            return movieDetails;
        }
        return null;
    }

    @Override
    public List<Movie> findAllMovieHistoryForUser(String username) {
        RepoUser user = userRepository.findByUsername(username);
        List<UserMovies> moviesId =  userMovieRepository.getAllHistoryMoviesByUser(user);
        List<Movie> movies= new ArrayList<>();
        moviesId.forEach(movieId -> {
            movies.add(movieId.getUserMovieId().getMovie());
        });
        return movies;
    }

    @Override
    public Boolean movieHistory(String username, Integer movieId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
            Optional<UserMovies> watchLaterMovieExists = userMovieRepository.findById(new UserMovieId(user,movie.get()));
            if(watchLaterMovieExists.isPresent()) {
                UserMovies userMovies = watchLaterMovieExists.get();
                if(userMovies.getHistory()) {
                    userMovies.setHistory(false);
                    userMovies.setWatchedDate(null);
                }
                else {
                    userMovies.setHistory(true);
                    userMovies.setWatchedDate(LocalDateTime.now());
                }
                this.userMovieRepository.save(userMovies);
                return userMovies.getHistory();
            }
            else {
                UserMovies userMovies = new UserMovies(new UserMovieId(user, movie.get()), null, false, LocalDateTime.now(), true, 0);
                userMovieRepository.save(userMovies);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean setMovieRating(String username, Integer movieId, Integer rating) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
            Optional<UserMovies> watchLaterMovieExists = userMovieRepository.findById(new UserMovieId(user,movie.get()));
            if(watchLaterMovieExists.isPresent()) {
                UserMovies userMovies = watchLaterMovieExists.get();
                userMovies.setRating(rating);
                this.userMovieRepository.save(userMovies);
                return true;
            }
            else{
                UserMovies userMovies = new UserMovies(new UserMovieId(user, movie.get()), null, false, null, false, rating);
                userMovieRepository.save(userMovies);
                return true;
            }
        }
        return false;
    }

}
