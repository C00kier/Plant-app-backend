package com.plantapp.plantapp.plant.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String picture;
    private double matureSize;
    private boolean toxicity;
    private boolean airPurifying;
    private int repotting;
    private int fertilizer;
    private int sun;
    private int water;
    private int careDifficulty;
    @Column(columnDefinition = "text")
    private String botanicalName;
    @Column(columnDefinition = "text")
    private String commonName;
    @Column(columnDefinition = "text")
    private String translation;
    @Column(columnDefinition = "text")
    private String plantOverview;
    @Column(columnDefinition = "text")
    private String nativeArea;
    @Column(columnDefinition = "text")
    private String plantType;
    @Column(columnDefinition = "text")
    private String careDescription;
    @Column(columnDefinition = "text")
    private String waterExtended;
    @Column(columnDefinition = "text")
    private String sunExtended;
    @Column(columnDefinition = "text")
    private String temperature;
    @Column(columnDefinition = "text")
    private String humidity;
    @Column(columnDefinition = "text")
    private String fertilizerExtended;
    @Column(columnDefinition = "text")
    private String bloomTime;
    @Column(columnDefinition = "text")
    private String repottingExtended;
    @Column(columnDefinition = "text")
    private String soilType;
    @Column(columnDefinition = "text")
    private String soilPh;
    @Column(columnDefinition = "text")
    private String propagating;
    @Column(columnDefinition = "text")
    private String pestsAndDiseases;
    @Column(columnDefinition = "text")
    private String pruning;

}
