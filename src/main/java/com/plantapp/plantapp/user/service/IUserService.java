package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;

import java.util.Optional;

public interface IUserService {
    void addUser(String email, String password, String login);

    Optional<User> getUserById(int userId);

    void deleteUserById(int userId);

    void updateUser(int userId, String newPassword, String newEmail, String newLogin, String newPhotoUrl);

    boolean authenticateUserByEmail(String email, String password);

    boolean authenticateUserByLogin(String email, String password);

    void changeUserStatus(int userId, boolean newStatus);
}
