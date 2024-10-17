package com.library.backend.controller;

import com.library.backend.model.SignUpRequest;
import com.library.backend.model.User;
import com.library.backend.service.UserService;
import com.library.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    // Registrierungs-Endpoint
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.registerUser(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
        return "Benutzer erfolgreich registriert!";
    }

    // Login-Endpoint
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);  // JWT-Token zurückgeben
    }
}
