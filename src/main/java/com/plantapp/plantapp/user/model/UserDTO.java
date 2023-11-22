package com.plantapp.plantapp.user.model;

import com.plantapp.plantapp.user_type.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String email;
    private String nickName;
    private String photoUrl;
    private UserType userType;
}
