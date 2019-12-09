package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class SeasonModel implements Serializable {


    private String name;
    private String generalDescription;
    private String serialName;

    public SeasonModel(String name, String generalDescription, String serialName) {
        this.name = name;
        this.generalDescription = generalDescription;
        this.serialName = serialName;
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


    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String searialName) {
        this.serialName = searialName;
    }

    @Override
    public String toString() {
        return "SeasonModel{" +
                "name='" + name + '\'' +
                ", generalDescription='" + generalDescription + '\'' +
                ", serialName='" + serialName + '\'' +
                '}';
    }
}
