package com.plantapp.plantapp.user_game_progress.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import com.plantapp.plantapp.user_game_progress.repository.UserGameProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGameProgressService implements IUserGameProgressService{

    private final UserGameProgressRepository userGameProgressRepository;
@Autowired
    public UserGameProgressService(UserGameProgressRepository userGameProgressRepository) {
        this.userGameProgressRepository = userGameProgressRepository;
    }

    @Override
    public int getUserExperienceByUserId(int userId) {
        return 0;
    }

    @Override
    public void removeUserExperienceByUserId(int userId) {

    }

    @Override
    public int updateUserExperienceByUserId(int userId, int exp) {
        return 0;
    }

    @Override
    public void postUserExperienceByUserId(int userId) {

    }
}
