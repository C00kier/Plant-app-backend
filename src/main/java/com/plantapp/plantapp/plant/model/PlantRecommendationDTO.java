package com.plantapp.plantapp.plant.model;

public record PlantRecommendationDTO (
        int id, String botanicalName,
        int sun,
        double matureSize,
        int careDifficulty,
        boolean airPurifying,
        boolean toxicity)
{ }
