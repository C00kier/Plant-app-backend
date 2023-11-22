package com.plantapp.plantapp.user_activity_log.service;


import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity_log.model.ActivityType;
import com.plantapp.plantapp.user_activity_log.model.UserActivityLog;
import com.plantapp.plantapp.user_activity_log.repository.UserActivityRepository;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserActivityLogService implements IUserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    @Autowired
    public UserActivityLogService(UserActivityRepository userActivityRepository, UserRepository userRepository, PlantRepository plantRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
    }

    @Override
    public void addPlantActivity(User user, UserPlant userPlant, ActivityType activityType) {
        UserActivityLog userActivityLog = new UserActivityLog();
        userActivityLog.setUser(user);
        userActivityLog.setUserPlant(userPlant);
        userActivityLog.setActivityType(activityType);
        userActivityRepository.save(userActivityLog);
    }

    @Override
    public List<UserActivityLog> getAllUsersLogActivity() {
        return userActivityRepository.findAll();
    }

    @Override
    public void removePlantActivity(int userPlantId) {
       Optional<UserActivityLog> activityLog =  userActivityRepository.findByUserPlantId(userPlantId);
       userActivityRepository.delete(activityLog);
    }


}
