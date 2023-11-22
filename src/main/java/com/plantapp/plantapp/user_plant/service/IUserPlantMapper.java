package com.plantapp.plantapp.user_plant.service;

import com.plantapp.plantapp.user_plant.model.UserPlantDTO;
import com.plantapp.plantapp.user_plant.model.UserPlant;

import java.util.List;

public interface IUserPlantMapper {
    List<UserPlantDTO> getShorterUserPlantInfo(List<UserPlant> userPlants);
}
