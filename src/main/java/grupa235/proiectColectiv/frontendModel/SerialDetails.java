package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class SerialDetails implements Serializable {

    private Integer id;
    private String name;
    private String image;
    private Integer noOfSeasons;
    private Integer noOfEpisodes;
    private String genres;
    private String director;
    private String releaseYear;
    private Double rating;
    private String description;

    public SerialDetails(Integer id, String name, String image, Integer noOfSeasons, Integer noOfEpisodes, String genres, String director, String releaseYear, Double rating, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.noOfSeasons = noOfSeasons;
        this.noOfEpisodes= noOfEpisodes;
        this.genres = genres;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.description = description;
    }

    public SerialDetails() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getNoOfEpisodes() {
        return noOfEpisodes;
    }

    public void setNoOfEpisodes(Integer noOfEpisodes) {
        this.noOfEpisodes = noOfEpisodes;
    }

    @Override
    public String toString() {
        return "SerialDetails{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", noOfSeasons=" + noOfSeasons +
                ", noOfEpisodes=" + noOfEpisodes +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }
}
