package com.plantapp.plantapp.user.model;

import com.plantapp.plantapp.user_type.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String login;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = true)
    private String photoUrl;

    private boolean isActive;

    public User(String email, String password, String login){
        this.email = email;
        this.password = password;
        this.login = login;
        this.userType = UserType.USER;
    }

    public User(int userId){
        this.userId = userId;
    }
}
