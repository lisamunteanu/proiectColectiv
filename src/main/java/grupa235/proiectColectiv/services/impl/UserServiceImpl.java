package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.UserRepository;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public RepoUser save(RepoUser user) {
        return userRepository.save(user);
    }

    @Override
    public RepoUser update(RepoUser user,String password) {
        RepoUser found = userRepository.findByUsername(user.getUsername());
        if(found == null)
            throw new UsernameNotFoundException(user.getUsername());
        found.setPassword(password);
        return found;
    }
}
