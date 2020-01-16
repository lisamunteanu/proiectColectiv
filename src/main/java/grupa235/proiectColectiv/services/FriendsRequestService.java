package grupa235.proiectColectiv.services;


import grupa235.proiectColectiv.frontendModel.FriendsRequestModel;
import grupa235.proiectColectiv.model.FriendsRequest;

import java.util.List;

public interface FriendsRequestService {

    FriendsRequestModel sendFriendRequest(String sendByUser ,String userName) throws Exception;

    boolean acceptRequest(String currentNameUser, String user) throws Exception;

    boolean cancelRequest(String currentNameUser, String user) throws Exception;

    List<FriendsRequestModel> getAllFriendsRequest(String name) throws Exception;

}
