package com.plantapp.plantapp.user_game_progress.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-game-progress")
public class UserGameProgressController {

    @GetMapping("/{user-id}")
    public int getUserExperienceByUserId(@PathVariable("user-id") int userId) { return 0; }

    @DeleteMapping("/user-id")
    public void removeUserExperienceByUserId(@PathVariable("user-id") int userId) {}

    @PatchMapping("/{user-id}")
    public void updateUserExperienceByUserId(@PathVariable("user-id") int userId) {}

}
