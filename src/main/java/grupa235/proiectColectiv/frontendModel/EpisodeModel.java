package grupa235.proiectColectiv.frontendModel;

public class EpisodeModel {

    private String name;
    private Integer number;
    private String description;
    private Integer duration;
    private Integer seasonId;

    public EpisodeModel(String name, Integer number, String description, Integer duration, Integer seasonId) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.duration = duration;
        this.seasonId = seasonId;
    }

    public EpisodeModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    @Override
    public String toString() {
        return "EpisodeModel{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", seasonId=" + seasonId +
                '}';
    }
}
