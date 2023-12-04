package com.plantapp.plantapp.user_activity.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.model.UserActivity;

import java.util.List;

public interface IUserActivityService {

    void addPlantActivity(int userPlantId, ActivityType activityType);
    List<UserActivity> getAllUsersLogActivity();

    void deleteUserActivitiesByUserPlantId(int userPlantId);
    boolean areAllUserActivitiesOnTime(User user, ActivityType activityType, long daysToCheck);
}
