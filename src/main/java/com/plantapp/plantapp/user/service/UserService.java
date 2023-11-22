package com.plantapp.plantapp.user.service;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.model.UserDTO;
import com.plantapp.plantapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void addUser(String email, String password, String login) {
        userRepository.save(new User(email, password, login));
    }

    @Override
    public Optional<User> getUserById(int userId){
         return userRepository.findById(userId);
    }

    @Override
    public UserDTO getUserObjectById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(user -> new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getNickName(),
                user.getPhotoUrl(),
                user.getUserType())).orElse(null);

    }

    @Override
    public void deleteUserById(int userId){
        if(getUserById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    @Override
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
            }
            if (newEmail != null) {
                user.setEmail(newEmail);
            }
            if (newNickName != null) {
                user.setNickName(newNickName);
            }
            if (newPhotoUrl != null) {
                user.setPhotoUrl(newPhotoUrl);
            }
            userRepository.save(user);
        }
    }


}
