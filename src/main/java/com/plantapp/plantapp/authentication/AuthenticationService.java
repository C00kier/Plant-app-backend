package com.plantapp.plantapp.authentication;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.plantapp.plantapp.configuration.JwtService;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_type.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.jackson2.*;


import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${google_client_id}")
    private String CLIENT_ID;

    public AuthenticationResponse register(RegisterRequest request) {

        boolean userExists = userRepository.existsByEmail(request.getEmail());

        if (userExists) {
            return null;
        }

        var user = new User();
        String[] emailParts = request.getEmail().split("@");
        if (emailParts.length > 0) {
            user.setNickName(emailParts[0]);
        }
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserType(UserType.USER);
        user.setActive(true);
        userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .userId(user.getUserId())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .userId(user.getUserId())
                .build();
    }

    public AuthenticationResponse authenticateGoogle(GoogleAuthenticationRequest request) {
        GoogleUserInfo googleUserInfo = validateAndExtractGoogleToken(request.getCredential());

        assert googleUserInfo != null;
        Optional<User> existingUser = userRepository.findByEmail(googleUserInfo.getEmail());

        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
           user = new User();
            System.out.println(googleUserInfo);
            user.setEmail(googleUserInfo.getEmail());
            user.setPassword(googleUserInfo.getGoogleId());
            user.setPhotoUrl(googleUserInfo.getPhotoUrl());
            user.setActive(true);
            String[] emailParts = googleUserInfo.getEmail().split("@");
            if (emailParts.length > 0) {
                user.setNickName(emailParts[0]);
            }
            userRepository.save(user);
        }

        String jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .userId(user.getUserId())
                .build();
    }
    @SuppressWarnings("deprecation")
    private GoogleUserInfo validateAndExtractGoogleToken(String idToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken token = verifier.verify(idToken);
            if (token != null) {
                Payload payload = token.getPayload();
                String email = payload.getEmail();
                String googleId = payload.getSubject();
                String photoUrl = (String) payload.get("picture");
                return new GoogleUserInfo(email, googleId, photoUrl);
            } else {
                return null;
            }
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
}
