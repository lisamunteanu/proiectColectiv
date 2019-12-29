package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.EpisodeModel;
import grupa235.proiectColectiv.model.Episode;
import grupa235.proiectColectiv.model.Season;
import grupa235.proiectColectiv.repository.EpisodesRepository;
import grupa235.proiectColectiv.repository.SeasonsRepository;
import grupa235.proiectColectiv.services.EpisodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodesServiceImpl implements EpisodesService {

    private final EpisodesRepository episodesRepository;
    private final SeasonsRepository seasonsRepository;

    @Autowired
    public EpisodesServiceImpl(EpisodesRepository episodesRepository, SeasonsRepository seasonsRepository) {
        this.episodesRepository = episodesRepository;
        this.seasonsRepository=seasonsRepository;
    }

    public List<Episode> getAllEpisodes(){
        return this.episodesRepository.findAll();
    }

    public List<Episode> getAllEpisodesFromASerial(String idSerial){
        return this.episodesRepository.getAllEpisodeFromASeason(idSerial);
    }

    public Episode findEpisodeById(int id) throws Exception {
        return this.episodesRepository.findById(id).get();
    }

    public Episode addEpisode(EpisodeModel episodeModel) throws Exception{
        Episode episode = ConvertData.convertEpisodeModelToEpisode(episodeModel);
        Season season = this.seasonsRepository.findById(episodeModel.getSeasonId()).get();
        if(season!=null){
            episode.setSeason(season);
            this.episodesRepository.save(episode);
            return episode;
        }
        return null;
    }

    public Episode updateEpisode(Episode episode) throws Exception{
        Episode updatedEpisode = this.episodesRepository.findById(episode.getId()).get();
        if (updatedEpisode!=null){
            updatedEpisode.setNumber(episode.getNumber());
            updatedEpisode.setDuration(episode.getDuration());
            updatedEpisode.setDescription(episode.getDescription());
            updatedEpisode.setName(episode.getName());
            this.episodesRepository.save(updatedEpisode);
        }
        return updatedEpisode;
    }

}
