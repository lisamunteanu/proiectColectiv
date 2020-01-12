package grupa235.proiectColectiv.services;

import java.util.List;

public interface UserService {

    List<String> getFriends(String userName) throws Exception;

    List<String> searchForNewFriends(String userName) throws Exception;

}
