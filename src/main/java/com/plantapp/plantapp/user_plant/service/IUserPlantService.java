package com.plantapp.plantapp.user_plant.service;

import com.plantapp.plantapp.user_plant.model.UserPlant;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface IUserPlantService {
    List<UserPlant> getUserPlantsByUserId(int userId);
    void addPlantToUserPlantsById(UserPlant userPlant);

    void removePlantFromUserPlantsById(int userPlantId);
    void updateUserPlantRoomById(int userPlantId, String roomName);
    void updateUserPlantAliasById(int userPlantId, String alias);
    void updateLastWateringByUserPlantId(int userPlantId);
    void updateLastPropagateByUserPlantId(int userPlantId);
    void updateLastRepotByUserPlantId(int userPlantId);
    void updateLastFertilizerByUserPlantId(int userPlantId);

    void updateLastPrunedByUserPlantId(int userPlantId);
}
