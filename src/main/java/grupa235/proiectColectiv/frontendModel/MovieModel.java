package grupa235.proiectColectiv.frontendModel;

public class MovieModel {
    private Integer id;
    private String name;
    private String genres;
    private String releaseYear;
    private Integer duration;
    private String director;
    private Double rating;
    private String image;

    public MovieModel(Integer id, String name, String genres, String releaseYear,
                      Integer duration, String director, Double rating, String image) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.director = director;
        this.rating = rating;
        this.image = image;
    }

    public MovieModel() {
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
}
