package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.frontendModel.UserDTO;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    //it is the code for the userRole = user; 2 is for admin
    public static final String USER_ROLE = "1";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<RepoUser> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                new ArrayList<>());
    }

    public RepoUser findByUsername(String username) throws UsernameNotFoundException {
        Optional<RepoUser> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user.get();
    }

    public RepoUser save(UserDTO user) throws Exception {
        RepoUser newUser = new RepoUser();
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new Exception("User already registered!");
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(USER_ROLE);
        return userRepository.save(newUser);
    }

}