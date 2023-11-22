package com.plantapp.plantapp.user_activity_log.repository;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_activity_log.model.UserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivityLog, Integer> {
    Optional<UserActivityLog> findByUser(User user);
}
