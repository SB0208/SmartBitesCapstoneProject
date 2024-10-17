package com.library.backend.service;
import com.library.backend.model.User;
import com.library.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service

public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this(userRepository, null);
    }

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User saveOrUpdateUser(OAuth2User oAuth2User ) {
        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email);

        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUsername(oAuth2User.getAttribute("username"));
            user.setPictureUrl(oAuth2User.getAttribute("picture"));
            userRepository.save(user);
        }
        return user;
    }

    public User registerUser(String username,String email,String rawPassword) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(rawPassword));
        return userRepository.save(user);

    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            user = new User();
            user.setEmail(username);
            user.setPassword(password);
            userRepository.save(user);


        }
        return user;
    }

    public UserDetails findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User does not exist");

        }
        return (UserDetails) user;

    }

    public User registerUser(User user) {
        User userSaved = userRepository.save(user);
        return userSaved;
    }
}
