package com.library.backend.service;


import com.library.backend.controller.UserController;
import com.library.backend.model.User;
import com.library.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User(); // Hier kannst du die Eigenschaften des Benutzers festlegen.
        testUser.setUsername("testUser");
        testUser.setPassword("testPassword");
    }

    @Test
    void testRegisterUser() throws Exception {
        // Mocking der Benutzerregistrierungsfunktion
        when(userService.registerUser(any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testUser\", \"password\":\"testPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }
}
