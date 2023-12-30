package com.plantapp.plantapp.user_activity.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.authentication.AuthenticationResponse;
import com.plantapp.plantapp.authentication.RegisterRequest;
import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.service.UserActivityService;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import com.plantapp.plantapp.user_plant.service.UserPlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class UserActivityControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserPlantService userPlantService;


    @BeforeEach
    void setUp() throws Exception {
        RegisterRequest testRegisterRequest = new RegisterRequest("test@example.pl", "testpassword");
        AuthenticationResponse authenticationResponse = registerUser(testRegisterRequest);
        User testUser = userRepository.findById(authenticationResponse.getUserId()).orElse(null);
        Plant testPlant = getExistingPlant(1);
        Date testDate = new Date();
        UserPlant userPlant = new UserPlant();
        userPlant.setUser(testUser);
        userPlant.setPlant(testPlant);
        userPlant.setRoom("testRoom");
        userPlant.setAlias("testAlias");
        userPlant.setLastWatered(testDate);
        userPlant.setLastFertilized(testDate);
        userPlant.setLastPropagated(testDate);
        userPlant.setLastPruned(testDate);
        userPlant.setLastRepotted(testDate);
        userPlantService.addPlantToUserPlants(userPlant);
        userActivityService.addPlantActivity(userPlant.getUserPlantId(), ActivityType.REPOTTING_PLANT);

    }

    @Test
    void getAllUsersActivityLog() throws Exception {
        mockMvc.perform(get("/user-activity-log/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

    }

    private AuthenticationResponse registerUser(RegisterRequest request) throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), AuthenticationResponse.class);
    }

    private Plant getExistingPlant(int id) {
        Optional<Plant> plant = plantRepository.findById(id);
        return plant.orElse(null);
    }
}