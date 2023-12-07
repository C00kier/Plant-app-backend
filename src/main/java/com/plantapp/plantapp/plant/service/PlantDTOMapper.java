package com.plantapp.plantapp.plant.service;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.model.PlantNameDTO;
import com.plantapp.plantapp.plant.model.PlantRecommendationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantDTOMapper implements IPlantDTOMapper {
    @Override
    public List<PlantNameDTO> getShorterPlant(List<Plant> plants) {
        return  plants.stream()
                            .filter(plant -> "PL".equals(plant.getTranslation()))
                            .map(plant -> new PlantNameDTO(plant.getPlantId(), plant.getBotanicalName(),plant.getPicture()))
                            .collect(Collectors.toList());
    }
}
