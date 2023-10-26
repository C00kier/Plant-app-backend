package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;

import java.util.Optional;

public interface IUserService {
    void addUser(String email, String password, String login);

    Optional<User> getUserById(int userId);

    void deleteUserById(int userId);

    void updateUserPasswordById(int userId, String newPassword);

    void updateUserEmailById(int userId, String newEmail);

    void updateUserLoginById(int userId, String newLogin);

    void updateUserPhotoById(int userId, String newPhotoUrl);

    boolean authenticateUserByEmail(String email, String password);

    boolean authenticateUserByLogin(String email, String password);
}
