package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getAllSeries(){
        return this.seriesRepository.findAll();
    }

    public List<String> getAllNamesSeries(){
        Optional<List<String>> optionalNames = this.seriesRepository.getSerialNames();
        return optionalNames.orElse(null);
    }

    @Transactional
    public Series addOrUpdate(SerialModel serialModel){
        Series serial = ConvertData.convertSerialModelToSerial(serialModel);
        Optional<Series> optionalSerial = this.seriesRepository.getSerialByName(serialModel.getName());
        if(optionalSerial.isPresent()){
            Series dbSerial = optionalSerial.get();
            dbSerial.setGenres(serial.getGenres());
            dbSerial.setDirector(serial.getDirector());
            dbSerial.setName(serial.getName());
            this.seriesRepository.save(dbSerial);
        } else {
            serial.setNoOfEpisodes(0);
            serial.setNoOfSeasons(0);
            this.seriesRepository.save(serial);
        }
        return serial;
    }

    public Series findSerialById(int id)throws Exception{
        return this.seriesRepository.findById(id).get();
    }

    @Transactional
    public Series updateSerial(Series serial) throws Exception{
        Series isPresentSerial = this.seriesRepository.findById(serial.getId()).get();
        if(isPresentSerial!=null){
            isPresentSerial.setGenres(serial.getGenres());
            isPresentSerial.setDirector(serial.getDirector());
            isPresentSerial.setName(serial.getName());
            isPresentSerial.setNoOfSeasons(serial.getNoOfSeasons());
            isPresentSerial.setNoOfEpisodes(serial.getNoOfEpisodes());
            this.seriesRepository.save(isPresentSerial);
        }
        return isPresentSerial;
    }

}
