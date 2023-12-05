package com.plantapp.plantapp.plant.controller;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.model.PlantNameDTO;
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
    public ResponseEntity<List<Plant>> getAllPlants() {
        try {
            List<Plant> plants = plantService.getAllPlants();
            return ResponseEntity.ok(plants);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Plant> addNewPlant(@RequestBody Plant plant) {
        return ResponseEntity.ok(plantService.addNewPlant(plant));
    }

    @DeleteMapping("/delete/{plant-id}")
    public ResponseEntity<Object> deletePlantById(@PathVariable("plant-id") int plantId) {
        plantService.deletePlantById(plantId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{plant-id}")
    public ResponseEntity<Plant> changePlantById(@PathVariable("plant-id") int plantId, @RequestBody Plant plant) {
        try {
            Plant changedPlant = plantService.changePlantById(plantId, plant);

            return ResponseEntity.ok(changedPlant);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{plant-id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable("plant-id") int plantId) {
        try {
            Optional<Plant> plant = plantService.getPlantById(plantId);
            return plant.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/name/{plant-name}")
    public ResponseEntity<List<PlantNameDTO>> getPlantsByName(@PathVariable("plant-name") String plantName) {
        try {
            List<PlantNameDTO> plants = plantService.getPlantsByName(plantName);
            return ResponseEntity.ok(plants);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/filter/sun/{sun}")
    public ResponseEntity<List<PlantNameDTO>> getPlantsBySunIntensity(@PathVariable("sun") int sun, @RequestParam(required = false) String name) {
        try {
            List<PlantNameDTO> plants = plantService.getPlantsBySunIntensity(sun, name);
            return ResponseEntity.ok(plants);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/filter/difficulty/{difficulty}")
    public ResponseEntity<List<PlantNameDTO>> getPlantsByDifficulty(@PathVariable("difficulty") int difficulty, @RequestParam(required = false) String name) {
        try {
            List<PlantNameDTO> plants = plantService.getPlantsByDifficulty(difficulty, name);
            return ResponseEntity.ok(plants);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/filter/airpuryfying")
    public ResponseEntity<List<PlantNameDTO>> getAirPuryfyingPlants(@RequestParam(required = false) String name) {
        try {
            List<PlantNameDTO> plants = plantService.getAirPurifyingPlants(name);
            return ResponseEntity.ok(plants);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/filter/nontoxic")
    public ResponseEntity<List<PlantNameDTO>> getNonToxicPlants(@RequestParam(required = false) String name) {
        try {
            List<PlantNameDTO> plants = plantService.getNonToxicPlants(name);
            return ResponseEntity.ok(plants);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/filter/plants-by-quiz")
    public ResponseEntity<List<PlantNameDTO>> getPlantsByQuizAnswers(@RequestParam("isToxic") boolean isToxic,
                                                                     @RequestParam("sun") int sun,
                                                                     @RequestParam("isAirPurifying") boolean isAirPurifying,
                                                                     @RequestParam("matureSize") double matureSize,
                                                                     @RequestParam("careDifficulty") int careDifficulty) {
        try {
            List<PlantNameDTO> plants = plantService.getPlantsByQuizAnswers(isToxic, sun, isAirPurifying, matureSize, careDifficulty);
            return ResponseEntity.ok(plants);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
