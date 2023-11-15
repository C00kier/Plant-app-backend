package com.plantapp.plantapp.user_game_progress.model;

import lombok.Data;

@Data
public class UserProgressRequestDTO {
    private int userId;
    private int exp;
}
