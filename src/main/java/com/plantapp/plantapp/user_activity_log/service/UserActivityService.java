package com.plantapp.plantapp.user_activity_log.service;


import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity_log.model.ActivityType;
import com.plantapp.plantapp.user_activity_log.model.UserActivity;
import com.plantapp.plantapp.user_activity_log.repository.UserActivityRepository;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        userActivity.setTiming(calculateTiming(userPlant.getPlant(),activityType));
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

    private String calculateTiming(Plant plant, ActivityType activityType) {
        LocalDate today = LocalDate.now();
        LocalDate previousLogDate = userActivityRepository.findPreviousLogDate(plant, activityType, today);

        if (previousLogDate != null) {
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(previousLogDate, today);

            switch (activityType) {
                case WATERING_PLANT:
                    int waterInterval = plant.getWater();
                    if (daysBetween == waterInterval) {
                        return "on-time";
                    } else if (daysBetween > waterInterval){
                        return "delay";
                    }


                case FERTILIZING_PLANT:
                    int fertilizerInterval = plant.getFertilizer() * 30;
                    if (daysBetween <= fertilizerInterval) {
                        return "on-time";
                    } else {
                        return "delay";
                    }

                case REPOTING_PLANT:
                    int repottingInterval = getRepottingInterval(plant);
                    if (daysBetween <= repottingInterval) {
                        return "on-time";
                    } else {
                        return "delay";
                    }


                default:
                    return "irrelevant";
            }
        } else {
            return "irrelevant";
        }
    }
    private int getRepottingInterval(Plant plant) {
        switch (plant.getRepotting()) {
            case 1:
                return 365;
            case 2:
                return 730;
            case 3:
                return 1095;
            default:
                return 0;
        }
    }

}
