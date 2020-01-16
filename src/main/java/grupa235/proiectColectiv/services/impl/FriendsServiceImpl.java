package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.Friends;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.FriendsRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsRepository friends;

    @Autowired
    private UserRepository users;

    @Override
    public Optional<List<Friends>> findAllFriendsForUser(String username) {
        RepoUser user =  users.findByUsername(username);
        return friends.getFriends(user.getId());
    }
}
