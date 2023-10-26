package com.plantapp.plantapp.user_game_progress.repository;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGameProgressRepository extends JpaRepository<UserGameProgress, Integer> {

    Optional<UserGameProgress> findByUser(User user);
}
