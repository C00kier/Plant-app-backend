package com.plantapp.plantapp.user.model;

import lombok.Data;

@Data
public class UpdateRequestDTO {
    private int userId;
    private String oldPassword;
    private String newPassword;
    private String newEmail;
    private String newNickName;
    private String newPhotoUrl;
}
