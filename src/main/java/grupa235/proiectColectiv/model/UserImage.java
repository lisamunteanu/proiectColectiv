package grupa235.proiectColectiv.model;

import javax.persistence.*;

@Entity
@Table(name = "userimage")
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="image")
    private String image;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "idUser")
    private RepoUser user;

    public UserImage(String image) {
        this.image = image;
    }

    public UserImage(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RepoUser getUser() {
        return user;
    }

    public void setUser(RepoUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserImage{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", user=" + user +
                '}';
    }
}
