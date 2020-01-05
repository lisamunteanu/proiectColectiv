package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.repository.SeriesRepository;
import grupa235.proiectColectiv.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getAllSeries() {
        return this.seriesRepository.findAll();
    }

    public Series addSerial(SerialModel serialModel) {
        Series serial = ConvertData.convertSerialModelToSerial(serialModel);
        this.seriesRepository.save(serial);
        return serial;
    }

    public Series findSerialById(int id) throws Exception {
        return this.seriesRepository.findById(id).get();
    }

    public Series updateSerial(Series serial) throws Exception {
        Series isPresentSerial = this.seriesRepository.findById(serial.getId()).get();
        if (isPresentSerial != null) {
            isPresentSerial.setGenres(serial.getGenres());
            isPresentSerial.setDirector(serial.getDirector());
            isPresentSerial.setName(serial.getName());
            isPresentSerial.setNoOfSeasons(serial.getNoOfSeasons());
            isPresentSerial.setNoOfEpisodes(serial.getNoOfEpisodes());
            this.seriesRepository.save(isPresentSerial);
        }
        return isPresentSerial;
    }

    @Override
    public List<Series> SortSeriesByRating() {
        return this.seriesRepository.findAll();
    }

    @Override
    public List<Series> SortSeriesByStartYear() { return this.seriesRepository.findAll(); }

}
