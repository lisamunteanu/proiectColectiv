package grupa235.proiectColectiv.model;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "idSeason")
    private Season season;


    public Episode(String name, Integer number, String description, Integer duration) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.duration = duration;
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
}
