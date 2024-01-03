package com.plantapp.plantapp.plant.controller;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.model.PlantNameDTO;
import com.plantapp.plantapp.plant.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PlantControllerUnitTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantController plantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlants() {
        List<Plant> plants = new ArrayList<>();
        plants.add(new Plant());
        plants.add(new Plant());
        when(plantService.getAllPlants()).thenReturn(plants);
        ResponseEntity<List<Plant>> response = plantController.getAllPlants();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plants, response.getBody());
        verify(plantService, times(1)).getAllPlants();
    }
    @Test
    void testGetAllPlantsException() {
        when(plantService.getAllPlants()).thenThrow(new RuntimeException());
        ResponseEntity<List<Plant>> response = plantController.getAllPlants();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(plantService, times(1)).getAllPlants();
    }

    @Test
    void testAddNewPlant(){
        Plant newPlant = new Plant();
        when(plantService.addNewPlant(any(Plant.class))).thenReturn(newPlant);
        ResponseEntity<Plant> response = plantController.addNewPlant(newPlant);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newPlant, response.getBody());
        verify(plantService, times(1)).addNewPlant(any(Plant.class));
    }

    @Test
    void testAddNewPlantException() {
        when(plantService.addNewPlant(any(Plant.class))).thenThrow(new RuntimeException());
        ResponseEntity<Plant> response = plantController.addNewPlant(new Plant());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).addNewPlant(any(Plant.class));
    }

    @Test
    public void testDeletePlantById() {
        int plantId = 1;
        ResponseEntity<Object> response = plantController.deletePlantById(plantId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(plantService, times(1)).deletePlantById(plantId);
    }

    @Test
    public void testDeletePlantByIdNotFound() {
        int nonExistingPlantId = 999;
        doThrow(new RuntimeException("Plant not found")).when(plantService).deletePlantById(nonExistingPlantId);
        ResponseEntity<Object> response = plantController.deletePlantById(nonExistingPlantId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(plantService, times(1)).deletePlantById(nonExistingPlantId);
    }

    @Test
    public void testChangePlantById() {
        int plantId = 1;
        Plant updatedPlant = new Plant();
        when(plantService.changePlantById(plantId, updatedPlant)).thenReturn(updatedPlant);
        ResponseEntity<Plant> response = plantController.changePlantById(plantId, updatedPlant);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPlant, response.getBody());
        verify(plantService, times(1)).changePlantById(plantId, updatedPlant);
    }

    @Test
    public void testChangePlantById_Exception() {
        int plantId = 1;
        Plant updatedPlant = new Plant();
        when(plantService.changePlantById(plantId, updatedPlant)).thenThrow(new RuntimeException("Internal Server Error"));
        ResponseEntity<Plant> response = plantController.changePlantById(plantId, updatedPlant);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).changePlantById(plantId, updatedPlant);
    }

    @Test
    public void testGetPlantById() {
        int existingPlantId = 1;
        Plant existingPlant = new Plant();
        when(plantService.getPlantById(existingPlantId)).thenReturn(Optional.of(existingPlant));
        ResponseEntity<Plant> response = plantController.getPlantById(existingPlantId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingPlant, response.getBody());
        verify(plantService, times(1)).getPlantById(existingPlantId);
    }

    @Test
    public void testGetPlantById_NotExists() {
        int nonExistingPlantId = 999;
        when(plantService.getPlantById(nonExistingPlantId)).thenReturn(Optional.empty());
        ResponseEntity<Plant> response = plantController.getPlantById(nonExistingPlantId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).getPlantById(nonExistingPlantId);
    }

    @Test
    public void testGetPlantById_Exception() {
        int plantId = 1;
        when(plantService.getPlantById(plantId)).thenThrow(new RuntimeException("Internal Server Error"));
        ResponseEntity<Plant> response = plantController.getPlantById(plantId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).getPlantById(plantId);
    }

    @Test
    public void testGetPlantsByName() {
        String existingPlantName = "Monstera";
        List<PlantNameDTO> existingPlants = new ArrayList<>();
        existingPlants.add(new PlantNameDTO(1, "Monstera"));
        when(plantService.getPlantsByName(existingPlantName)).thenReturn(existingPlants);
        ResponseEntity<List<PlantNameDTO>> response = plantController.getPlantsByName(existingPlantName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingPlants, response.getBody());
        verify(plantService, times(1)).getPlantsByName(existingPlantName);
    }


    @Test
    public void testGetPlantsByName_NotExists() {
        String nonExistingPlantName = "Christmas Tree";
        when(plantService.getPlantsByName(nonExistingPlantName)).thenReturn(new ArrayList<>());
        ResponseEntity<List<PlantNameDTO>> response = plantController.getPlantsByName(nonExistingPlantName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(plantService, times(1)).getPlantsByName(nonExistingPlantName);
    }

    @Test
    public void testGetPlantsByName_Exception() {
        String plantName = "Monstera";
        when(plantService.getPlantsByName(plantName)).thenThrow(new RuntimeException("Internal Server Error"));
        ResponseEntity<List<PlantNameDTO>> response = plantController.getPlantsByName(plantName);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).getPlantsByName(plantName);
    }

    @Test
    public void testGetPlantsByDifficulty() {
        int difficulty = 0;
        String plantName = "Monstera";
        List<PlantNameDTO> filteredPlants = new ArrayList<>();
        filteredPlants.add(new PlantNameDTO(1, "Monstera"));
        when(plantService.getPlantsByDifficulty(difficulty, plantName)).thenReturn(filteredPlants);
        ResponseEntity<List<PlantNameDTO>> response = plantController.getPlantsByDifficulty(difficulty, plantName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filteredPlants, response.getBody());
        verify(plantService, times(1)).getPlantsByDifficulty(difficulty, plantName);
    }

    @Test
    public void testGetPlantsByDifficulty_Exception() {
        int difficulty = 0;
        when(plantService.getPlantsByDifficulty(difficulty, null)).thenThrow(new RuntimeException("Internal Server Error"));
        ResponseEntity<List<PlantNameDTO>> response = plantController.getPlantsByDifficulty(difficulty, null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).getPlantsByDifficulty(difficulty, null);
    }

    @Test
    public void testGetAirPurifyingPlants() {
        String plantName = "Monstera";
        List<PlantNameDTO> airPurifyingPlants = new ArrayList<>();
        airPurifyingPlants.add(new PlantNameDTO(1, "Monstera"));
        when(plantService.getAirPurifyingPlants(plantName)).thenReturn(airPurifyingPlants);
        ResponseEntity<List<PlantNameDTO>> response = plantController.getAirPuryfyingPlants(plantName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airPurifyingPlants, response.getBody());
        verify(plantService, times(1)).getAirPurifyingPlants(plantName);
    }
    @Test
    public void testGetAirPurifyingPlants_Exception() {
        when(plantService.getAirPurifyingPlants(null)).thenThrow(new RuntimeException("Internal Server Error"));
        ResponseEntity<List<PlantNameDTO>> response = plantController.getAirPuryfyingPlants(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).getAirPurifyingPlants(null);
    }
    @Test
    public void testGetPlantsByQuizAnswers() {
        boolean isToxic = false;
        int sun = 0;
        boolean isAirPurifying = true;
        double matureSize = 2.0;
        int careDifficulty = 1;

        List<PlantNameDTO> matchingPlants = new ArrayList<>();
        matchingPlants.add(new PlantNameDTO(1, "Monstera"));
        when(plantService.getPlantsByQuizAnswers(isToxic, sun, isAirPurifying, matureSize, careDifficulty)).thenReturn(matchingPlants);
        ResponseEntity<List<PlantNameDTO>> response = plantController.getPlantsByQuizAnswers(isToxic, sun, isAirPurifying, matureSize, careDifficulty);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(matchingPlants, response.getBody());
        verify(plantService, times(1)).getPlantsByQuizAnswers(isToxic, sun, isAirPurifying, matureSize, careDifficulty);
    }

    @Test
    public void testGetPlantsByQuizAnswers_Exception() {
        boolean isToxic = true;
        int sun = 1;
        boolean isAirPurifying = false;
        double matureSize = 1.0;
        int careDifficulty = 0;

        when(plantService.getPlantsByQuizAnswers(isToxic, sun, isAirPurifying, matureSize, careDifficulty))
                .thenThrow(new RuntimeException("Internal Server Error"));
        ResponseEntity<List<PlantNameDTO>> response = plantController.getPlantsByQuizAnswers(isToxic, sun, isAirPurifying, matureSize, careDifficulty);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(plantService, times(1)).getPlantsByQuizAnswers(isToxic, sun, isAirPurifying, matureSize, careDifficulty);
    }
}
