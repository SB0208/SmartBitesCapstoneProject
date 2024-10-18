package com.library.backend.controller;
import com.library.backend.model.User;
import com.library.backend.service.UserSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private UserSrvice userSrvice;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userSrvice.registerUser(user.getUsername(),user.getEmail(),user.getPassword());
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestBody User user) {
        return userSrvice.loginUser(user.getEmail(),user.getPassword());
    }


}
