package grupa235.proiectColectiv.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="episodes")
public class Episode implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="number",nullable = false)
    private Integer number;

    @Column(name="description")
    private String description;

    @Column(name="duration")
    private Integer duration;

    @Column(name="addedDate")
    private Date addedDate;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "idSeason")
    private Season season;


    public Episode(String name, Integer number, String description, Integer duration, Date addedDate) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.duration = duration;
        this.addedDate=addedDate;
    }

    public Episode() {
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
