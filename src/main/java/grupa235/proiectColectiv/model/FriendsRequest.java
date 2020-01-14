package grupa235.proiectColectiv.model;

import javax.persistence.*;

@Entity
@Table(name = "friendsrequest")
public class FriendsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sendby")
    private String sendByUser;

    @ManyToOne(cascade = {
            CascadeType.ALL})
    @JoinColumn(name = "idUser")
    private RepoUser user;


    public FriendsRequest(String sendByUser, RepoUser user) {
        this.sendByUser = sendByUser;
        this.user = user;
    }

    public FriendsRequest(String sendByUser) {
        this.sendByUser = sendByUser;
    }

    public FriendsRequest(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSendByUser() {
        return sendByUser;
    }

    public void setSendByUser(String sendByUser) {
        this.sendByUser = sendByUser;
    }

    public RepoUser getUser() {
        return user;
    }

    public void setUser(RepoUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FriendsRequest{" +
                "id=" + id +
                ", sendByUser='" + sendByUser + '\'' +
                ", user=" + user +
                '}';
    }
}
