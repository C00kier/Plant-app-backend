package com.plantapp.plantapp.user.model;

import com.plantapp.plantapp.user_type.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data

@Table(name="plants_user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = true)
    private String photoUrl;

    public User(String email, String password, String userName){
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userType = UserType.USER;
    }
}
