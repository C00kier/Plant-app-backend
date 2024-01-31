package com.plantapp.plantapp.user_plant.controller;

import com.plantapp.plantapp.user_activity.model.ActivityType;
import com.plantapp.plantapp.user_activity.service.UserActivityService;
import com.plantapp.plantapp.user_game_progress.service.UserGameProgressService;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import com.plantapp.plantapp.user_plant.service.UserPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-plant")
public class UserPlantController {
    private final UserPlantService userPlantsService;

    private final UserActivityService userActivityService;

    private final UserGameProgressService userGameProgressService;

    @Autowired
    public UserPlantController(UserPlantService userPlantsService, UserActivityService userActivityService, UserGameProgressService userGameProgressService) {
        this.userPlantsService = userPlantsService;
        this.userActivityService = userActivityService;
        this.userGameProgressService= userGameProgressService;
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<List<UserPlant>> getAllUserPlantsByUserId(@PathVariable("user-id") int userId) {
        List<UserPlant> userPlants = userPlantsService.getUserPlantsByUserId(userId);
        return ResponseEntity.ok(userPlants);
    }

    @PostMapping("/add")
    public void addPlantToUserPlants(
            @RequestBody UserPlant userPlant) {
        userPlantsService.addPlantToUserPlants(userPlant);
        userActivityService.addPlantActivity(userPlant.getUserPlantId(), ActivityType.ADDING_PLANT);
        userGameProgressService.updateUserExperienceByUserId(userPlant.getUser().getUserId(), 5);
    }

    @DeleteMapping("/{user-plant-id}")
        public ResponseEntity<String> removePlantFromUserPlantsById(@PathVariable("user-plant-id") int userPlantId) {
        userActivityService.deleteUserActivitiesByUserPlantId(userPlantId);
        userPlantsService.removePlantFromUserPlantsById(userPlantId);
        return ResponseEntity.ok("Plant removed successfully");
    }

    @PatchMapping("/{user-plant-id}/room/remove")
    public void removeRoomFromPlant(
            @PathVariable("user-plant-id") int userPlantId
    ){
        userPlantsService.removeRoomFromPlantById(userPlantId);
    }

    @PatchMapping("/{user-plant-id}/room")
    public void updateUserPlantRoom(
            @PathVariable("user-plant-id") int userPlantId,
            @RequestParam String roomName) {
        userPlantsService.updateUserPlantRoomById(userPlantId, roomName);
    }

    @PatchMapping("/{user-plant-id}/alias")
    public void updateUserPlantAlias(
            @PathVariable("user-plant-id") int userPlantId,
            @RequestParam String alias) {
        userPlantsService.updateUserPlantAliasById(userPlantId, alias);
    }

    @PatchMapping("/{user-plant-id}/last-watering")
    public void updateLastWatering(
            @PathVariable("user-plant-id") int userPlantId,
            @RequestBody Map<String, Date> requestBody
            ){
        Date date = requestBody.get("date");
        userPlantsService.updateLastWateredByUserPlantId(userPlantId,date);
        userActivityService.addPlantActivity(userPlantId, ActivityType.WATERING_PLANT);
    }

    @PatchMapping("/{user-plant-id}/last-propagated")
    public void updateLastPropagated(@PathVariable("user-plant-id") int userPlantId,
                                     @RequestBody Map<String, Date> requestBody){
        Date date = requestBody.get("date");
        userPlantsService.updateLastPropagatedByUserPlantId(userPlantId, date);
        userActivityService.addPlantActivity(userPlantId, ActivityType.PROPAGATING_PLANT);
    }

    @PatchMapping("/{user-plant-id}/last-repotted")
    public void updateLastRepotted(@PathVariable("user-plant-id") int userPlantId,
                                   @RequestBody Map<String, Date> requestBody) {
        Date date = requestBody.get("date");
        userPlantsService.updateLastRepottedByUserPlantId(userPlantId,date);
        userActivityService.addPlantActivity(userPlantId, ActivityType.REPOTTING_PLANT);
    }

    @PatchMapping("/{user-plant-id}/last-fertilized")
    public void updateLastFertilized(@PathVariable("user-plant-id") int userPlantId,
                                     @RequestBody Map<String, Date> requestBody) {
        Date date = requestBody.get("date");
        userPlantsService.updateLastFertilizedByUserPlantId(userPlantId, date);
        userActivityService.addPlantActivity(userPlantId, ActivityType.FERTILIZING_PLANT);
    }

    @PatchMapping("/{user-plant-id}/last-pruned")
    public void updateLastPruned(@PathVariable("user-plant-id") int userPlantId,
                                 @RequestBody Map<String, Date> requestBody){
        Date date = requestBody.get("date");
        userPlantsService.updateLastPrunedByUserPlantId(userPlantId, date);
        userActivityService.addPlantActivity(userPlantId, ActivityType.PRUNING_PLANT);
    }
}
