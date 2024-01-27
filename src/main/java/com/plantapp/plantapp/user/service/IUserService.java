package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.model.UserDTO;

import java.util.Optional;

public interface IUserService {
    void addUser(String email, String password, String login);

    Optional<User> getUserById(int userId);

    UserDTO getUserObjectById(int userId);

    void deleteUserById(int userId);

    void updateUser(int userId, String oldPassword, String newPassword, String newEmail, String newLogin, String newPhotoUrl);

    void updateResetPasswordToken(String token, String email);

    Optional<User> getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);
}
