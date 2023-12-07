package com.plantapp.plantapp.user_plant.service;

import com.plantapp.plantapp.user_plant.model.UserPlant;

import java.util.Date;
import java.util.List;

public interface IUserPlantService {
    List<UserPlant> getUserPlantsByUserId(int userId);
    void addPlantToUserPlants(UserPlant userPlant);

    void removePlantFromUserPlantsById(int userPlantId);

    void removeRoomFromPlantById(int userPlantId);
    void updateUserPlantRoomById(int userPlantId, String roomName);
    void updateUserPlantAliasById(int userPlantId, String alias);
    void updateLastWateredByUserPlantId(int userPlantId, Date date);
    void updateLastPropagatedByUserPlantId(int userPlantId);
    void updateLastRepottedByUserPlantId(int userPlantId, Date date);
    void updateLastFertilizedByUserPlantId(int userPlantId, Date date);

    void updateLastPrunedByUserPlantId(int userPlantId);
}
