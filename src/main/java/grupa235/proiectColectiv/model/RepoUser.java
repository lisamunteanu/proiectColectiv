package grupa235.proiectColectiv.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class RepoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="username", unique = true)
    @Email(message = "Please provide a valid e-mail as username")
    @NotEmpty(message = "Please provide an e-mail as username")
    private String username;

    @Column(name="password")
    @JsonIgnore
    private String password;

    @Column(name="reset_token")
    private String resetToken;

    @Column(name="user_role")
    private String role;

    public RepoUser(String username) {
        this.username = username;
    }

    public RepoUser() {

    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
