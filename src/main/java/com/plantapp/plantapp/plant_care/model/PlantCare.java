package com.plantapp.plantapp.plant_care.model;

import com.plantapp.plantapp.plant.model.Plant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PlantCare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_plant-id")
    private Plant plant;

    private Date last_water;
    private Date last_fertilizer;
    private Date propagated;
    private Date pruned;
    private Date repotted;

}
