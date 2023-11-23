package com.plantapp.plantapp.user.controller;

import com.plantapp.plantapp.user.model.UpdateRequestDTO;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.model.UserDTO;
import com.plantapp.plantapp.user.service.UserService;
import com.plantapp.plantapp.user_game_progress.service.UserGameProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final UserGameProgressService userGameProgressService;


    @Autowired
    public UserController(UserService userService, UserGameProgressService userGameProgressService){
        this.userService = userService;
        this.userGameProgressService = userGameProgressService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> getUserById(@RequestBody User user){
        UserDTO userDTO = userService.getUserObjectById(user.getUserId());
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
             @RequestBody UpdateRequestDTO updateRequest) {
        int userId = updateRequest.getUserId();
        String oldPassword =updateRequest.getOldPassword();
        String newPassword = updateRequest.getNewPassword();
        String newEmail = updateRequest.getNewEmail();
        String newNickName = updateRequest.getNewNickName();
        String newPhotoUrl = updateRequest.getNewPhotoUrl();

        if (oldPassword == null && newPassword == null && newEmail == null && newNickName == null && newPhotoUrl == null) {
            return ResponseEntity.badRequest().body("No updates provided.");
        }

        userService.updateUser(userId, oldPassword, newPassword, newEmail, newNickName, newPhotoUrl);
        return ResponseEntity.ok("User updated successfully");
    }


    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody int userId){
        userGameProgressService.removeUserExperienceByUserId(userId);
        userService.deleteUserById(userId);
    }
}
