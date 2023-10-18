package com.plantapp.plantapp.user_plants.controller;

import com.plantapp.plantapp.user_plants.model.UserPlants;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-plants")
public class UserPlantsController {

    @GetMapping("/{user-id}")
    public List<UserPlants> getUserPlantsByUserId(@PathVariable("user-id") int userId){
        return null;
    }

    @PostMapping("/{user-id}/{user-plant-id}")
    public void addPlantToUserPlantsById(@PathVariable("user-id") int userId, @PathVariable("user-plant-id") int userPlantId){}

    @DeleteMapping("/{user-id}/{user-plant-id}")
    public void removePlantFromUserPlantsById(@PathVariable("user-id") int userId, @PathVariable("user-plant-id") int userPlantId){}

    //update room and alias
    @PatchMapping("/{user-id}/{user-plant-id}")
    public void updateUserPlant(@PathVariable("user-id") int userId, @PathVariable("user-plant-id") int userPlantId){}

}
