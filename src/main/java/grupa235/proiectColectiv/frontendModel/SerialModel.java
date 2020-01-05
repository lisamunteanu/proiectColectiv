package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;
import java.util.Date;

public class SerialModel implements Serializable {

    private String name;
    private String image;
    private Integer noOfEpisodes;
    private Integer noOfSeasons;
    private String genres;
    private String director;
    private Integer rating;
    private Date startYear;
    private Date endYear;


    public SerialModel(String name, String image,Integer noOfEpisodes, Integer noOfSeasons, String genres, String director, Integer rating, Date startYear, Date endYear) {
        this.name = name;
        this.image = image;
        this.noOfEpisodes = noOfEpisodes;
        this.noOfSeasons = noOfSeasons;
        this.genres = genres;
        this.director = director;
        this.rating=rating;
        this.startYear=startYear;
        this.endYear=endYear;
    }

    public SerialModel() {
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

    public Integer getNoOfEpisodes() {
        return noOfEpisodes;
    }

    public void setNoOfEpisodes(Integer noOfEpisodes) {
        this.noOfEpisodes = noOfEpisodes;
    }

    public Integer getNoOfSeasons() {
        return noOfSeasons;
    }

    public void setNoOfSeasons(Integer noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRating() {
        return rating;
    }

    public void setrating(Integer rating) {
        this.rating = rating;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }


    @Override
    public String toString() {
        return "SerialModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", noOfEpisodes=" + noOfEpisodes +
                ", noOfSeasons=" + noOfSeasons +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                ", rating='" + rating + '\'' +
                ", startYear='" + startYear + '\'' +
                ", endYear='" + endYear + '\'' +
                '}';
    }
}
