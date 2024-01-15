package com.plantapp.plantapp.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class AuthenticationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;


    @Test
    void register() throws Exception {
        RegisterRequest testRequest = new RegisterRequest();
        testRequest.setEmail("test@email.com");
        testRequest.setPassword("test123");

        mockMvc.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(getExistingUserId(testRequest.getEmail()))));
    }

    @Test
    void authenticate() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@email.com");
        authenticationRequest.setPassword("test123");

        mockMvc.perform(
                        post("/auth/authenticate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(getExistingUserId(authenticationRequest.getEmail()))));

    }

    private int getExistingUserId(String email){
        Optional<User> testUser = userRepository.findByEmail(email);

        return testUser.map(User::getUserId).orElse(0);
    }
}