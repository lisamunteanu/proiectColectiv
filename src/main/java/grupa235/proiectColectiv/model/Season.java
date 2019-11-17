package grupa235.proiectColectiv.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="seasons")
public class Season implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="generalDescription")
    private String generalDescription;

    @Column(name="number",nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Episode> episodes;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "idSeries")
    private Series series;

    public Season(String name, String generalDescription, Integer number) {
        this.name = name;
        this.generalDescription = generalDescription;
        this.number = number;
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

    public String getGeneralDescription() {
        return generalDescription;
    }

    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
