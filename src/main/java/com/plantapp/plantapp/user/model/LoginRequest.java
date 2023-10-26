package com.plantapp.plantapp.user.model;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String login;
    private String password;
}
