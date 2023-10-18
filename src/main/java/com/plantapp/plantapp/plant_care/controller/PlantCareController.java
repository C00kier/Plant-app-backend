package com.plantapp.plantapp.plant_care.controller;

import com.plantapp.plantapp.plant_care.model.PlantCare;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plant-care")
public class PlantCareController {

    @GetMapping("/{user-plant-id}")
    public PlantCare getPlantCare(@PathVariable("user-plant-id") int userPlantId){return null;}

    @PatchMapping("/{user-plant-id}")
    public void updatePlantCare(@PathVariable("user-plant-id") int userPlantId){}

    @DeleteMapping("/{user-plant-id}")
    public void deletePlantCare(@PathVariable("user-plant-id") int userPlantId){}
}
