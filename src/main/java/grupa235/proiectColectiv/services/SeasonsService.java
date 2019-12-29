package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.SeasonModel;
import grupa235.proiectColectiv.model.Season;

import java.util.List;

public interface SeasonsService{
    List<Season> getAllSeasons();

    Season findSeasonById(int id) throws Exception;

    List<Season> getAllSeasonsFromASerial(String serialId);

    Season addSeason(SeasonModel seasonModel) throws Exception;

    Season updateSeason(Season season) throws Exception;
}
