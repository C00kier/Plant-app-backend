package com.plantapp.plantapp.user.controller;

import com.plantapp.plantapp.user.model.UpdateRequestDTO;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> getUserById(@RequestBody int userId){
        Optional<User> userOptional = userService.getUserById(userId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("/change-user-status")
    public ResponseEntity<String> changeUserStatus(
           @RequestBody User user,
            @RequestParam("isActive") boolean isActive) {
        userService.changeUserStatus(user.getUserId(), isActive);
        return ResponseEntity.ok("User status updated successfully");
    }


    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody int userId){
        userService.deleteUserById(userId);
    }
}
