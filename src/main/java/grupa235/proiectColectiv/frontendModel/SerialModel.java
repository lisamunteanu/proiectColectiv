package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class SerialModel implements Serializable {

    private Integer id;
    private String name;
    private String image;
    private Integer noOfEpisodes;
    private Integer noOfSeasons;
    private String genres;
    private String director;
    private Double rating;
    private String releaseYear;
    private String description;


    public SerialModel(Integer id, String name, String image, Integer noOfEpisodes, Integer noOfSeasons, String genres, String director, Double rating, String releaseYear, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.noOfEpisodes = noOfEpisodes;
        this.noOfSeasons = noOfSeasons;
        this.genres = genres;
        this.director = director;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.description = description;
    }

    public SerialModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
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

    @Override
    public String toString() {
        return "SerialModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", noOfEpisodes=" + noOfEpisodes +
                ", noOfSeasons=" + noOfSeasons +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
