package grupa235.proiectColectiv.converter;

import grupa235.proiectColectiv.frontendModel.*;
import grupa235.proiectColectiv.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        movieModel.setAddedDate(movie.getAddedDate());
        return movieModel;
    }

    public static MovieDetails convertMovieToMovieDetails(Movie movie){
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setName(movie.getName());
        movieDetails.setDirector(movie.getDirector());
        movieDetails.setDuration(movie.getDuration());
        movieDetails.setGenres(movie.getGenres());
        movieDetails.setRating(movie.getRating());
        movieDetails.setReleaseYear(movie.getReleaseYear());
        return movieDetails;
    }

    public static SerialDetails convertSerialToSerialDetails(Series serialDetails) {
        SerialDetails serial = new SerialDetails();
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
    public static SerialModel convertSeriesToSeriesModel(Series serial) {
        SerialModel serialModel = new SerialModel();
        serialModel.setName(serial.getName());
        serialModel.setImage(serial.getImage());
        serialModel.setNoOfEpisodes(serial.getNoOfEpisodes());
        serialModel.setNoOfSeasons(serial.getNoOfSeasons());
        serialModel.setGenres(serial.getGenres());
        serialModel.setDirector(serial.getDirector());
        serialModel.setrating(serial.getRating());
        serialModel.setEndYear(serial.getEndYear());
        serialModel.setStartYear(serial.getStartYear());

        return serialModel;
    }

    public  static List<MovieModel> convertMovieList(List<Movie> allMovies) {
        List<MovieModel> convertedMovies = new ArrayList<>();
        for (Movie m : allMovies) {
            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
        }
        return convertedMovies;
    }

    public static EpisodeModel convertEpisodeToEpisodeModel(Episode episode) {
        EpisodeModel episodeModel = new EpisodeModel();
        episodeModel.setAddedDate(episode.getAddedDate());
        episodeModel.setDescription(episode.getDescription());
        episodeModel.setDuration(episode.getDuration());
        episodeModel.setNumber(episode.getNumber());
        return episodeModel;
    }

    public static List<EpisodeModel> convertEpisodeList(List<Episode> allEpisodes) {
        List<EpisodeModel> convertedEpisodes = new ArrayList<>();
        for (Episode e : allEpisodes) {
            convertedEpisodes.add(ConvertData.convertEpisodeToEpisodeModel(e));
        }
        return convertedEpisodes;
    }

    public static List<SerialModel> convertSerialList(List<Series> allSeries) {
        List<SerialModel> convertedSeries = new ArrayList<>();
        for (Series s : allSeries) {
            convertedSeries.add(ConvertData.convertSeriesToSeriesModel(s));
        }
        return convertedSeries;
    }

}
