package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.EpisodeModel;
import grupa235.proiectColectiv.model.Episode;

import java.util.List;

public interface EpisodesService {
    List<Episode> getAllEpisodes();

    List<Episode> getAllEpisodesFromASerial(String idSerial);

    Episode findEpisodeById(int id) throws Exception;

    Episode addEpisode(EpisodeModel episodeModel) throws Exception;

    Episode updateEpisode(Episode episode) throws Exception;
}
