package com.plantapp.plantapp.plant.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double matureSize;

    private boolean toxicity;

    private boolean airPurifying;

    private int repotting;
    private int fertilizer;

    private int sun;

    private int water;

    private int careDifficulty;

    @Column(length = 100)
    private String botanicalName;

    @Column(length = 100)
    private String commonName;

    @Column(length = 65000)
    private String translation;
    @Column(length = 65000)
    private String plantOverview;

    @Column(length = 65000)
    private String nativeArea;

    @Column(length = 65000)
    private String plantType;

    @Column(length = 65000)
    private String careDescription;

    @Column(length = 65000)
    private String waterExtended;

    @Column(length = 65000)
    private String sunExtended;

    @Column(length = 65000)
    private String temperature;

    @Column(length = 65000)
    private String humidity;

    @Column(length = 65000)
    private String fertilizerExtended;

    @Column(length = 65000)
    private String bloomTime;

    @Column(length = 65000)
    private String repottingExtended;

    @Column(length = 65000)
    private String soilType;

    @Column(length = 65000)
    private String soilPh;

    @Column(length = 65000)
    private String propagating;

    @Column(length = 65000)
    private String pestsAndDiseases;

    @Column(length = 65000)
    private String pruning;
}
