package com.plantapp.plantapp.user_game_progress.repository;

import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameProgressRepository extends JpaRepository<UserGameProgress, Integer> {
}
