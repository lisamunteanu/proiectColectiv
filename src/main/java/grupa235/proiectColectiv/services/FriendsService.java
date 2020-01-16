package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.model.Friends;

import java.util.List;
import java.util.Optional;

public interface FriendsService {
    Optional<List<Friends>> findAllFriendsForUser(String username);
}
