package com.plantapp.plantapp.user.controller;

import com.plantapp.plantapp.user.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/create")
    public void createUser(){}

    @GetMapping("/{user-id}")
    public User getUserById(@PathVariable("user-id") int userId){
    return null;
    }

    @PutMapping("/{user-id}")
    public User updateUser(@PathVariable("user-id") int userId){
        return null;
    }

    @DeleteMapping("/{user-id}")
    public void deleteUser(@PathVariable("user-id") int userId){}
}
