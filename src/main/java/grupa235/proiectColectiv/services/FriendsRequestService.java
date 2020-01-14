package grupa235.proiectColectiv.services;


import grupa235.proiectColectiv.model.FriendsRequest;

public interface FriendsRequestService {

    FriendsRequest sendFriendRequest(String sendByUser ,String userName) throws Exception;

    boolean acceptRequest(String currentNameUser, String user) throws Exception;

    boolean cancelRequest(String currentNameUser, String user) throws Exception;

}
