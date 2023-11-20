package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUserById(int userId){
         return userRepository.findById(userId);
    }

<<<<<<< HEAD

=======
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
    @Override
    public void deleteUserById(int userId){
        if(getUserById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    @Override
<<<<<<< HEAD
    public void updateUser(int userId, String newPassword, String newEmail, String newLogin, String newPhotoUrl) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (newPassword != null) {
                user.setPassword(newPassword);
=======
    public void updateUser(int userId, String oldPassword, String newPassword, String newEmail, String newNickName, String newPhotoUrl) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (oldPassword != null && newPassword != null) {
                if (passwordEncoder.matches(oldPassword, user.getPassword())){
                    user.setPassword(passwordEncoder.encode(newPassword));
                }
                else {
                    throw new IllegalStateException("Passwords not matching");
                }
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
            }
            if (newEmail != null) {
                user.setEmail(newEmail);
            }
<<<<<<< HEAD
            if (newLogin != null) {
                user.setLogin(newLogin);
=======
            if (newNickName != null) {
                user.setNickName(newNickName);
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
            }
            if (newPhotoUrl != null) {
                user.setPhotoUrl(newPhotoUrl);
            }
            userRepository.save(user);
        }
    }

<<<<<<< HEAD
    public void updateUserEmailById(int userId, String newEmail){
=======
    @Override
    public void changeUserStatus(int userId, boolean newStatus) {
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
<<<<<<< HEAD
            user.setEmail(newEmail);
            userRepository.save(user);
        }
    }

    @Override
    public boolean authenticateUserByEmail(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        return user.filter(value -> Objects.equals(value.getPassword(), password)).isPresent();
    };
    @Override
    public boolean authenticateUserByLogin(String login, String password){
        Optional<User> user = userRepository.findByLogin(login);
        return user.filter(value -> Objects.equals(value.getPassword(), password)).isPresent();
    }

    @Override
    public void changeUserStatus(int userId, boolean newStatus) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
=======
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
            user.setActive(newStatus);
            userRepository.save(user);
        }
    }

}
