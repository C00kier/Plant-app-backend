package com.plantapp.plantapp.user_plants.model;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_plants")
public class UserPlants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userPlantId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant plant;

    private String room;

    private String alias;

    @NotBlank
    private LocalDate added;
}
