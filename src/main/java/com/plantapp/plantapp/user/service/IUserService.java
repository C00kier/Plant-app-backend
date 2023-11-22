package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.model.UserDTO;

import java.util.Optional;

public interface IUserService {

    Optional<User> getUserById(int userId);

    UserDTO getUserObjectById(int userId);

    void deleteUserById(int userId);

    void updateUser(int userId, String oldPassword, String newPassword, String newEmail, String newLogin, String newPhotoUrl);

}
