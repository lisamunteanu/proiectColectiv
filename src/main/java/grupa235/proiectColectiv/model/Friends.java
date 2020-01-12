package grupa235.proiectColectiv.model;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {
            CascadeType.ALL})
    @JoinColumn(name = "firstUser")
    private RepoUser firstUser;

    @ManyToOne(cascade = {
            CascadeType.ALL})
    @JoinColumn(name = "secondUser")
    private RepoUser secondUser;

    public Friends() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RepoUser getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(RepoUser firstUser) {
        this.firstUser = firstUser;
    }

    public RepoUser getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(RepoUser secondUser) {
        this.secondUser = secondUser;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", firstUser=" + firstUser +
                ", secondUser=" + secondUser +
                '}';
    }
}
