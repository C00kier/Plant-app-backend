package com.plantapp.plantapp.user_plant.service;

import com.plantapp.plantapp.user_plant.model.UserPlantDTO;
import com.plantapp.plantapp.user_plant.model.UserPlant;

import java.util.List;
import java.util.stream.Collectors;

public class UserPlantMapper implements IUserPlantMapper {
    @Override
    public List<UserPlantDTO> getShorterUserPlantInfo(List<UserPlant> userPlants) {
        return  userPlants.stream()
                .map(userPlant -> new UserPlantDTO(userPlant.getUserPlantId(),userPlant.getPlant().getBotanicalName(), userPlant.getRoom(), userPlant.getAlias()))
                .collect(Collectors.toList());
    }
}
