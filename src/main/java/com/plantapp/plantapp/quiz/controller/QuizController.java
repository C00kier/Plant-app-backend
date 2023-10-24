package com.plantapp.plantapp.quiz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.quiz.QuizService.QuizService;
import com.plantapp.plantapp.quiz.model.Quiz;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/quiz")

public class QuizController {
    private final RestTemplate restTemplate;
    private final QuizService quizService = new QuizService();

    public QuizController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/result")
    public String getResult(@RequestParam("hasChildren") boolean hasChildren,
                            @RequestParam("hasAnimals") boolean hasAnimals,
                            @RequestParam("isSunny") int isSunny,
                            @RequestParam("isAirpurifyingImportant") boolean isAirpurifyingImportant,
                            @RequestParam("matureSize") double matureSize,
                            @RequestParam("isExperienced") int isExperienced) {
        Quiz res = new Quiz(hasChildren, hasAnimals, isSunny, isAirpurifyingImportant, matureSize, isExperienced);
        return quizService.returnDBQuery(res);
    }

}
