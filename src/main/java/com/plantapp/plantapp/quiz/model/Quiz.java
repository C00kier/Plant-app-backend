package com.plantapp.plantapp.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Quiz {
    public Quiz(boolean toxicity, int sun, boolean air_purifying, double mature_size, int care_difficulty, int userId) {
        this.toxicity = toxicity;
        this.sun = sun;
        this.air_purifying = air_purifying;
        this.mature_size = mature_size;
        this.care_difficulty = care_difficulty;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;
    private boolean toxicity;
    private int sun;
    private boolean air_purifying;
    private double mature_size;
    private int care_difficulty;
    private int userId;
}
