package com.plantapp.plantapp.user_game_progress.controller;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.service.UserActivityService;
import com.plantapp.plantapp.user_game_progress.model.UserProgressRequestDTO;
import com.plantapp.plantapp.user_game_progress.service.UserGameProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-game-progress")
public class UserGameProgressController {
    private final UserGameProgressService userGameProgressService;
    private final UserActivityService userActivityService;
    private final UserRepository userRepository;

    public UserGameProgressController(UserGameProgressService userGameProgressService, UserActivityService userActivityService, UserRepository userRepository) {
        this.userGameProgressService = userGameProgressService;
        this.userActivityService = userActivityService;
        this.userRepository = userRepository;
    }
    @PostMapping("/post-user-progress")
    public void postUserExperienceByUserId(@RequestBody UserProgressRequestDTO userProgressRequestDTO){
        userGameProgressService.postUserExperienceByUserId(userProgressRequestDTO.getUserId(), userProgressRequestDTO.getExp());
    }
    @GetMapping("/get-exp")
    public ResponseEntity<Integer> getUserExperienceByUserId(@RequestParam int userId) {
        int experience = userGameProgressService.getUserExperienceByUserId(userId);
        if (experience != 0) {
            return ResponseEntity.ok(experience);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-game-title")
    public ResponseEntity<String> getUserGameTitleByUserId(@RequestParam int userId) {
        String title = userGameProgressService.getUserGameTitleByUserId(userId);
        if (title != null) {
            return ResponseEntity.ok(title);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-exp-left")
    public ResponseEntity<Integer> getUserExperienceLeftToNextLvlByUserId(@RequestParam int userId) {
        int exp = userGameProgressService.getUserExperienceByUserId(userId);
        int differance = userGameProgressService.calculatePointLeftToNextLvl(exp);
        if (differance != 0) {
            return ResponseEntity.ok(differance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-exp")
    public void removeUserExperienceByUserId(@RequestBody User user) {
        userGameProgressService.removeUserExperienceByUserId(user.getUserId());
    }

    @PatchMapping("/update-exp")
    public void updateUserExperienceByUserId(@RequestBody UserProgressRequestDTO userProgressRequestDTO) {
        userGameProgressService.updateUserExperienceByUserId(userProgressRequestDTO.getUserId(), userProgressRequestDTO.getExp());
        userGameProgressService.updateUserGameTitleByUserId(userProgressRequestDTO.getUserId());
    }

    @PatchMapping("/award-for-weekly-watering")
    public void awardForWeeklyWatering(@RequestBody UserProgressRequestDTO userProgressRequestDTO) {
        int userId = userProgressRequestDTO.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (userActivityService.areAllUserActivitiesOnTime(user, ActivityType.WATERING_PLANT, 7)) {
            userGameProgressService.updateUserExperienceByUserId(userProgressRequestDTO.getUserId(), userProgressRequestDTO.getExp());
            userGameProgressService.updateUserGameTitleByUserId(userProgressRequestDTO.getUserId());
        }
    }

    @PatchMapping("/award-for-monthly-fertilizing")
    public void awardForMonthlyFertilizing(@RequestBody UserProgressRequestDTO userProgressRequestDTO) {
        int userId = userProgressRequestDTO.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (userActivityService.areAllUserActivitiesOnTime(user, ActivityType.FERTILIZING_PLANT, 32)) {
            userGameProgressService.updateUserExperienceByUserId(userProgressRequestDTO.getUserId(), userProgressRequestDTO.getExp());
            userGameProgressService.updateUserGameTitleByUserId(userProgressRequestDTO.getUserId());
        }
    }

    @PatchMapping("/award-for-yearly-repotting")
    public void awardForYearlyRepotting(@RequestBody UserProgressRequestDTO userProgressRequestDTO) {
        int userId = userProgressRequestDTO.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (userActivityService.areAllUserActivitiesOnTime(user, ActivityType.REPOTTING_PLANT, 365)) {
            userGameProgressService.updateUserExperienceByUserId(userProgressRequestDTO.getUserId(), userProgressRequestDTO.getExp());
            userGameProgressService.updateUserGameTitleByUserId(userProgressRequestDTO.getUserId());
        }
    }
}