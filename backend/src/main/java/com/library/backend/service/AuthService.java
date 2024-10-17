package com.library.backend.service;

import com.library.backend.model.User;
import com.library.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service



public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Incorrect password or email");
        }
        return jwtUtil.generateToken(email);  // JWT-Token erzeugen
    }
}
