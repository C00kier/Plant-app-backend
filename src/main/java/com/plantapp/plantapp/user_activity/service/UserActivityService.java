package com.plantapp.plantapp.user_activity.service;


import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.model.UserActivity;
import com.plantapp.plantapp.user_activity.repository.UserActivityRepository;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import com.plantapp.plantapp.user_plant.repository.UserPlantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserActivityService implements IUserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final UserPlantRepository userPlantRepository;

    @Autowired
    public UserActivityService(UserActivityRepository userActivityRepository, UserRepository userRepository, PlantRepository plantRepository, UserPlantRepository userPlantRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
        this.userPlantRepository = userPlantRepository;
    }

    @Override
    public void addPlantActivity(int userPlantId, ActivityType activityType) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            UserActivity userActivity = new UserActivity();
            userActivity.setUser(userPlant.getUser());
            userActivity.setUserPlant(userPlant);
            userActivity.setActivityType(activityType);
            userActivityRepository.save(userActivity);
        }
    }

    @Override
    public List<UserActivity> getAllUsersLogActivity() {
        return userActivityRepository.findAll();
    }

@Override
@Transactional
    public void deleteUserActivitiesByUserPlantId(int userPlantId) {
    Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
    if (optionalUserPlant.isPresent()) {
        UserPlant userPlant = optionalUserPlant.get();
        userActivityRepository.deleteByUserPlant(userPlant);
    }
    }
}
