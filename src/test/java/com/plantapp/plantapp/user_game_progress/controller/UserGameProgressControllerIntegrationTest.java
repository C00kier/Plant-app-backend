package com.plantapp.plantapp.user_game_progress.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.authentication.AuthenticationResponse;
import com.plantapp.plantapp.authentication.RegisterRequest;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.service.UserActivityService;
import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import com.plantapp.plantapp.user_game_progress.model.UserGameTitle;
import com.plantapp.plantapp.user_game_progress.model.UserProgressRequestDTO;
import com.plantapp.plantapp.user_game_progress.repository.UserGameProgressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class UserGameProgressControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGameProgressRepository userGameProgressRepository;

    @MockBean
    private UserActivityService userActivityService;

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
    void getUserExperienceByUserId() throws Exception {
        mockMvc.perform(get("/user-game-progress/get-exp").param("userId", String.valueOf(testUser.getUserId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(0)));
    }

    @Test
    void getUserGameTitleByUserId() throws Exception {
        mockMvc.perform(get("/user-game-progress/get-game-title").param("userId", String.valueOf(testUser.getUserId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(UserGameTitle.POCZĄTKUJĄCY_ZIELONOSKÓRNIK.name())));
    }

    @Test
    void getUserExperienceLeftToNextLvlByUserId() throws Exception {
        mockMvc.perform(get("/user-game-progress/get-exp-left").param("userId", String.valueOf(testUser.getUserId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(15)));
    }

    @Test
    void removeUserExperienceByUserId() throws Exception {
        ResultActions resultActions = mockMvc.perform(delete("/user-game-progress/delete-exp").param("userId", String.valueOf(testUser.getUserId())));
        resultActions
                .andExpect(status().isOk());
        assertFalse(userGameProgressRepository.findByUser(testUser).isPresent());
    }

    @Test
    void updateUserExperienceByUserId() throws Exception {
        UserProgressRequestDTO userProgressRequestDTO = new UserProgressRequestDTO();
        userProgressRequestDTO.setExp(15);
        userProgressRequestDTO.setUserId(testUser.getUserId());



        mockMvc.perform(patch("/user-game-progress/update-exp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userProgressRequestDTO)))
                .andExpect(status().isOk());
        assertTrue(userGameProgressRepository.findByUser(testUser).isPresent());
        assertEquals(15, userGameProgressRepository.findByUser(testUser).get().getExperience());
        assertEquals(UserGameTitle.NOWICJUSZ_ROŚLINNY, userGameProgressRepository.findByUser(testUser).get().getGAME_TITLE());
    }

    @Test
    void awardForWeeklyWatering() throws Exception {
        UserProgressRequestDTO userProgressRequestDTO = new UserProgressRequestDTO();
        userProgressRequestDTO.setExp(30);
        userProgressRequestDTO.setUserId(testUser.getUserId());

        when(userActivityService.areAllUserActivitiesOnTime(testUser, ActivityType.WATERING_PLANT, 7)).thenReturn(true);

        mockMvc.perform(patch("/user-game-progress/award-for-weekly-watering")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userProgressRequestDTO)))
                .andExpect(status().isOk());
        assertTrue(userGameProgressRepository.findByUser(testUser).isPresent());
        assertEquals(30, userGameProgressRepository.findByUser(testUser).get().getExperience());
    }

    @Test
    void awardForMonthlyFertilizing() throws Exception {
        UserProgressRequestDTO userProgressRequestDTO = new UserProgressRequestDTO();
        userProgressRequestDTO.setExp(35);
        userProgressRequestDTO.setUserId(testUser.getUserId());

        when(userActivityService.areAllUserActivitiesOnTime(testUser, ActivityType.FERTILIZING_PLANT, 32)).thenReturn(true);

        mockMvc.perform(patch("/user-game-progress/award-for-monthly-fertilizing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userProgressRequestDTO)))
                .andExpect(status().isOk());
        assertTrue(userGameProgressRepository.findByUser(testUser).isPresent());
        assertEquals(35, userGameProgressRepository.findByUser(testUser).get().getExperience());
    }

    @Test
    void awardForYearlyRepotting() throws Exception {
        UserProgressRequestDTO userProgressRequestDTO = new UserProgressRequestDTO();
        userProgressRequestDTO.setExp(60);
        userProgressRequestDTO.setUserId(testUser.getUserId());

        when(userActivityService.areAllUserActivitiesOnTime(testUser, ActivityType.REPOTTING_PLANT, 365)).thenReturn(true);

        mockMvc.perform(patch("/user-game-progress/award-for-yearly-repotting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userProgressRequestDTO)))
                .andExpect(status().isOk());
        assertTrue(userGameProgressRepository.findByUser(testUser).isPresent());
        assertEquals(60, userGameProgressRepository.findByUser(testUser).get().getExperience());
    }

    private AuthenticationResponse registerUser(RegisterRequest request) throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), AuthenticationResponse.class);
    }
}