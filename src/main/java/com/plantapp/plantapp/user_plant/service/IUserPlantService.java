package com.plantapp.plantapp.user_plant.service;

import com.plantapp.plantapp.user_plant.model.UserPlant;

import java.util.Date;
import java.util.List;

public interface IUserPlantService {
    List<UserPlant> getUserPlantsByUserId(int userId);
    void addPlantToUserPlants(UserPlant userPlant);

    void removePlantFromUserPlantsById(int userPlantId);
    void updateUserPlantRoomById(int userPlantId, String roomName);
    void updateUserPlantAliasById(int userPlantId, String alias);
    void updateLastWateredByUserPlantId(int userPlantId);
    void updateLastPropagatedByUserPlantId(int userPlantId);
    void updateLastRepottedByUserPlantId(int userPlantId);
    void updateLastFertilizedByUserPlantId(int userPlantId);

    void updateLastPrunedByUserPlantId(int userPlantId);
}
