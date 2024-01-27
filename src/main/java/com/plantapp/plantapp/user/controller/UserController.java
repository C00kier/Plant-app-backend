package com.plantapp.plantapp.user.controller;

import com.plantapp.plantapp.user.model.UpdateRequestDTO;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.model.UserDTO;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user.service.UserService;
import com.plantapp.plantapp.user_activity.service.UserActivityService;
import com.plantapp.plantapp.user_game_progress.service.UserGameProgressService;
import com.plantapp.plantapp.user_plant.service.UserPlantService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final UserRepository userRepository;

    private final UserGameProgressService userGameProgressService;

    private final UserPlantService userPlantService;

    private final UserActivityService userActivityService;

    private final JavaMailSender mailSender;

    @Value("${client_address}")
    private String clientAddress;

    @Value("${support_email_address}")
    private String supportAddress;

    @Value("${support_email_name}")
    private String supportName;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, UserGameProgressService userGameProgressService, UserPlantService userPlantService, UserActivityService userActivityService, JavaMailSender mailSender) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userGameProgressService = userGameProgressService;
        this.userPlantService = userPlantService;
        this.userActivityService = userActivityService;
        this.mailSender = mailSender;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> getUserById(@RequestBody User user) {
        UserDTO userDTO = userService.getUserObjectById(user.getUserId());
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
            @RequestBody UpdateRequestDTO updateRequest) {
        int userId = updateRequest.getUserId();
        String oldPassword = updateRequest.getOldPassword();
        String newPassword = updateRequest.getNewPassword();
        String newEmail = updateRequest.getNewEmail();
        String newNickName = updateRequest.getNewNickName();
        String newPhotoUrl = updateRequest.getNewPhotoUrl();

        if (oldPassword == null && newPassword == null && newEmail == null && newNickName == null && newPhotoUrl == null) {
            return ResponseEntity.badRequest().body("No updates provided.");
        }

        userService.updateUser(userId, oldPassword, newPassword, newEmail, newNickName, newPhotoUrl);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody int userId) {
        userActivityService.deleteUserActivitiesByUserId(userId);
        userGameProgressService.removeUserExperienceByUserId(userId);
        userPlantService.removeAllUserPlants(userId);
        userService.deleteUserById(userId);
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> processForgotPassword(@RequestBody Map<String, String> requestBody) throws MessagingException,
            UnsupportedEncodingException {
        String email = requestBody.get("email");

        if (userRepository.findByEmail(email).isEmpty()){
            return ResponseEntity.badRequest().body("Email not found.");
        }

        String token = generateRandomString();

        userService.updateResetPasswordToken(token, email);
        String resetPasswordLink = String.format("%s/reset-password?token=%s", clientAddress, token);
        sendEmail(email, resetPasswordLink);

        return ResponseEntity.ok("Reset password email has been sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> processResetPassword(@RequestBody Map<String,String> requestBody) {
        String resetToken = requestBody.get("resetToken");
        String password = requestBody.get("password");

        Optional<User> optionalUser = userService.getByResetPasswordToken(resetToken);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            userService.updatePassword(user, password);
            return ResponseEntity.ok("The password has been reset");
        }

        return ResponseEntity.badRequest().body("Reset token not valid");
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(supportAddress,supportName);
        helper.setTo(recipientEmail);

        String subject = "Reset your Sprout password";

        String content = "<p>Hi,</p>"
                + "<p>You have requested a password reset.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    public String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
