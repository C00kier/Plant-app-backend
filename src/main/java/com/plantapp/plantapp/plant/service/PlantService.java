package com.plantapp.plantapp.plant.service;


import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.quiz.model.Quiz;
import com.plantapp.plantapp.quiz.repository.QuizRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantService {
    private final PlantRepository plantRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;


    public PlantService(PlantRepository plantRepository, QuizRepository quizRepository, UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public List<Plant> getRecommendedPlants(Quiz quiz) {
        return filterPlantsByQuiz(plantRepository.findAll(), quiz);
    }

    private List<Plant> filterPlantsByQuiz(List<Plant> plants, Quiz quiz) {
        plants.stream()
                .filter(f -> f.isToxicity() == quiz.isToxicity() &&
                        f.getSun() == quiz.getSun() &&
                        f.isAirPurifying() == quiz.isAir_purifying() &&
                        f.getMatureSize() == f.getCareDifficulty())
                .collect(Collectors.toList());
        return plants;
    }
}
