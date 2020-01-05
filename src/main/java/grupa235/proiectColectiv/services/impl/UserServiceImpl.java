package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RepoUser findUserByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public RepoUser findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public void save(RepoUser user) {
        userRepository.save(user);
    }
}
