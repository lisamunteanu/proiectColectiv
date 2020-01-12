package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.Friends;
import grupa235.proiectColectiv.model.FriendsRequest;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.FriendsRepository;
import grupa235.proiectColectiv.repository.FriendsRequestRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.FriendsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
// deci tu vrei sa adaugi alte fisiere si dupa sa dai commit?da dar sunt foarte multe am multe create noi
// si astea le ai pe pc nu? sunt aici pe branch . unde?Clasa FriendsRequestController spre exemplu e un fisier din 10
//si astea is push uite nu? au fost , ca am sters pull requestul stai sa vad ceva
// cred ca cel mai simplu ii sa faci alt branch
//ca daca ai dat push acuma ori le salvezi local astea si dai git push cu ce aveai inainte si dupa le readaugi
//deci sa inteleg, imi fac alt branch dar cum imi pastrez ce am lucrat aici...?
@Service
public class FriendsRequestServiceImpl implements FriendsRequestService {

    private final UserRepository userRepository;
    private final FriendsRequestRepository friendsRequestRepository;
    private final FriendsRepository friendsRepository;

    @Autowired
    public FriendsRequestServiceImpl(UserRepository userRepository, FriendsRequestRepository friendsRequestRepository, FriendsRepository friendsRepository) {
        this.userRepository = userRepository;
        this.friendsRequestRepository = friendsRequestRepository;
        this.friendsRepository = friendsRepository;
    }


    @Override
    public FriendsRequest sendFriendRequest(String sendByUser ,String userName) throws Exception{
        RepoUser repoUser = this.userRepository.findByUsername(userName);
        Optional<FriendsRequest> optionalFriendsRequest = this.friendsRequestRepository.existThisRequest(sendByUser,repoUser.getId());
        if (!optionalFriendsRequest.isPresent()){
            FriendsRequest friendsRequest = new FriendsRequest();
            friendsRequest.setSendByUser(sendByUser);
            friendsRequest.setUser(repoUser);
            this.friendsRequestRepository.save(friendsRequest);
            FriendsRequest requestModel = new FriendsRequest();
            requestModel.setSendByUser(sendByUser);
            return requestModel;
        }
        throw new Exception("They are already friends!");
    }

    @Transactional
    @Override
    public boolean acceptRequest(String currentNameUser, String user) throws Exception{
        RepoUser firstUser = this.userRepository.findByUsername(currentNameUser);
        RepoUser secondUser = this.userRepository.findByUsername(user);
        Optional<Friends> optionalFriends = this.friendsRepository.existFriends(firstUser.getId(),secondUser.getId());
        if (!optionalFriends.isPresent()) {
            if (firstUser != null && secondUser != null) {
                Friends friends = new Friends();
                friends.setFirstUser(firstUser);
                friends.setSecondUser(secondUser);
                this.friendsRepository.save(friends);
                this.friendsRequestRepository.deleteRequest(user, firstUser.getId());
                return true;
            }
        }
        else{
            return false;
        }
        throw new Exception("A user was deleted!");
    }

    @Transactional
    @Override
    public boolean cancelRequest(String currentNameUser, String user) throws Exception {
        RepoUser repoUser = this.userRepository.findByUsername(currentNameUser);
        RepoUser sendBy = this.userRepository.findByUsername(user);
        if (repoUser!=null && sendBy!=null){
            this.friendsRequestRepository.deleteRequest(user,repoUser.getId());
            return true;
        }
        throw new Exception("A user was deleted!");
    }
}
