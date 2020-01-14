package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class RateModel implements Serializable {
    private Integer rating;

    public RateModel() {
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public RateModel(Integer rating) {
        this.rating = rating;
    }
}
