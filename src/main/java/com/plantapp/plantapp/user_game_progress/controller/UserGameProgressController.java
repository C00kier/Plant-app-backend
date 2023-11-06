package com.plantapp.plantapp.user_game_progress.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-game-progress")
public class UserGameProgressController {

    @GetMapping("/get-exp")
    public ResponseEntity<Integer> getUserExperienceByUserId(@RequestBody int userId) { return ResponseEntity.ok().build(); }

    @DeleteMapping("/delete-exp")
    public void removeUserExperienceByUserId(@RequestBody int userId) {}

    @PatchMapping("/update-exp")
    public void updateUserExperienceByUserId(@RequestBody int userId) {}
}
