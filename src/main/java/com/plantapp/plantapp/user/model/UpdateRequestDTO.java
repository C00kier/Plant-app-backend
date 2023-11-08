package com.plantapp.plantapp.user.model;

import lombok.Data;

@Data
public class UpdateRequestDTO {
    private int userId;
    private String newPassword;
    private String newEmail;
    private String newLogin;
    private String newPhotoUrl;
}
