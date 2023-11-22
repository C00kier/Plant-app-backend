package com.plantapp.plantapp.user_activity_log.repository;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_activity_log.model.UserActivity;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    Optional<UserActivity> findByUser(User user);
    void deleteByUserPlant(UserPlant userPlant);
}
