package grupa235.proiectColectiv.model;
import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name="director")
    private String director;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;


    public Movie() {
    }

    public Movie(String name, String genres, String releaseYear, Integer duration, Double rating, String director,
                 String image, String description) {
        this.name = name;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.director = director;
        this.image=image;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
