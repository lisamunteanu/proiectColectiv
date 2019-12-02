package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class SeasonModel implements Serializable {


    private String name;
    private String generalDescription;
    private Integer number;
    private Integer serialId;

    public SeasonModel(String name, String generalDescription, Integer number, Integer serialId) {
        this.name = name;
        this.generalDescription = generalDescription;
        this.number = number;
        this.serialId = serialId;
    }

    public SeasonModel(){}

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

    public Integer getSerialId() {
        return serialId;
    }

    public void setSerialId(Integer serialId) {
        this.serialId = serialId;
    }

    @Override
    public String toString() {
        return "SeasonModel{" +
                "name='" + name + '\'' +
                ", generalDescription='" + generalDescription + '\'' +
                ", number=" + number +
                ", serialId=" + serialId +
                '}';
    }
}
