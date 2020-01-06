package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class SeasonDetails implements Serializable {

    private String name;
    private String generalDescription;
    private Integer number;
    private Integer numberOfEpisodes;


    public SeasonDetails(String name, String generalDescription, Integer number, Integer numberOfEpisodes) {
        this.name = name;
        this.generalDescription = generalDescription;
        this.number = number;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public SeasonDetails(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneralDescription() {
        return generalDescription;
    }

    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public String toString() {
        return "SeasonDetails{" +
                "name='" + name + '\'' +
                ", generalDescription='" + generalDescription + '\'' +
                ", number=" + number +
                ", numberOfEpisodes=" + numberOfEpisodes +
                '}';
    }
}
