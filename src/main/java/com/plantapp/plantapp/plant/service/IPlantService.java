package com.plantapp.plantapp.plant.service;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.model.PlantNameDTO;

import java.util.List;
import java.util.Optional;

public interface IPlantService {

    List<Plant> getAllPlants();
    Optional<Plant> getPlantById(int plantId);

    List<PlantNameDTO> getPlantsByName(String plantName);

    Plant addNewPlant(Plant plant);

    void deletePlantById(int plantId);

    Plant changePlantById(int plantId, Plant plant);
    List<Plant> getPlantsBySunIntensity(int sun);
    List<Plant> getPlantsByDifficulty(int difficulty);
    List<Plant> getAirPuryfyingPlants();
    List<Plant> getNonToxicPlants();
}
