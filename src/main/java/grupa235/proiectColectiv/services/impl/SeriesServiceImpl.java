package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.SerialDetails;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.identities.WatchLaterSeriesId;
import grupa235.proiectColectiv.model.*;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.repository.SeasonsRepository;
import grupa235.proiectColectiv.repository.SeriesRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.repository.WatchLaterSeriesRepository;
import grupa235.proiectColectiv.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final WatchLaterSeriesRepository watchLaterSeriesRepository;

    private final SeriesRepository seriesRepository;
    private final SeasonsRepository seasonsRepository;

    private final UserRepository userRepository;

    @Autowired
    public SeriesServiceImpl(UserRepository userRepository, WatchLaterSeriesRepository watchLaterSeriesRepository, SeriesRepository seriesRepository,SeasonsRepository seasonsRepository) {
        this.watchLaterSeriesRepository = watchLaterSeriesRepository;
        this.seriesRepository = seriesRepository;
        this.userRepository =userRepository;
        this.seasonsRepository=seasonsRepository;
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
    public List<Series> findAllWatchLaterSeriesForUser(String username) {
        RepoUser user = userRepository.findByUsername(username);
        List<WatchLaterSeries> seriesIds =  watchLaterSeriesRepository.getAllSeriesByUser(user);
        List<Series> series= new ArrayList<>();
        seriesIds.forEach(seriesId -> {
            series.add(seriesId.getWatchLaterSeriesId().getSeries());
        });
        return series;
    }

    @Override
    public void addWatchLaterSeries(String username, Integer seriesId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Series> series = seriesRepository.findById(seriesId);
        if(series.isPresent()){
            WatchLaterSeries watchLaterSeries = new WatchLaterSeries(new WatchLaterSeriesId(user, series.get()), LocalDateTime.now());
            watchLaterSeriesRepository.save(watchLaterSeries);
        }

    }

    @Override
    public void deleteWatchLaterSeries(String username, Integer seriesId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Series> series = seriesRepository.findById(seriesId);
        if(series.isPresent()){
            WatchLaterSeries watchLaterSeries = new WatchLaterSeries(new WatchLaterSeriesId(user, series.get()), LocalDateTime.now());
            watchLaterSeriesRepository.delete(watchLaterSeries);
        }
    }

    @Override
    public Boolean watchLaterSeries(String username, Integer seriesId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Series> series = seriesRepository.findById(seriesId);
        if (series.isPresent()){
            Optional<WatchLaterSeries> watchLaterSeriesOptional = watchLaterSeriesRepository.findById(new WatchLaterSeriesId(user,series.get()));
            if(watchLaterSeriesOptional.isPresent()) {
                watchLaterSeriesRepository.delete(watchLaterSeriesOptional.get());
                return false;
            }
            else {
                WatchLaterSeries watchLaterSeries = new WatchLaterSeries(new WatchLaterSeriesId(user, series.get()), LocalDateTime.now());
                watchLaterSeriesRepository.save(watchLaterSeries);
                return true;
            }
        }
        return false;
    }
    @Override
    public SerialDetails getDetailsForASerial(String serialName) {
        SerialDetails serialDetails;
        Optional<Series> serial = this.seriesRepository.getSerialByName(serialName);
        if (serial.isPresent()){
            serialDetails = ConvertData.convertSerialToSerialDetails(serial.get());
            return serialDetails;
        }
        return null;
    }

}
