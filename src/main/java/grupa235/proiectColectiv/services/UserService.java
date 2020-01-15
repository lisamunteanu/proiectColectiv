package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.model.RepoUser;
import java.util.List;

public interface UserService {
    RepoUser findUserByEmail(String email);
    RepoUser findUserByResetToken(String resetToken);
    RepoUser save(RepoUser user);
    RepoUser update(RepoUser user,String password);

    List<String> getFriends(String userName) throws Exception;
    List<String> searchForNewFriends(String userName) throws Exception;
}
