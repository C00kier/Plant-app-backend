package com.plantapp.plantapp.user.controller;

import com.plantapp.plantapp.user.model.LoginRequest;
import com.plantapp.plantapp.user.model.UpdateRequestDTO;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.addUser(user.getEmail(), user.getPassword(), user.getUsername());
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
        String newPassword = updateRequest.getNewPassword();
        String newEmail = updateRequest.getNewEmail();
        String newLogin = updateRequest.getNewLogin();
        String newPhotoUrl = updateRequest.getNewPhotoUrl();

        if (newPassword == null && newEmail == null && newLogin == null && newPhotoUrl == null) {
            return ResponseEntity.badRequest().body("No updates provided.");
        }

        userService.updateUser(userId, newPassword, newEmail, newLogin, newPhotoUrl);
        return ResponseEntity.ok("User updated successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if ((email != null && userService.authenticateUserByEmail(email, password)) ||
                (username != null && userService.authenticateUserByUsername(username, password))) {

            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. Invalid credentials.");
        }
    }

    @PutMapping("/change-user-status")
    public ResponseEntity<String> changeUserStatus(
           @RequestBody int userId,
            @RequestParam("isActive") boolean isActive) {
        userService.changeUserStatus(userId, isActive);
        return ResponseEntity.ok("User status updated successfully");
    }

    @PutMapping("/update-email/{user-id}")
    public void updateUserEmail(@PathVariable("user-id") int userId, @RequestBody String newEmail){
        userService.updateUserEmailById(userId, newEmail);
    }

    @PutMapping("/update-login/{user-id}")
    public void updateUsername(@PathVariable("user-id") int userId, @RequestBody String newUsername){
        userService.updateUsernameById(userId, newUsername);
    }


    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody int userId){
        userService.deleteUserById(userId);
    }
}
