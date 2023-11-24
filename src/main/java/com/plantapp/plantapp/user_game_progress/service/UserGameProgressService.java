package com.plantapp.plantapp.user_game_progress.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import com.plantapp.plantapp.user_game_progress.repository.UserGameProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGameProgressService implements IUserGameProgressService{

    private final UserGameProgressRepository userGameProgressRepository;
    private final UserRepository userRepository;
@Autowired
    public UserGameProgressService(UserGameProgressRepository userGameProgressRepository, UserRepository userRepository) {
        this.userGameProgressRepository = userGameProgressRepository;
    this.userRepository = userRepository;
}

    @Override
    public void postUserExperienceByUserId(int userId, int exp) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            userGameProgressRepository.save(new UserGameProgress(exp, optionalUser.get()));
        }
    }

    @Override
    public int getUserExperienceByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());

            if (optionalUserGameProgress.isPresent()) {
                return optionalUserGameProgress.get().getExperience();
            }
        }
        return 0;
    }

    @Override
    public void removeUserExperienceByUserId(int userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if(optionalUser.isPresent()){
        Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());
        userGameProgressRepository.delete(optionalUserGameProgress.get());
     }
    }

    @Override
    public int updateUserExperienceByUserId(int userId, int exp) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if(optionalUser.isPresent()){
        Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());
        int userExperience = optionalUserGameProgress.get().getExperience();
        return userExperience + exp;
    }
        return 0;
    }

    @Override
    public int awardForWeeklyWatering(int userId, int exp) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());
            int userExperience = optionalUserGameProgress.get().getExperience();
            return userExperience + 15;
        }
        return 0;
    }


}
