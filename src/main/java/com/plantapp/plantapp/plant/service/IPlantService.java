package com.plantapp.plantapp.plant.service;

import com.plantapp.plantapp.plant.model.Plant;

import java.util.List;
import java.util.Optional;

public interface IPlantService {

    List<Plant> getAllPlants();
    Optional<Plant> getPlantById(int plantId);

    List<Plant> getPlantsByName(String plantName);

    Plant addNewPlant(double matureSize, boolean toxicity, boolean airPurifying,
                                int repotting, int fertilizer, int sun, int water,
                                int careDifficulty, String botanicalName, String commonName,
                                String translation, String plantOverview, String nativeArea,
                                String plantType, String careDescription, String waterExtended,
                                String sunExtended, String temperature, String humidity,
                                String fertilizerExtended, String bloomTime, String repottingExtended,
                                String soilType, String soilPh, String propagating, String pestsAndDiseases,
                                String pruning);

    Optional<Plant> deletePlantById(int plantId);
}
