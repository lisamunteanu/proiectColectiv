package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class MovieDetails implements Serializable {

    private Integer id;
    private String name;
    private String genres;
    private String releaseYear;
    private Integer duration;
    private Double rating;
    private String director;
    private String image;
    private String description;

    public MovieDetails(Integer id, String name, String genres, String releaseYear, Integer duration, Double rating, String director, String image, String description) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.rating = rating;
        this.director = director;
        this.image = image;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovieDetails(){}

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

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "name='" + name + '\'' +
                ", genres='" + genres + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", director='" + director + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
