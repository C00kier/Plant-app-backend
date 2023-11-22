package com.plantapp.plantapp.user_plant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public UserPlant(User user, Plant plant, String room, String alias, Date lastWater, Date lastFertilizer, Date lastPropagated, Date lastPruned, Date lastRepotted) {
        this.user = user;
        this.plant = plant;
        this.room = room;
        this.alias = alias;
        this.lastWater = lastWater;
        this.lastFertilizer = lastFertilizer;
        this.lastPropagated = lastPropagated;
        this.lastPruned = lastPruned;
        this.lastRepotted = lastRepotted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userPlantId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @JsonProperty("user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "plantId")
    private Plant plant;

    private String room;

    private String alias;
    @JsonProperty("last_water")
    private Date lastWater;
    @JsonProperty("last_fertilizer")
    private Date lastFertilizer;
    @JsonProperty("last_propagated")
    private Date lastPropagated;
    @JsonProperty("last_pruned")
    private Date lastPruned;
    @JsonProperty("last_repotted")
    private Date lastRepotted;

    private final LocalDate added = LocalDate.now();
}
