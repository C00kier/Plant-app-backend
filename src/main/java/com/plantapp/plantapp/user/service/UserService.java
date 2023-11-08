package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void addUser(String email, String password, String username){
        userRepository.save(new User(email, password, username));
    }
    @Override
    public Optional<User> getUserById(int userId){
         return userRepository.findById(userId);
    }


    @Override
    public void deleteUserById(int userId){
        if(getUserById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    @Override
    public void updateUser(int userId, String newPassword, String newEmail, String newLogin, String newPhotoUrl) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (newPassword != null) {
                user.setPassword(newPassword);
            }
            if (newEmail != null) {
                user.setEmail(newEmail);
            }
            if (newLogin != null) {
                user.setLogin(newLogin);
            }
            if (newPhotoUrl != null) {
                user.setPhotoUrl(newPhotoUrl);
            }
            userRepository.save(user);
        }
    }

    public void updateUserEmailById(int userId, String newEmail){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setEmail(newEmail);
            userRepository.save(user);
        }
    }

    @Override
    public void updateUsernameById(int userId, String newUsername){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setUserName(newUsername);
            userRepository.save(user);
        }
    }
    @Override
    public void updateUserPhotoById(int userId, String newPhotoUrl){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPhotoUrl(newPhotoUrl);
            userRepository.save(user);
        }
    }

    @Override
    public boolean authenticateUserByEmail(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        return user.filter(value -> Objects.equals(value.getPassword(), password)).isPresent();
    }
    @Override
    public boolean authenticateUserByUsername(String username, String password){
        Optional<User> user = userRepository.findByUserName(username);
        return user.filter(value -> Objects.equals(value.getPassword(), password)).isPresent();
    }

    @Override
    public void changeUserStatus(int userId, boolean newStatus) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(newStatus);
            userRepository.save(user);
        }
    }

}
