package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.MovieModel;
import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    //
    @GetMapping(value = "movies/{genre}")
    public ResponseEntity<List<MovieModel>> FilterMoviesByGenres(@PathVariable String genre) {
        List<Movie> allMovies = movieService.FilterMoviesByGenres(genre);
        List<MovieModel> convertedMovies = new ArrayList<>();
        for (Movie m : allMovies) {
            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
        }
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> SortMoviesByAddedDate() {
        List<Movie> allMovies = movieService.SortMoviesByAddedDate();
        List<MovieModel> convertedMovies = new ArrayList<>();
        for (Movie m : allMovies) {
            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
        }
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> SortMoviesByRating() {
        List<Movie> allMovies = movieService.SortMoviesByRating();
        List<MovieModel> convertedMovies = new ArrayList<>();
        for (Movie m : allMovies) {
            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
        }
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> SortMoviesByReleaseYear() {
        List<Movie> allMovies = movieService.SortMoviesByReleaseYear();
        List<MovieModel> convertedMovies = new ArrayList<>();
        for (Movie m : allMovies) {
            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
        }
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }


    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> SortMoviesByName() {
        List<Movie> allMovies = movieService.SortMoviesByName();
        List<MovieModel> convertedMovies = new ArrayList<>();
        for (Movie m : allMovies) {
            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
        }
        return new ResponseEntity<>(convertedMovies, HttpStatus.OK);
    }

}
