package com.plantapp.plantapp.user_game_progress.model;

import com.plantapp.plantapp.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserGameProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int experience;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

public UserGameProgress(int experience, User user){
    this.experience = experience;
    this.user = user;
}
}
