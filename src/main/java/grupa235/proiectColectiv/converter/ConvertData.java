package grupa235.proiectColectiv.converter;

import grupa235.proiectColectiv.frontendModel.*;
import grupa235.proiectColectiv.model.*;

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

    public static SerialModel convertSerialToSerialModel(Series series, Double rating) {
        SerialModel serialModel = new SerialModel();
        serialModel.setId(series.getId());
        serialModel.setName(series.getName());
        serialModel.setRating(rating);
        serialModel.setNoOfEpisodes(series.getNoOfEpisodes());
        serialModel.setNoOfSeasons(series.getNoOfSeasons());
        serialModel.setReleaseYear(series.getReleaseYear());
        serialModel.setDirector(series.getDirector());
        serialModel.setGenres(series.getGenres());
        serialModel.setImage(series.getImage());
        return serialModel;
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
        movie.setReleaseYear(movieModel.getReleaseYear());
        return movie;
    }

    public static MovieModel convertMovieToMovieModel(Movie movie, Double rating) {
        MovieModel movieModel = new MovieModel();
        movieModel.setName(movie.getName());
        movieModel.setId(movie.getId());
        movieModel.setRating(rating);
        movieModel.setDirector(movie.getDirector());
        movieModel.setDuration(movie.getDuration());
        movieModel.setGenres(movie.getGenres());
        movieModel.setImage(movie.getImage());
        movieModel.setReleaseYear(movie.getReleaseYear());
        return movieModel;
    }

    public static MovieDetails convertMovieToMovieDetails(Movie movie, Double rating){
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setName(movie.getName());
        movieDetails.setId(movie.getId());
        movieDetails.setRating(rating);
        movieDetails.setDirector(movie.getDirector());
        movieDetails.setDuration(movie.getDuration());
        movieDetails.setGenres(movie.getGenres());
        movieDetails.setDuration(movie.getDuration());
        movieDetails.setReleaseYear(movie.getReleaseYear());
        return movieDetails;
    }

    public static SerialDetails convertSerialToSerialDetails(Series serialDetails, Double rating) {
        SerialDetails serial = new SerialDetails();
        serial.setId(serialDetails.getId());
        serial.setDescription(serialDetails.getDescription());
        serial.setRating(rating);
        serial.setImage(serialDetails.getImage());
        serial.setGenres(serialDetails.getGenres());
        serial.setDirector(serialDetails.getDirector());
        serial.setName(serialDetails.getName());
        serial.setReleaseYear(serialDetails.getReleaseYear());
        serial.setNoOfSeasons(serialDetails.getNoOfSeasons());
        serial.setNoOfEpisodes(serialDetails.getNoOfEpisodes());
        return serial;
    }

    public static SeasonDetails convertSeasonToSeasonDetails(Season season){
        SeasonDetails seasonDetails = new SeasonDetails();
        seasonDetails.setNumber(season.getNumber());
        seasonDetails.setGeneralDescription(season.getGeneralDescription());
        seasonDetails.setName(season.getName());
        return seasonDetails;
    }

    public static EpisodeDetails convertEpisodeToEpisodeDetails(Episode episode){
        EpisodeDetails episodeDetails = new EpisodeDetails();
        episodeDetails.setDescription(episode.getDescription());
        episodeDetails.setDuration(episode.getDuration());
        episodeDetails.setName(episode.getName());
        episodeDetails.setNumber(episode.getNumber());
        return episodeDetails;
    }

    public static Image convertUserImageToImage(UserImage userImage){
        Image image = new Image();
        image.setImage(userImage.getImage());
        return image;
    }
}
