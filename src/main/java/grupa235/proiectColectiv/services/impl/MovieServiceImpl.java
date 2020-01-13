package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.MovieDetails;
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
    public MovieDetails getDetailsForAMovie(String movieName) {
        Optional<Movie> movie = this.movieRepository.getMovieByName(movieName);
        MovieDetails movieDetails;
        if (movie.isPresent()){
            movieDetails = ConvertData.convertMovieToMovieDetails(movie.get());
            return movieDetails;
        }
        return null;
    }
}
