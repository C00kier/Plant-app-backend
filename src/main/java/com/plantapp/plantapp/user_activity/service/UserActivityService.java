package com.plantapp.plantapp.user_activity.service;


import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_activity.repository.UserActivityRepository
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.model.UserActivity;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import com.plantapp.plantapp.user_plant.repository.UserPlantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserActivityService implements IUserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserPlantRepository userPlantRepository;

    @Autowired
    public UserActivityService(UserActivityRepository userActivityRepository, UserPlantRepository userPlantRepository, UserRepository userRepository, PlantRepository plantRepository) {
        this.userActivityRepository = userActivityRepository;
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
            userActivity.setTiming(calculateTiming(userPlantRepository.findById(userPlantId).get().getPlant(), activityType));
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

    @Override
    public boolean areAllUserActivitiesOnTime(User user, ActivityType activityType) {
        LocalDate sixDaysAgo = LocalDate.now().minusDays(6);
        Optional<List<UserActivity>> userActivitiesOptional = userActivityRepository
                .findByUserAndActivityTypeAndActivityDateAfter(user, activityType, sixDaysAgo);
        if(userActivitiesOptional.isPresent()){
            List<UserActivity> userActivities = userActivitiesOptional.get();
            return userActivities.stream()
                    .allMatch(activity -> "on-time".equals(activity.getTiming()));
        }
            return false;
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
                case REPOTTING_PLANT:
                    int repottingInterval = getRepottingInterval(plant);
                    if (daysBetween <= repottingInterval) {
                        return "on-time";
                    } else {
                        return "delay";
                    }
                default:
                    return "default";
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
