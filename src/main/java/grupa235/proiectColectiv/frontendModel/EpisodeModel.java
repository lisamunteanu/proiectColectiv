package grupa235.proiectColectiv.frontendModel;

public class EpisodeModel {

    private String name;
    private String description;
    private Integer duration;
    private String serialName;

    public EpisodeModel(String name, String description, Integer duration, String serialName) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.serialName = serialName;
    }

    public EpisodeModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }

    @Override
    public String toString() {
        return "EpisodeModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", serialName='" + serialName + '\'' +
                '}';
    }
}
