package com.plantapp.plantapp.user_plants.controller;

import com.plantapp.plantapp.user_plants.model.UserPlants;
import com.plantapp.plantapp.user_plants.service.UserPlantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-plants")
public class UserPlantsController {
    private final UserPlantsService userPlantsService;

    @Autowired
    public UserPlantsController(UserPlantsService userPlantsService) {
        this.userPlantsService = userPlantsService;
    }

    @GetMapping("/{user-id}")
    public List<UserPlants> getUserPlantsByUserId(@PathVariable("user-id") int userId){
        return userPlantsService.getUserPlantsByUserId(userId);
    }

    @PostMapping("/{user-id}/{plant-id}")
    public void addPlantToUserPlantsById(
            @PathVariable("user-id") int userId,
            @PathVariable("plant-id") int plantId){
        userPlantsService.addPlantToUserPlantsById(userId,plantId);
    }

    @DeleteMapping("/{user-plant-id}")
    public void removePlantFromUserPlantsById(@PathVariable("user-plant-id") int userPlantId){
        userPlantsService.removePlantFromUserPlantsById(userPlantId);
    }

    @PatchMapping("/{user-plant-id}/room")
    public void updateUserPlantRoom(
            @PathVariable("user-plant-id") int userPlantId,
            @RequestParam String roomName){
        userPlantsService.updateUserPlantRoomById(userPlantId, roomName);
    }

    @PatchMapping("/{user-plant-id}/alias")
    public void updateUserPlantAlias(
            @PathVariable("user-plant-id") int userPlantId,
            @RequestParam String alias){
        userPlantsService.updateUserPlantAliasById(userPlantId, alias);
    }
}
