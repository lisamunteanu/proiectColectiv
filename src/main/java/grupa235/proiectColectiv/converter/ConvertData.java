package grupa235.proiectColectiv.converter;

import grupa235.proiectColectiv.frontendModel.EpisodeModel;
import grupa235.proiectColectiv.frontendModel.MovieModel;
import grupa235.proiectColectiv.frontendModel.SeasonModel;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Episode;
import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.model.Season;
import grupa235.proiectColectiv.model.Series;

import java.time.LocalDate;

public class ConvertData {

    public static Series convertSerialModelToSerial(SerialModel serialModel) {
        Series databaseSerial = new Series();
        databaseSerial.setName(serialModel.getName());
        databaseSerial.setNoOfEpisodes(serialModel.getNoOfEpisodes());
        databaseSerial.setNoOfSeasons(serialModel.getNoOfSeasons());
        databaseSerial.setReleaseYear(String.valueOf(LocalDate.now().getYear()));
        databaseSerial.setDirector(serialModel.getDirector());
        databaseSerial.setGenres(serialModel.getGenres());
        databaseSerial.setImage(serialModel.getImage());
        return databaseSerial;
    }

    public static Season convertSeasonModelToSeason(SeasonModel seasonModel) {
        Season season = new Season();
        season.setGeneralDescription(seasonModel.getGeneralDescription());
        season.setName(seasonModel.getName());
        season.setNumber(seasonModel.getNumber());
        return season;
    }

    public static Episode convertEpisodeModelToEpisode(EpisodeModel episodeModel) {
        Episode episode = new Episode();
        episode.setDescription(episodeModel.getDescription());
        episode.setDuration(episodeModel.getDuration());
        episode.setName(episodeModel.getName());
        episode.setNumber(episodeModel.getNumber());
        return episode;
    }

    public static Movie convertMovieModelToMovie(MovieModel movieModel) {
        Movie movie = new Movie();
        movie.setName(movieModel.getName());
        movie.setDirector(movieModel.getDirector());
        movie.setDuration(movieModel.getDuration());
        movie.setGenres(movieModel.getGenres());
        movie.setImage(movieModel.getImage());
        movie.setRating(movieModel.getRating());
        movie.setReleaseYear(movieModel.getReleaseYear());
        return movie;
    }

    public static MovieModel convertMovieToMovieModel(Movie movie) {
        MovieModel movieModel = new MovieModel();
        movieModel.setName(movie.getName());
        movieModel.setDirector(movie.getDirector());
        movieModel.setDuration(movie.getDuration());
        movieModel.setGenres(movie.getGenres());
        movieModel.setImage(movie.getImage());
        movieModel.setRating(movie.getRating());
        movieModel.setReleaseYear(movie.getReleaseYear());
        return movieModel;
    }

    public static SerialModel convertSeriesToSeriesModel(Series serial) {
        SerialModel serialModel = new SerialModel();
        serialModel.setName(serial.getName());
        serialModel.setImage(serial.getImage());
        serialModel.setNoOfEpisodes(serial.getNoOfEpisodes());
        serialModel.setNoOfSeasons(serial.getNoOfSeasons());
        serialModel.setGenres(serial.getGenres());
        serialModel.setDirector(serial.getDirector());
        return serialModel;
    }

}
