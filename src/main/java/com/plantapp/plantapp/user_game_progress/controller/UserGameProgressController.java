package com.plantapp.plantapp.user_game_progress.controller;

<<<<<<< HEAD
=======
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_game_progress.model.UserProgressRequestDTO;
import com.plantapp.plantapp.user_game_progress.service.UserGameProgressService;

>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-game-progress")
public class UserGameProgressController {
    private final UserGameProgressService userGameProgressService;

<<<<<<< HEAD
    @GetMapping("/get-exp")
    public ResponseEntity<Integer> getUserExperienceByUserId(@RequestBody int userId) { return ResponseEntity.ok().build(); }

    @DeleteMapping("/delete-exp")
    public void removeUserExperienceByUserId(@RequestBody int userId) {}

    @PatchMapping("/update-exp")
    public void updateUserExperienceByUserId(@RequestBody int userId) {}
=======
    public UserGameProgressController(UserGameProgressService userGameProgressService) {
        this.userGameProgressService = userGameProgressService;
    }
    @PostMapping("/post-user-progress")
    public void postUserExperienceByUserId(@RequestBody UserProgressRequestDTO userProgressRequestDTO){
        userGameProgressService.postUserExperienceByUserId(userProgressRequestDTO.getUserId(), userProgressRequestDTO.getExp());
    }
    @GetMapping("/get-exp")
    public ResponseEntity<Integer> getUserExperienceByUserId(@RequestBody User user) {
        int experience = userGameProgressService.getUserExperienceByUserId(user.getUserId());
        if (experience != 0) {
            return ResponseEntity.ok(experience);
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
    }

>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
}
