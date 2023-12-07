package com.plantapp.plantapp.user_activity.repository;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.model.UserActivity;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {

    Optional<UserActivity> findByUser(User user);
    void deleteByUserPlant(UserPlant userPlant);

    @Query("SELECT MAX(ua.activityDate) FROM UserActivity ua " +
            "WHERE ua.userPlant.plant = :plant " +
            "AND ua.activityType = :activityType " +
            "AND ua.activityDate < :currentDate")
    LocalDate findPreviousLogDate(
            @Param("plant") Plant plant,
            @Param("activityType") ActivityType activityType,
            @Param("currentDate") LocalDate currentDate
    );

    Optional<List<UserActivity>> findByUserAndActivityType(User user, ActivityType activityType);

    Optional<List<UserActivity>> findByUserAndActivityTypeAndActivityDateAfter(User user, ActivityType activityType, LocalDate sixDaysAgo);
}
