package com.plantapp.plantapp.user_plants.service;

import com.plantapp.plantapp.user_plants.model.UserPlants;

import java.util.List;

public interface IUserPlantsService {
    List<UserPlants> getUserPlantsByUserId(int userId);
    void addPlantToUserPlantsById(int userId, int plantId);
    void removePlantFromUserPlantsById(int userPlantId);
    void updateUserPlantRoomById(int userPlantId, String roomName);
    void updateUserPlantAliasById(int userPlantId, String alias);
}
