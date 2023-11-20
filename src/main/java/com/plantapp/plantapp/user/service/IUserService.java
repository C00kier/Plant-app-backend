package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> getUserById(int userId);

    void deleteUserById(int userId);

<<<<<<< HEAD
    void updateUser(int userId, String newPassword, String newEmail, String newLogin, String newPhotoUrl);

    boolean authenticateUserByEmail(String email, String password);

    boolean authenticateUserByLogin(String email, String password);

    void changeUserStatus(int userId, boolean newStatus);
=======
    void updateUser(int userId, String oldPassword, String newPassword, String newEmail, String newLogin, String newPhotoUrl);

    void changeUserStatus(int userId, boolean newStatus);

>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
}
