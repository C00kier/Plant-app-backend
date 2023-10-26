package com.plantapp.plantapp.plant.controller;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plant")
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping()
    public ResponseEntity<List<Plant>> getAllPlants(){
        try{
            List<Plant> plants = plantService.getAllPlants();
            return ResponseEntity.ok(plants);
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public void addNewPlant(@RequestBody Plant plant){
       plantService.addNewPlant(
                plant.getMatureSize(), plant.isToxicity(), plant.isAirPurifying(),
                plant.getRepotting(), plant.getFertilizer(), plant.getSun(),
               plant.getWater(), plant.getCareDifficulty(), plant.getBotanicalName(),
               plant.getCommonName(), plant.getTranslation(), plant.getPlantOverview(),
               plant.getNativeArea(), plant.getPlantType(), plant.getCareDescription(),
               plant.getWaterExtended(), plant.getSunExtended(), plant.getTemperature(),
               plant.getHumidity(), plant.getFertilizerExtended(), plant.getBloomTime(),
               plant.getRepottingExtended(), plant.getSoilType(), plant.getSoilPh(),
               plant.getPropagating(), plant.getPestsAndDiseases(), plant.getPruning());
    }

    @DeleteMapping("/delete/{plant-id}")
    public ResponseEntity<Optional<Plant>> deletePlantById(@PathVariable("plant-id") int plantId){
        try{
            Optional<Plant> plant = plantService.deletePlantById(plantId);
            return ResponseEntity.ok(plant);
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{plant-id}")

    @GetMapping("/{plant-id}")
    public ResponseEntity<Optional<Plant>> getPlantById(@PathVariable("plant-id") int plantId){
        try{
        Optional<Plant> plant = plantService.getPlantById(plantId);
        return ResponseEntity.ok(plant);
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{plant-name}")
    public ResponseEntity<List<Plant>> getPlantsByName(@PathVariable("plant-name") String plantName){
        try{
            List<Plant> plants = plantService.getPlantsByName(plantName);
            return ResponseEntity.ok(plants);
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
