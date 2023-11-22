package com.plantapp.plantapp.user_plant.controller;

import com.plantapp.plantapp.user_plant.model.UserPlant;
import com.plantapp.plantapp.user_plant.service.UserPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user-plant")
public class UserPlantController {
    private final UserPlantService userPlantsService;

    @Autowired
    public UserPlantController(UserPlantService userPlantsService) {
        this.userPlantsService = userPlantsService;
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<List<UserPlant>> getAllUserPlantsByUserId(@PathVariable("user-id") int userId) {
        List<UserPlant> userPlants = userPlantsService.getUserPlantsByUserId(userId);
        return ResponseEntity.ok(userPlants);
    }

    @PostMapping("/add")
    public void addPlantToUserPlantsById(
            @RequestBody UserPlant userPlant){
        userPlantsService.addPlantToUserPlantsById(userPlant);
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

    @PatchMapping("/{user-plant-id}/last-watering")
    public void updateLastWatering(@PathVariable("user-plant-id") int userPlantId){
        userPlantsService.updateLastWateringByUserPlantId(userPlantId);
    }
    @PatchMapping("/{user-plant-id}/last-propagated")
    public void updateLastPropagated(@PathVariable("user-plant-id") int userPlantId){
        userPlantsService.updateLastPropagateByUserPlantId(userPlantId);
    }
    @PatchMapping("/{user-plant-id}/last-repotted")
    public void updateLastRepotted(@PathVariable("user-plant-id") int userPlantId){
        userPlantsService.updateLastRepotByUserPlantId(userPlantId);
    }
    @PatchMapping("/{user-plant-id}/last-fertilized")
    public void updateLastFertilized(@PathVariable("user-plant-id") int userPlantId){
        userPlantsService.updateLastFertilizerByUserPlantId(userPlantId);
    }
    @PatchMapping("/{user-plant-id}/last-pruned")
    public void updateLastPruned(@PathVariable("user-plant-id") int userPlantId){
        userPlantsService.updateLastPrunedByUserPlantId(userPlantId);
    }
}
