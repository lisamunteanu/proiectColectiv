package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.model.RepoUser;

public interface UserService {
    RepoUser findUserByEmail(String email);
    RepoUser findUserByResetToken(String resetToken);
    RepoUser save(RepoUser user);
    RepoUser update(RepoUser user,String password);
}
