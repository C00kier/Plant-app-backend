package com.plantapp.plantapp.user.controller;

import com.plantapp.plantapp.user.model.LoginRequest;
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

    @GetMapping("/{user-id}")
    public ResponseEntity<User> getUserById(@PathVariable("user-id") int userId){
        Optional<User> userOptional = userService.getUserById(userId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("/update-password/{user-id}")
    public void updateUserPassword(@PathVariable("user-id") int userId, @RequestBody String newPassword){
        userService.updateUserPasswordById(userId, newPassword);
    }

    @PutMapping("/update-email/{user-id}")
    public void updateUserEmail(@PathVariable("user-id") int userId, @RequestBody String newEmail){
        userService.updateUserEmailById(userId, newEmail);
    }

    @PutMapping("/update-login/{user-id}")
    public void updateUsername(@PathVariable("user-id") int userId, @RequestBody String newUsername){
        userService.updateUsernameById(userId, newUsername);
    }

    @PutMapping("/update-photo/{user-id}")
    public void updateUserPhoto(@PathVariable("user-id") int userId, @RequestBody String newPhotoUrl){
        userService.updateUserPhotoById(userId, newPhotoUrl);
    }

    @DeleteMapping("/{user-id}")
    public void deleteUser(@PathVariable("user-id") int userId){
        userService.deleteUserById(userId);
    }
}
