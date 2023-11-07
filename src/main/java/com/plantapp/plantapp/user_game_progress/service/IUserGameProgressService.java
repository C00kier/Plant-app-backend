package com.plantapp.plantapp.user_game_progress.service;

public interface IUserGameProgressService {

    void postUserExperienceByUserId(int userId, int exp);
    int getUserExperienceByUserId(int userId);
    void removeUserExperienceByUserId(int userId);
    int updateUserExperienceByUserId(int userId, int exp);

}
