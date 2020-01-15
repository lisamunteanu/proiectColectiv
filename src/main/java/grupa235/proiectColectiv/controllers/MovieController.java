package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.Message;
import grupa235.proiectColectiv.frontendModel.MovieDetails;
import grupa235.proiectColectiv.frontendModel.MovieModel;
import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> findAllMovies() {
        List<Movie> allMovies = movieService.findAllMovies();
        List<MovieModel> convertedMovies = new ArrayList<>();
        for (Movie m : allMovies) {
            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
        }
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "movies/{id}")
    public ResponseEntity<Map<String, MovieModel>> findMovieById(@PathVariable String id) {
        Optional<Movie> movie = movieService.findById(Integer.parseInt(id));
        Map<String, MovieModel> response = new HashMap<>();
        if (movie.isPresent()) {
            response.put("Movie found", ConvertData.convertMovieToMovieModel(movie.get()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Movie not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "movies/details/{movieName}")
    public ResponseEntity<?> getDetailsForAMovie(@PathVariable String movieName){
        MovieDetails movieDetails = this.movieService.getDetailsForAMovie(movieName);
        if (movieDetails!=null){
            return new ResponseEntity<>(movieDetails,HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("There is not a movie with this name!"),HttpStatus.BAD_REQUEST);
    }
    @GetMapping(value = "movies/FilterMoviesByGenres/{genre}")
    public ResponseEntity<List<MovieModel>> FilterMoviesByGenres(@PathVariable String genre) {
        List<Movie> allMovies = movieService.FilterMoviesByGenres(genre);
        List<MovieModel> convertedMovies = ConvertData.convertMovieList(allMovies);
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "/movies/sortmoviesbyaddeddate")
    public ResponseEntity<List<MovieModel>> SortMoviesByAddedDate() {
        List<Movie> allMovies = movieService.SortMoviesByAddedDate();
        List<MovieModel> convertedMovies = ConvertData.convertMovieList(allMovies);
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "/movies/sortmoviesbyrating")
    public ResponseEntity<List<MovieModel>> SortMoviesByRating() {
        List<Movie> allMovies = movieService.SortMoviesByRating();
        List<MovieModel> convertedMovies = ConvertData.convertMovieList(allMovies);
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "/movies/sortmoviesbyreleaseyear")
    public ResponseEntity<List<MovieModel>> SortMoviesByReleaseYear() {
        List<Movie> allMovies = movieService.SortMoviesByReleaseYear();
        List<MovieModel> convertedMovies = ConvertData.convertMovieList(allMovies);
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "/movies/sortmoviesbyname")
    public ResponseEntity<List<MovieModel>> SortMoviesByName() {
        List<Movie> allMovies = movieService.SortMoviesByName();
        List<MovieModel> convertedMovies = ConvertData.convertMovieList(allMovies);
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }
}
