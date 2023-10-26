package com.plantapp.plantapp.user_game_progress.service;

public interface IUserGameProgressService {
    int getUserExperienceByUserId(int userId);
    void removeUserExperienceByUserId(int userId);
    int updateUserExperienceByUserId(int userId, int exp);
    void postUserExperienceByUserId(int userId);

}
