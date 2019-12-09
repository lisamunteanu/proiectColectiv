package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.SeasonModel;
import grupa235.proiectColectiv.model.Season;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.repository.SeasonsRepository;
import grupa235.proiectColectiv.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SeasonsService {

    private final SeasonsRepository seasonsRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public SeasonsService(SeasonsRepository seasonsRepository,SeriesRepository seriesRepository) {
        this.seasonsRepository = seasonsRepository;
        this.seriesRepository=seriesRepository;
    }

    public List<Season> getAllSeasons(){
        return this.seasonsRepository.findAll();
    }


    public Season findSeasonById(int id) throws Exception{
        return this.seasonsRepository.findById(id).get();
    }

    public List<Season> getAllSeasonsFromASerial(String serialId){
        return this.seasonsRepository.getAllSeasonsFromASerial(serialId);
    }

    @Transactional
    public Season addSeason(SeasonModel seasonModel){
        Season season = ConvertData.convertSeasonModelToSeason(seasonModel);
        Optional<Series> optionalSerial = this.seriesRepository.getSerialByName(seasonModel.getSerialName());
        if(optionalSerial.isPresent()){
            Series serial = optionalSerial.get();
            int totalSeries = serial.getNoOfSeasons();
            serial.setNoOfSeasons(totalSeries+1);
            this.seriesRepository.save(serial);
            season.setSeries(serial);
            Optional<Integer> seasonNumber = this.seasonsRepository.getLastSeasonNumber(serial.getId());
            if(seasonNumber.isPresent()){
                season.setNumber(seasonNumber.get()+1);
            }
            else{
                season.setNumber(1);
            }
            this.seasonsRepository.save(season);
        }
        return season;
    }

    @Transactional
    public Season updateSeason(Season season) throws Exception{
        Season updatedSeason = this.seasonsRepository.findById(season.getId()).get();
        if(updatedSeason!=null){
            updatedSeason.setGeneralDescription(season.getGeneralDescription());
            updatedSeason.setNumber(season.getNumber());
            updatedSeason.setName(season.getName());
            this.seasonsRepository.save(updatedSeason);
        }
        return updatedSeason;
    }

}
