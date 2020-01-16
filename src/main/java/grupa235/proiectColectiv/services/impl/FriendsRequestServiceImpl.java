package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.FriendsRequestModel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<FriendsRequestModel> getAllFriendsRequest(String name) throws Exception {
        List<FriendsRequestModel> friendsRequestModels = new ArrayList<>();
        RepoUser repoUser = this.userRepository.findByUsername(name);
        Optional<List<FriendsRequest>> optionalFriendsRequest = this.friendsRequestRepository.getRequestByAUser(repoUser.getId());
        if (optionalFriendsRequest.isPresent()){
            List<FriendsRequest> friendsRequests = optionalFriendsRequest.get();
            friendsRequests.forEach(x->{friendsRequestModels.add(ConvertData.convertFriendsRequestToFriendsRequestModel(x));});
        }
        return friendsRequestModels;
    }
}
