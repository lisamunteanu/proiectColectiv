package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.SeasonModel;
import grupa235.proiectColectiv.model.Season;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.repository.SeasonsRepository;
import grupa235.proiectColectiv.repository.SeriesRepository;
import grupa235.proiectColectiv.services.SeasonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonsServiceImpl implements SeasonsService {

    private final SeasonsRepository seasonsRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public SeasonsServiceImpl(SeasonsRepository seasonsRepository, SeriesRepository seriesRepository) {
        this.seasonsRepository = seasonsRepository;
        this.seriesRepository = seriesRepository;
    }

    public List<Season> getAllSeasons() {
        return this.seasonsRepository.findAll();
    }


    public Season findSeasonById(int id) throws Exception {
        return this.seasonsRepository.findById(id).get();
    }

    public List<Season> getAllSeasonsFromASerial(String serialId) {
        return this.seasonsRepository.getAllSeasonsFromASerial(serialId);
    }

    public Season addSeason(SeasonModel seasonModel) throws Exception {
        Season season = ConvertData.convertSeasonModelToSeason(seasonModel);
        Series series = this.seriesRepository.findById(seasonModel.getSerialId()).get();
        if (series != null) {
            season.setSeries(series);
            this.seasonsRepository.save(season);
            return season;
        }
        return null;
    }

    public Season updateSeason(Season season) throws Exception {
        Season updatedSeason = this.seasonsRepository.findById(season.getId()).get();
        if (updatedSeason != null) {
            updatedSeason.setGeneralDescription(season.getGeneralDescription());
            updatedSeason.setNumber(season.getNumber());
            updatedSeason.setName(season.getName());
            this.seasonsRepository.save(updatedSeason);
        }
        return updatedSeason;
    }

}
