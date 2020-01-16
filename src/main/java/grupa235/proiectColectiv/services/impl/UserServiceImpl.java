package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.Friends;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.FriendsRepository;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FriendsRepository friendsRepository) {
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
    }

    @Override
    public Optional<RepoUser> findUserByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public Optional<RepoUser> findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public RepoUser save(RepoUser user) {
        return userRepository.save(user);
    }

    @Override
    public RepoUser update(RepoUser user,String password) {
        Optional<RepoUser> found = userRepository.findByUsername(user.getUsername());
        if(!found.isPresent())
            throw new UsernameNotFoundException(user.getUsername());
        found.get().setPassword(password);
        return found.get();
    }

    @Override
    public List<String> getFriends(String userName){
        Optional<RepoUser> repoUser = this.userRepository.findByUsername(userName);
        List<Friends> friends;
        List<String> names = new ArrayList<>();
        Optional<List<Friends>> repoUsers = this.friendsRepository.getFriends(repoUser.get().getId());
        if (repoUsers.isPresent()){
            friends = repoUsers.get();
            names = getAllFriendsUsingAnId(repoUser.get().getId(),friends);
        }
        return names;
    }

    @Override
    public List<String> searchForNewFriends(String userName) {
        Optional<RepoUser> repoUser = this.userRepository.findByUsername(userName);
        List<Friends> newFriends;
        List<String> names = new ArrayList<>();
        Optional<List<Friends>> repoUsers = this.friendsRepository.getFriends(repoUser.get().getId());
        if (repoUsers.isPresent()){
            newFriends = repoUsers.get();
            names = getAllFriendsUsingAnId(repoUser.get().getId(),newFriends);
            return getAllNewFriends(names,repoUser.get().getUsername());
        }
        else{
            names = getAllFriendsName();
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

    private List<String> getAllFriendsName(){
        List<RepoUser> repoUserList = (List<RepoUser>) this.userRepository.findAll();
        return repoUserList.stream().map(RepoUser::getUsername).collect(Collectors.toList());
    }

}
