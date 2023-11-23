package com.plantapp.plantapp.user_activity_log.model;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_plant_id", referencedColumnName = "userPlantId")
    private UserPlant userPlant;

    private final LocalDate ACTIVITY_DATE = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    private String timing;
}
