package com.library.backend.controller;

import com.library.backend.model.User;
import com.library.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest

public class UserControllerTest {

    private UserRepository userRepository;

    @Test
    public void registerUser() {
        User user = new User(2, "MM", "passwort", "maria@gmail.com");
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        userRepository.save(user);


    }
}

