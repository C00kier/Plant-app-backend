package com.plantapp.plantapp.user_game_progress.service;


import com.plantapp.plantapp.user_game_progress.model.UserGameTitle;

public interface IUserGameProgressService {

    void createUserGameProgressByEmail(String email);
    int getUserExperienceByUserId(int userId);
    void removeUserExperienceByUserId(int userId);
    int updateUserExperienceByUserId(int userId, int exp);
    String getUserGameTitleByUserId(int userId);
    UserGameTitle updateUserGameTitleByUserId(int userId);
    UserGameTitle findGameProgressTitleByExperience(int userExperience);
    int calculatePointLeftToNextLvl(int userExperience);
}
