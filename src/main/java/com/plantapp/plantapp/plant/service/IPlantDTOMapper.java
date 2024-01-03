package com.plantapp.plantapp.plant.service;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.model.PlantNameDTO;

import java.util.List;

public interface IPlantDTOMapper {
    List<PlantNameDTO> getShorterPlant(List<Plant> plants);
}
