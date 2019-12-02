package grupa235.proiectColectiv.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(name="releaseDate")
    private LocalDate releaseDate;

    @Column(name="noOfEpisodes")
    private Integer noOfEpisodes;

    @Column(name="noOfSeasons")
    private Integer noOfSeasons;

    @Column(name="genres")
    private String genres;

    @Column(name="director")
    private String director;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Season> seasons;


    public Series(String name, LocalDate releaseDate, Integer noOfEpisodes, Integer noOfSeasons,String genres,String director) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.noOfEpisodes = noOfEpisodes;
        this.noOfSeasons = noOfSeasons;
        this.genres=genres;
        this.director=director;
    }

    public Series() {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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
