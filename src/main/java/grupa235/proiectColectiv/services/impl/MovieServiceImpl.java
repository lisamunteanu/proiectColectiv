package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.repository.MovieRepository;
import grupa235.proiectColectiv.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Integer id){
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> FilterMoviesByGenres(String genre) {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> SortMoviesByAddedDate() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> SortMoviesByRating() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> SortMoviesByReleaseYear() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> SortMoviesByName() {
        return movieRepository.findAll();
    }
}
