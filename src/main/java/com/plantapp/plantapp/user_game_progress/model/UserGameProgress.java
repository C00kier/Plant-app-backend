package com.plantapp.plantapp.user_game_progress.model;

import com.plantapp.plantapp.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserGameProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int experience;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;




}
