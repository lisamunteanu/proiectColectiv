package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.Friends;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.FriendsRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FriendsRepository friendsRepository) {
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
    }

    @Override
    public List<String> getFriends(String userName) throws Exception{
        RepoUser repoUser = this.userRepository.findByUsername(userName);
        List<Friends> friends;
        List<String> names = new ArrayList<>();
        Optional<List<Friends>> repoUsers = this.friendsRepository.getFriends(repoUser.getId());
        if (repoUsers.isPresent()){
            friends = repoUsers.get();
            names = getAllFriendsUsingAnId(repoUser.getId(),friends);
        }
        return names;
    }

    @Override
    public List<String> searchForNewFriends(String userName) throws Exception {
        RepoUser repoUser = this.userRepository.findByUsername(userName);
        List<Friends> newFriends;
        List<String> names = new ArrayList<>();
        Optional<List<Friends>> repoUsers = this.friendsRepository.getFriends(repoUser.getId());
        if (repoUsers.isPresent()){
            newFriends = repoUsers.get();
            names = getAllFriendsUsingAnId(repoUser.getId(),newFriends);
            return getAllNewFriends(names,repoUser.getUsername());
        }
        return names;
    }

    private List<String> getAllFriendsUsingAnId(Long id, List<Friends> repoUserList){
        List<String> names = new ArrayList<>();
        repoUserList.forEach(friends->{
            if (friends.getFirstUser().getId() != id){
                names.add(friends.getFirstUser().getUsername());
            }
            else{
                names.add(friends.getSecondUser().getUsername());
            }
        });
        return names;
    }

    private boolean findName(List<String> currentFriends, String name){
        for (String userName: currentFriends){
            if (userName.equals(name)){
                return true;
            }
        }
        return false;
    }

    private List<String> getAllNewFriends(List<String> currentFriends, String currentUserName){
        List<RepoUser> repoUserList = (List<RepoUser>) this.userRepository.findAll();
        List<String> possibleFriends = new ArrayList<>();
        for (RepoUser user : repoUserList){
            if (!findName(currentFriends,user.getUsername()) && !user.getUsername().equals(currentUserName)){
                possibleFriends.add(user.getUsername());
            }
        }
        return possibleFriends;
    }

}
