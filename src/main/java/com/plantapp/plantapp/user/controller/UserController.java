package com.plantapp.plantapp.user.controller;

<<<<<<< HEAD
import com.plantapp.plantapp.user.model.LoginRequest;
=======
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
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

<<<<<<< HEAD
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.addUser(user.getEmail(), user.getPassword(), user.getLogin());
    }

=======
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
    @GetMapping()
    public ResponseEntity<User> getUserById(@RequestBody int userId){
        Optional<User> userOptional = userService.getUserById(userId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
             @RequestBody UpdateRequestDTO updateRequest) {
        int userId = updateRequest.getUserId();
<<<<<<< HEAD
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
        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        if ((email != null && userService.authenticateUserByEmail(email, password)) ||
                (login != null && userService.authenticateUserByLogin(login, password))) {

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



=======
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


>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody int userId){
        userService.deleteUserById(userId);
    }
}
