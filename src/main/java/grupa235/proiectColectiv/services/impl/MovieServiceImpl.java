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
}
