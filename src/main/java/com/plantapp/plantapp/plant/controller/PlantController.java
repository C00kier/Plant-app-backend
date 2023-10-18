package com.plantapp.plantapp.plant.controller;

import com.plantapp.plantapp.plant.model.Plant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @GetMapping("/{plant-name}")
    public List<Plant> getPlantsByName(@PathVariable("plant-name") String plantName){
        return null;
    }

    @GetMapping("/{plant-id}")
    public Plant getPlantById(@PathVariable("plant-id") int plantId){
        return null;
    }
    @GetMapping("/get-plants")
    public List<Plant> getUserPlants(){
        return null;
    }
}
