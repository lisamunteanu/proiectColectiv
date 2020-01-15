package grupa235.proiectColectiv.model;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="movies")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private Integer id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="genres",nullable = false)
    private String genres;

    @Column(name="releaseYear")
    private String releaseYear;

    @Column(name="duration")
    private Integer duration;

    @Column(name="rating")
    private Double rating;

    @Column(name="director")
    private String director;

    @Column(name = "image")
    private String image;

    @Column(name = "addedDate")
    private Date addedDate;


    public Movie() {
    }

    public Movie(String name, String genres, String releaseYear, Integer duration, Double rating, String director,
                 String image, Date addedDate) {
        this.name = name;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.rating = rating;
        this.director = director;
        this.image=image;
        this.addedDate=addedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

}
