package com.plantapp.plantapp.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleUserInfo {
    private String email;
    private String googleId;
    private String photoUrl;
}