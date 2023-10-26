package com.plantapp.plantapp.plant.controller;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.service.PlantService;
import com.plantapp.plantapp.quiz.QuizService.QuizService;
import com.plantapp.plantapp.quiz.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    private final PlantService plantService;
    private final QuizService quizService;

    @Autowired
    public PlantController(PlantService plantService, QuizService quizService) {
        this.plantService = plantService;
        this.quizService = quizService;
    }

    @GetMapping("/{plant-name}")
    public List<Plant> getPlantsByName(@PathVariable("plant-name") String plantName) {
        return null;
    }

    @GetMapping("/{plant-id}")
    public Plant getPlantById(@PathVariable("plant-id") int plantId) {
        return null;
    }

    @GetMapping("/get-plants")
    public List<Plant> getUserPlants() {
        return null;
    }

    @GetMapping("/get-plants-by-user-quiz")
    public List<Plant> getPlantsByUserQuiz(@RequestParam("userId") int userId) {
        Quiz quiz = quizService.getQuizByUserId(userId);
        return plantService.getRecommendedPlants(quiz);
    }
}
