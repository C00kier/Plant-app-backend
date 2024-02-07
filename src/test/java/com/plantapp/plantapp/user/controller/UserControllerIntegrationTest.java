package com.plantapp.plantapp.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.authentication.AuthenticationResponse;
import com.plantapp.plantapp.authentication.RegisterRequest;
import com.plantapp.plantapp.user.model.UpdateRequestDTO;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.model.UserDTO;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import com.plantapp.plantapp.user_game_progress.repository.UserGameProgressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserGameProgressRepository userGameProgressRepository;

    private User testUser;
    @AfterEach
    void tearDown() {
        if (testUser != null) {
            Optional<UserGameProgress> userGameProgress = userGameProgressRepository.findByUser(testUser);
            userGameProgress.ifPresent(gameProgress -> userGameProgressRepository.deleteById(gameProgress.getId()));
            userRepository.deleteById(testUser.getUserId());
        }
    }
    @BeforeEach
    void setUp() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("testPassword");

        AuthenticationResponse authenticationResponse = registerUser(registerRequest);
        testUser = userRepository.findById(authenticationResponse.getUserId()).orElse(null);
    }


     @Test
    void getUserById() throws Exception {
         String getUserEndpoint = "/user";

         ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                 .post(getUserEndpoint)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(testUser.getUserId())));

         result.andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.userId", is(testUser.getUserId())))
                 .andExpect(jsonPath("$.email", is(testUser.getEmail())));

         UserDTO userDTO = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserDTO.class);

         assertEquals(testUser.getUserId(), userDTO.getUserId());
    }

    @Test
    void updateUser() throws Exception {
        String updateUserEndpoint = "/user/update";

        UpdateRequestDTO updateRequest = new UpdateRequestDTO();
        updateRequest.setUserId(testUser.getUserId());
        updateRequest.setOldPassword("testPassword");
        updateRequest.setNewPassword("newTestPassword");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put(updateUserEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)));

        result.andExpect(MockMvcResultMatchers.status().isOk());

        User updatedUser = userRepository.findById(testUser.getUserId()).orElse(null);
        assertNotNull(updatedUser);
        assertTrue(passwordEncoder.matches("newTestPassword", updatedUser.getPassword()));
    }

    @Test
    void deleteUser() throws Exception {
        String deleteUserEndpoint = "/user/delete";

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete(deleteUserEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser.getUserId())));

        result.andExpect(MockMvcResultMatchers.status().isOk());
        assertFalse(userRepository.findById(testUser.getUserId()).isPresent());
    }

    private AuthenticationResponse registerUser(RegisterRequest request) throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), AuthenticationResponse.class);
    }
}