package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.model.RepoUser;

import java.util.Optional;

public interface UserService {
    RepoUser findUserByEmail(String email);
    RepoUser findUserByResetToken(String resetToken);
    void save(RepoUser user);
}
