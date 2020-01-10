package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.Image;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.UserImage;
import grupa235.proiectColectiv.repository.UserImageRepository;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserImageImpl implements UserImageService {

    private final UserImageRepository userImageRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserImageImpl(UserImageRepository userImageRepository, UserRepository userRepository) {
        this.userImageRepository = userImageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Image saveImage(String userName, String urlImage) {
        RepoUser repoUser = this.userRepository.findByUsername(userName);
        UserImage userImage = new UserImage(urlImage);
        userImage.setUser(repoUser);
        Image image = getImage(userName);
        if (image!=null){
            this.userImageRepository.updateImage(urlImage,repoUser.getId());
        }
        else{
            this.userImageRepository.save(userImage);
        }
        return ConvertData.convertUserImageToImage(userImage);
    }

    @Override
    public Image getImage(String userName) {
        RepoUser repoUser = this.userRepository.findByUsername(userName);
        Optional<UserImage> userImage = this.userImageRepository.getImage(repoUser.getId());
        return userImage.map(ConvertData::convertUserImageToImage).orElse(null);
    }
}
