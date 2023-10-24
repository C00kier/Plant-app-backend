package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void addUser(String email, String password, String login){
        userRepository.save(new User(email, password, login));
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
    public void updateUserPasswordById(int userId, String newPassword){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }
    @Override
    public void updateUserEmailById(int userId, String newEmail){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setEmail(newEmail);
            userRepository.save(user);
        }
    }
    @Override
    public void updateUserLoginById(int userId, String newLogin){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setLogin(newLogin);
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
}
