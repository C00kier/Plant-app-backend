package com.plantapp.plantapp.user_game_progress.controller;

import com.plantapp.plantapp.user_game_progress.service.UserGameProgressService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-game-progress")
public class UserGameProgressController {
    private final UserGameProgressService userGameProgressService;

    public UserGameProgressController(UserGameProgressService userGameProgressService) {
        this.userGameProgressService = userGameProgressService;
    }
    @PostMapping("/post-user-progress")
    public void postUserExperienceByUserId(@RequestBody int userId, @RequestBody int exp){
        userGameProgressService.postUserExperienceByUserId(userId, exp);
    }
    @GetMapping("/get-exp")
    public int getUserExperienceByUserId(@RequestBody int userId) {
      return  userGameProgressService.getUserExperienceByUserId(userId);
    }

    @DeleteMapping("/delete-exp")
    public void removeUserExperienceByUserId(@RequestBody int userId) {
        userGameProgressService.removeUserExperienceByUserId(userId);
    }

    @PatchMapping("/update-exp")
    public void updateUserExperienceByUserId(@RequestBody int userId,@RequestBody int exp) {
        userGameProgressService.updateUserExperienceByUserId(userId, exp);
    }

}
