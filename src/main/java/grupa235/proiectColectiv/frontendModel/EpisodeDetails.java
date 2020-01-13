package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class EpisodeDetails implements Serializable {

    private String name;
    private Integer number;
    private String description;
    private Integer duration;

    public EpisodeDetails(String name, Integer number, String description, Integer duration) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.duration = duration;
    }

    public EpisodeDetails(){}

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

    @Override
    public String toString() {
        return "EpisodeDetails{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}';
    }
}
