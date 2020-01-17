package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class FriendsRequestModel implements Serializable {

    private String friendsRequest;

    public FriendsRequestModel(String friendsRequest) {
        this.friendsRequest = friendsRequest;
    }

    public FriendsRequestModel(){}

    public String getFriendsRequest() {
        return friendsRequest;
    }

    public void setFriendsRequest(String friendsRequest) {
        this.friendsRequest = friendsRequest;
    }

    @Override
    public String toString() {
        return "FriendsRequestModel{" +
                "friendsRequest='" + friendsRequest + '\'' +
                '}';
    }
}
