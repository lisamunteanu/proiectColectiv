package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.Friends;
import grupa235.proiectColectiv.model.FriendsRequest;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.FriendsRepository;
import grupa235.proiectColectiv.repository.FriendsRequestRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;
    private final FriendsRequestRepository friendsRequestRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FriendsRepository friendsRepository, FriendsRequestRepository friendsRequestRepository) {
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
        this.friendsRequestRepository = friendsRequestRepository;
    }

    @Override
    public RepoUser findUserByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public RepoUser findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public RepoUser save(RepoUser user) {
        return userRepository.save(user);
    }

    @Override
    public RepoUser update(RepoUser user,String password) {
        RepoUser found = userRepository.findByUsername(user.getUsername());
        if(found == null)
            throw new UsernameNotFoundException(user.getUsername());
        found.setPassword(password);
        return found;
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
        else{
            names = getAllFriendsName(userName);
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
            if (!findName(currentFriends,user.getUsername()) && !user.getUsername().equals(currentUserName) && !user.getRole().equals("2")){
                possibleFriends.add(user.getUsername());
            }
        }
        return possibleFriends;
    }

    private List<String> getAllFriendsName(String currentName){
        List<String> names = new ArrayList<>();
        List<RepoUser> repoUserList = (List<RepoUser>) this.userRepository.findAll();
        repoUserList.forEach(x->{
            if ( !x.getUsername().equals(currentName) && !x.getRole().equals("2")){
                names.add(x.getUsername());
            }
        });
        return names;
    }

}
