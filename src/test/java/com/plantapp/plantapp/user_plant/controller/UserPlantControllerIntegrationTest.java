package com.plantapp.plantapp.user_plant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.plant.controller.PlantController;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@DisplayName("Integration tests for User Plant API endpoints")
@Tag("integration")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserPlantControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserPlantController userPlantController;

    @Autowired
    private PlantController plantController;

    @Autowired
    private UserRepository userRepository;

    private final int userId = 1;

    @BeforeAll
    void beforeAll(){
        dbSetup();
    }

    @Test
    void testGetAllUserPlantsByUserId() throws Exception {
        mockMvc.perform(get(String.format("/user-plant/%s", userId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void testRemovePlantFromUserPlantsById() throws Exception {
        String userPlantId = getExistingUserPlantId();
        mockMvc.perform(delete(String.format("/user-plant/%s", userPlantId)))
                .andExpect(status().isOk());
    }

    @Test
    void testRemoveRoomFromPlant() throws Exception {
        String userPlantId = getExistingUserPlantId();
        mockMvc.perform(patch(String.format("/user-plant/%s/room/remove", userPlantId)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUserPlantRoom() throws Exception {
        String userPlantId = getExistingUserPlantId();
        String roomName = "testRoom";
        ResultActions resultActions = mockMvc.perform(patch(String.format("/user-plant/%s/room", userPlantId))
                        .param("roomName", roomName)
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUserPlantAlias() throws Exception {
        String userPlantId = getExistingUserPlantId();
        String aliasName = "testAlias";
        ResultActions resultActions = mockMvc.perform(patch(String.format("/user-plant/%s/alias", userPlantId))
                        .param("alias", aliasName)
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLastWatering() throws Exception {
        testCareActionWithMapBody("last-watering");
    }

    @Test
    void testUpdateLastPropagated() throws Exception {
        testCareActionWithMapBody("/last-propagated");
    }

    @Test
    void testUpdateLastRepotted() throws Exception {
        testCareActionWithMapBody("/last-repotted");
    }

    @Test
    void testUpdateLastFertilized() throws Exception {
        testCareActionWithMapBody("/last-fertilized");
    }

    @Test
    void testUpdateLastPruned() throws Exception {
        testCareActionWithMapBody("/last-pruned");
    }

    private String getExistingUserPlantId() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(String.format("/user-plant/%s", userId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
        String content = resultActions.andReturn().getResponse().getContentAsString();
        return content.substring(content.indexOf(":") + 1, content.indexOf(","));
    }

    private void testCareActionWithMapBody(String methodEndpoint) throws Exception {
        String userPlantId = getExistingUserPlantId();
        Map<String,Date> dateMap= new HashMap<>();
        dateMap.put("date", new Date());
        ResultActions resultActions = mockMvc.perform(patch(String.format("/user-plant/%s/%s", userPlantId, methodEndpoint))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dateMap)));

        resultActions
                .andExpect(status().isOk());
    }

    private void dbSetup(){
        User user = new User(
                "test@test.com",
                "test123",
                "testUser");

        userRepository.save(user);

        //to test update date methods
        long millisecondsSinceEpoch = System.currentTimeMillis();
        long dayBeforeInMilliseconds = millisecondsSinceEpoch - 86400000;

        UserPlant userPlant1 = new UserPlant(1,
                user,
                plantController.getPlantById(1).getBody(),
                "kitchen",
                "testPlant1",
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds));

        UserPlant userPlant2 = new UserPlant(2,
                user,
                plantController.getPlantById(2).getBody(),
                "bathroom",
                "testPlant2",
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds),
                new Date(dayBeforeInMilliseconds));

        userPlantController.addPlantToUserPlants(userPlant1);
        userPlantController.addPlantToUserPlants(userPlant2);
    }
}