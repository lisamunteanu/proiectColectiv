package grupa235.proiectColectiv.converter;

import grupa235.proiectColectiv.frontendModel.EpisodeModel;
import grupa235.proiectColectiv.frontendModel.SeasonModel;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Episode;
import grupa235.proiectColectiv.model.Season;
import grupa235.proiectColectiv.model.Series;

import java.time.LocalDate;

public class ConvertData {

    public static Series convertSerialModelToSerial(SerialModel serialModel){
        Series databaseSerial = new Series();
        databaseSerial.setName(serialModel.getName());
        databaseSerial.setNoOfEpisodes(serialModel.getNoOfEpisodes());
        databaseSerial.setNoOfSeasons(serialModel.getNoOfSeasons());
        databaseSerial.setReleaseDate(LocalDate.now());
        databaseSerial.setDirector(serialModel.getDirector());
        databaseSerial.setGenres(serialModel.getGenres());
        databaseSerial.setImage(serialModel.getImage());
        return databaseSerial;
    }

    public static Season convertSeasonModelToSeason(SeasonModel seasonModel){
        Season season = new Season();
        season.setGeneralDescription(seasonModel.getGeneralDescription());
        season.setName(seasonModel.getName());
        return season;
    }

    public static Episode convertEpisodeModelToEpisode(EpisodeModel episodeModel){
        Episode episode=new Episode();
        episode.setDescription(episodeModel.getDescription());
        episode.setDuration(episodeModel.getDuration());
        episode.setName(episodeModel.getName());
        return episode;
    }

}
