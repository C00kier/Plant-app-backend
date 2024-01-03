package com.plantapp.plantapp.user_game_progress.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_game_progress.model.UserGameTitle;
import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import com.plantapp.plantapp.user_game_progress.repository.UserGameProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserGameProgressService implements IUserGameProgressService {

    private final UserGameProgressRepository userGameProgressRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserGameProgressService(UserGameProgressRepository userGameProgressRepository, UserRepository userRepository) {
        this.userGameProgressRepository = userGameProgressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createUserGameProgressByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(value -> userGameProgressRepository.save(new UserGameProgress(0, value)));
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
    public String getUserGameTitleByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());

            if (optionalUserGameProgress.isPresent()) {
                return optionalUserGameProgress.get().getGAME_TITLE().name();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void removeUserExperienceByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());
            optionalUserGameProgress.ifPresent(userGameProgressRepository::delete);
        }
    }

    @Override
    public int updateUserExperienceByUserId(int userId, int exp) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());
            if (optionalUserGameProgress.isPresent()) {
                updateUserGameTitleByUserId(userId);
                UserGameProgress userGameProgress = optionalUserGameProgress.get();
                int userExperience = userGameProgress.getExperience();
                int updatedExperience = userExperience + exp;
                userGameProgress.setExperience(updatedExperience);
                userGameProgressRepository.save(userGameProgress);
                return updatedExperience;
            }
        }

        return 0;
    }

    @Override
    public UserGameTitle updateUserGameTitleByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            System.out.println("Usertest");
            Optional<UserGameProgress> optionalUserGameProgress = userGameProgressRepository.findByUser(optionalUser.get());
            if (optionalUserGameProgress.isPresent()) {
                UserGameProgress userGameProgress = optionalUserGameProgress.get();
                int userExperience = userGameProgress.getExperience();
                UserGameTitle userGameTitle = findGameProgressTitleByExperience(userExperience);
                System.out.println("Enum:"+userGameTitle.name());

                userGameProgress.setGAME_TITLE(userGameTitle);
                userGameProgressRepository.save(userGameProgress);
                System.out.println("User"+optionalUser.get());
                return userGameTitle;
            }
        }
        return null;
    }
    @Override
    public UserGameTitle findGameProgressTitleByExperience(int userExperience) {
        for (UserGameTitle title : UserGameTitle.values()){
            if( userExperience >= title.getMIN_EXPERIENCE() && userExperience <= title.getMAX_EXPERIENCE()){
                return title;
            }
        }
        return UserGameTitle.POCZĄTKUJĄCY_ZIELONOSKÓRNIK;
    }
    @Override
    public int calculatePointLeftToNextLvl(int userExperience) {
        for (UserGameTitle title : UserGameTitle.values()){
            if( userExperience >= title.getMIN_EXPERIENCE() && userExperience <= title.getMAX_EXPERIENCE()){
                return title.getMAX_EXPERIENCE()-userExperience +1;
            }
        }
        return 0;
    }

}
