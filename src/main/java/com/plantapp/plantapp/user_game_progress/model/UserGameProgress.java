package com.plantapp.plantapp.user_game_progress.model;

import com.plantapp.plantapp.user.model.User;
import jakarta.persistence.*;

@Entity
public class UserGameProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int experience;
    @OneToOne
    private User user;





}
