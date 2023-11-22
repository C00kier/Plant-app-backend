package com.plantapp.plantapp.user_activity_log.service;


import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity_log.model.ActivityType;
import com.plantapp.plantapp.user_activity_log.model.UserActivity;
import com.plantapp.plantapp.user_activity_log.repository.UserActivityRepository;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityService implements IUserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    @Autowired
    public UserActivityService(UserActivityRepository userActivityRepository, UserRepository userRepository, PlantRepository plantRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
    }

    @Override
    public void addPlantActivity(User user, UserPlant userPlant, ActivityType activityType) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setUserPlant(userPlant);
        userActivity.setActivityType(activityType);
        userActivityRepository.save(userActivity);
    }

    @Override
    public List<UserActivity> getAllUsersLogActivity() {
        return userActivityRepository.findAll();
    }

@Override
    public void deleteUserActivitiesByUserPlant(UserPlant userPlant) {
        userActivityRepository.deleteByUserPlant(userPlant);
    }

}
