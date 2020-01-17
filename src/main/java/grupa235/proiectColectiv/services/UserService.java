package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.model.RepoUser;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<RepoUser> findUserByEmail(String email);
    Optional<RepoUser> findUserByResetToken(String resetToken);
    RepoUser save(RepoUser user);
    RepoUser update(RepoUser user,String password);

    List<String> getFriends(String userName) throws Exception;
    List<String> searchForNewFriends(String userName) throws Exception;
}
