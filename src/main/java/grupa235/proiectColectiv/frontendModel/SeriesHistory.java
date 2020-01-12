package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class SeriesHistory implements Serializable {
    private Integer seriesId;
    private String name;
    private String image;
    private Integer seasonNumber;
    private Integer episodeNumber;
    private String episodeTitle;

    public SeriesHistory() {
    }

    public SeriesHistory(Integer seriesId, String name, String image, Integer seasonNumber, Integer episodeNumber, String episodeTitle) {
        this.seriesId = seriesId;
        this.name = name;
        this.image = image;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.episodeTitle = episodeTitle;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }
}
