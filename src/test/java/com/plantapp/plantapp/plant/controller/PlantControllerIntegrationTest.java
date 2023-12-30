package com.plantapp.plantapp.plant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class PlantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlantRepository plantRepository;


    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void addNewPlant() throws Exception {
        Plant testPlant = new Plant();
        testPlant.setPicture("testUrl");
        testPlant.setMatureSize(1);
        testPlant.setToxicity(true);
        testPlant.setAirPurifying(true);
        testPlant.setRepotting(5);
        testPlant.setFertilizer(5);
        testPlant.setSun(1);
        testPlant.setWater(1);
        testPlant.setCareDifficulty(1);
        testPlant.setBotanicalName("testPlant");
        testPlant.setCommonName("testCommonName");
        testPlant.setTranslation("pl");
        testPlant.setPlantOverview("test overview");
        testPlant.setNativeArea("testArea");
        testPlant.setPlantType("testType");
        testPlant.setCareDescription("test description");
        testPlant.setWaterExtended("water extended");
        testPlant.setSunExtended("sun extended");
        testPlant.setTemperature("testTemperature");
        testPlant.setHumidity("testHumidity");
        testPlant.setFertilizerExtended("fertilizer extended");
        testPlant.setBloomTime("testBloomTime");
        testPlant.setRepottingExtended("repotting extended");
        testPlant.setSoilType("test type");
        testPlant.setSoilPh("test ph");
        testPlant.setPropagating("testPropagating");
        testPlant.setPestsAndDiseases("test disease");
        testPlant.setPruning("testPruning");

        ResultActions resultActions = mockMvc.perform(post("/plant/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPlant)));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plantId", notNullValue()))
                .andExpect(jsonPath("$.humidity", is(testPlant.getHumidity())))
                .andExpect(jsonPath("$.soilPh", is(testPlant.getSoilPh())))
                .andExpect(jsonPath("$.propagating", is(testPlant.getPropagating())));

    }

    @Test
    void getAllPlants() throws Exception {
        mockMvc.perform(get("/plant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void deletePlantById() throws Exception {
        Plant existingPlant = getExistingPlant(2);
        mockMvc.perform(delete("/plant/delete/{plant-id}", existingPlant.getPlantId()))
                .andExpect(status().isOk());
        assertFalse(plantRepository.findById(existingPlant.getPlantId()).isPresent());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void changePlantById() throws Exception {
        Plant changedPlant = getExistingPlant(1);
        changedPlant.setSoilPh("newTestPh");
        changedPlant.setSun(123);
        changedPlant.setSoilType("newTestSoilType");
        ResultActions resultActions = mockMvc.perform(put("/plant/{plant-id}", changedPlant.getPlantId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(changedPlant)));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soilPh", is(changedPlant.getSoilPh())))
                .andExpect(jsonPath("$.sun", is(changedPlant.getSun())))
                .andExpect(jsonPath("$.soilType", is(changedPlant.getSoilType())));
    }

    @Test
    void getPlantById() throws Exception {
        Plant existingPlant = getExistingPlant(1);
        ResultActions resultActions = mockMvc.perform(get("/plant/{plant-id}", existingPlant.getPlantId()));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plantId", is(existingPlant.getPlantId())))
                .andExpect(jsonPath("$.sun", is(existingPlant.getSun())))
                .andExpect(jsonPath("$.soilType", is(existingPlant.getSoilType())));
    }

    @Test
    void getPlantsByName() throws Exception {
        Plant existingPlant = getExistingPlant(1);
        ResultActions resultActions = mockMvc.perform(get("/plant/name/{plant-name}", existingPlant.getBotanicalName()));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void getPlantsBySunIntensity() throws Exception {
        Plant existingPlant = getExistingPlant(1);
        ResultActions resultActions = mockMvc.perform(get("/plant/filter/sun/{sun}", existingPlant.getSun())
                .param("name", existingPlant.getBotanicalName()));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void getPlantsByDifficulty() throws Exception {
        Plant existingPlant = getExistingPlant(1);
        ResultActions resultActions = mockMvc.perform(get("/plant/filter/difficulty/{difficulty}", existingPlant.getCareDifficulty())
                .param("name", existingPlant.getBotanicalName()));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void getAirPuryfyingPlants() throws Exception {
        Plant existingPlant = getExistingPlant(1);
        ResultActions resultActions = mockMvc.perform(get("/plant/filter/airpuryfying")
                .param("name", existingPlant.getBotanicalName()));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void getNonToxicPlants() throws Exception {
        Plant existingPlant = getExistingPlant(1);
        ResultActions resultActions = mockMvc.perform(get("/plant/filter/nontoxic")
                .param("name", existingPlant.getBotanicalName()));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void getPlantsByQuizAnswers() throws Exception {
        Plant existingPlant = getExistingPlant(1);
        ResultActions resultActions = mockMvc.perform(get("/plant/filter/plants-by-quiz")
                .param("isToxic", String.valueOf(existingPlant.isToxicity()))
                .param("sun", String.valueOf(existingPlant.getSun()))
                .param("isAirPurifying", String.valueOf(existingPlant.isAirPurifying()))
                .param("matureSize", String.valueOf(existingPlant.getMatureSize()))
                .param("careDifficulty", String.valueOf(existingPlant.getCareDifficulty())));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    private Plant getExistingPlant(int id) {
        Optional<Plant> plant = plantRepository.findById(id);
        return plant.orElse(null);
    }

}