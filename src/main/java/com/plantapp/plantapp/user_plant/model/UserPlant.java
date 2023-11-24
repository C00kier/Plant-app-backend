package com.plantapp.plantapp.user_plant.model;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.plant.model.Plant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_plants")
public class UserPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userPlantId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "plantId")
    private Plant plant;
    private String room;
    private String alias;
    private Date lastWatered;
    private Date lastFertilized;
    private Date lastPropagated;
    private Date lastPruned;
    private Date lastRepotted;

    private final LocalDate added = LocalDate.now();
}
