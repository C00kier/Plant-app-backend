package com.plantapp.plantapp.plant.service;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService implements IPlantService {
    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    @Override
    public Optional<Plant> getPlantById(int plantId) {
        return plantRepository.findById(plantId);
    }

    @Override
    @Transactional
    public List<Plant> getPlantsByName(String plantName) {
        String likeExpression = "%" + plantName + "%";
        return plantRepository.findByBotanicalNameLikeOrCommonNameLike(likeExpression, likeExpression);
    }

    @Override
    public Plant addNewPlant(double matureSize, boolean toxicity, boolean airPurifying,
                                       int repotting, int fertilizer, int sun, int water,
                                       int careDifficulty, String botanicalName, String commonName,
                                       String translation, String plantOverview, String nativeArea,
                                       String plantType, String careDescription, String waterExtended,
                                       String sunExtended, String temperature, String humidity,
                                       String fertilizerExtended, String bloomTime, String repottingExtended,
                                       String soilType, String soilPh, String propagating, String pestsAndDiseases,
                                       String pruning) {
        return plantRepository.save(new Plant(matureSize, toxicity, airPurifying, repotting, fertilizer, sun,
                water, careDifficulty, botanicalName, commonName, translation, plantOverview, nativeArea, plantType, careDescription,
                waterExtended, sunExtended, temperature, humidity, fertilizerExtended, bloomTime, repottingExtended, soilType, soilPh,
                propagating, pestsAndDiseases, pruning));
    }

    @Override
    public Optional<Plant> deletePlantById(int plantId) {
        return plantRepository.deleteById(plantId);
    }
}
