package grupa235.proiectColectiv.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="series")
public class Series implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false,updatable = false)
    private Integer id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name="releaseYear")
    private String releaseYear;

    @Column(name="noOfEpisodes")
    private Integer noOfEpisodes;

    @Column(name="noOfSeasons")
    private Integer noOfSeasons;

    @Column(name="genres")
    private String genres;

    @Column(name="director")
    private String director;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Season> seasons;



    public Series() {
    }

    public Series(String name, String image, String releaseYear, Integer noOfEpisodes, Integer noOfSeasons, String genres, String director, String description, Set<Season> seasons) {
        this.name = name;
        this.image = image;
        this.releaseYear = releaseYear;
        this.noOfEpisodes = noOfEpisodes;
        this.noOfSeasons = noOfSeasons;
        this.genres = genres;
        this.director = director;
        this.description = description;
        this.seasons = seasons;
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

    public Integer getNoOfSeasons() {
        return noOfSeasons;
    }

    public void setNoOfSeasons(Integer noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }

    public String getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public void setGenres(String genres) {
        this.genres = genres;
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
