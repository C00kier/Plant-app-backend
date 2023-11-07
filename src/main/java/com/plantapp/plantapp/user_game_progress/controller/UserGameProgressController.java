package com.plantapp.plantapp.user_game_progress.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-game-progress")
public class UserGameProgressController {

    @GetMapping("/get-exp")
    public int getUserExperienceByUserId(@RequestBody int userId) { return 0; }

    @DeleteMapping("/delete-exp")
    public void removeUserExperienceByUserId(@RequestBody int userId) {}

    @PatchMapping("/update-exp")
    public void updateUserExperienceByUserId(@RequestBody int userId) {}

}
