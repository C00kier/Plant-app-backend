package com.plantapp.plantapp.user.model;

import lombok.Data;

@Data
public class UpdateRequestDTO {
    private int userId;
<<<<<<< HEAD
    private String newPassword;
    private String newEmail;
    private String newLogin;
=======
    private String oldPassword;
    private String newPassword;
    private String newEmail;
    private String newNickName;
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
    private String newPhotoUrl;
}
