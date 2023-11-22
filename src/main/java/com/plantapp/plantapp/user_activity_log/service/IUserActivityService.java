package com.plantapp.plantapp.user_activity_log.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_activity_log.model.ActivityType;
import com.plantapp.plantapp.user_activity_log.model.UserActivity;
import com.plantapp.plantapp.user_plant.model.UserPlant;

import java.util.List;

public interface IUserActivityService {

    void addPlantActivity(User user, UserPlant userPlant, ActivityType activityType);
    List<UserActivity> getAllUsersLogActivity();
//    void removePlantActivity(int userPlantId);
    void deleteUserActivitiesByUserPlant(UserPlant userPlant);
}
