package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.Image;
import grupa235.proiectColectiv.model.UserImage;

public interface UserImageService {

    Image saveImage(String userName, String urlImage);

    Image getImage(String userName);

}
