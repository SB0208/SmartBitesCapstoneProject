package com.library.backend;
import com.library.backend.model.User;
import com.library.backend.repository.UserRepository;
import com.library.backend.service.UserSrvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;


public class UserSrviceTest {

    private UserSrvice userSrvice;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userSrvice = new UserSrvice();
    }

    @Test
    public void testRegisterUser() {
        User user = new User("Anna Marie", "anna@example.com", "password");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User registeredUser = userSrvice.registerUser(user.getUsername(), user.getEmail(), user.getPassword());

        assertNotNull(registeredUser);
        assertEquals(user.getEmail(), registeredUser.getEmail());
    }

    @Test
    public void testLoginUser_Success() {
        User user = new User("Anna Marie", "anna@example.com", bCryptPasswordEncoder.encode("password"));
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> loginUser = userSrvice.loginUser(user.getEmail(), "password");

        assertTrue(loginUser.isPresent());
        assertEquals(user.getEmail(), loginUser.get().getEmail());
    }

    @Test
    public void testLoginUser_Failure() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Optional<User> loginUser = userSrvice.loginUser("unknown@example.com", "password");
        assertFalse(loginUser.isPresent());
    }



}
