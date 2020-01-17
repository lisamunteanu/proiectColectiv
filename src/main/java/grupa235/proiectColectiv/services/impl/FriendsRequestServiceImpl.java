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
    public FriendsRequestModel sendFriendRequest(String sendByUser ,String userName) throws Exception{
        Optional<RepoUser> repoUser = this.userRepository.findByUsername(userName);
        Optional<FriendsRequest> optionalFriendsRequest = this.friendsRequestRepository.existThisRequest(sendByUser,repoUser.get().getId());
        if (!optionalFriendsRequest.isPresent()){
            FriendsRequest friendsRequest = new FriendsRequest();
            friendsRequest.setSendByUser(sendByUser);
            friendsRequest.setUser(repoUser.get());
            this.friendsRequestRepository.save(friendsRequest);
            FriendsRequestModel requestModel = new FriendsRequestModel();
            requestModel.setFriendsRequest(sendByUser);
            return requestModel;
        }
        throw new Exception("You already send a request!");
    }

    @Transactional
    @Override
    public boolean acceptRequest(String currentNameUser, String user) throws Exception{
        Optional<RepoUser> firstUser = this.userRepository.findByUsername(currentNameUser);
        Optional<RepoUser> secondUser = this.userRepository.findByUsername(user);
        Optional<Friends> optionalFriends = this.friendsRepository.existFriends(firstUser.get().getId(),secondUser.get().getId());
        if (!optionalFriends.isPresent()) {
            if (firstUser != null && secondUser != null) {
                Friends friends = new Friends();
                friends.setFirstUser(firstUser.get());
                friends.setSecondUser(secondUser.get());
                this.friendsRepository.save(friends);
                this.friendsRequestRepository.deleteRequest(user, firstUser.get().getId());
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
        Optional<RepoUser> repoUser = this.userRepository.findByUsername(currentNameUser);
        Optional<RepoUser> sendBy = this.userRepository.findByUsername(user);
        if (!repoUser.isPresent() && !sendBy.isPresent()){
            this.friendsRequestRepository.deleteRequest(user,repoUser.get().getId());
            return true;
        }
        throw new Exception("A user was deleted!");
    }

    @Override
    public List<FriendsRequestModel> getAllFriendsRequest(String name) {
        List<FriendsRequestModel> friendsRequestModels = new ArrayList<>();
        Optional<RepoUser> repoUser = this.userRepository.findByUsername(name);
        Optional<List<FriendsRequest>> optionalFriendsRequest = this.friendsRequestRepository.getRequestByAUser(repoUser.get().getId());
        if (optionalFriendsRequest.isPresent()){
            List<FriendsRequest> friendsRequests = optionalFriendsRequest.get();
            friendsRequests.forEach(x->{friendsRequestModels.add(ConvertData.convertFriendsRequestToFriendsRequestModel(x));});
        }
        return friendsRequestModels;
    }
}
