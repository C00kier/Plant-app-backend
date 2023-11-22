package com.plantapp.plantapp.quiz.controller;

import com.plantapp.plantapp.quiz.QuizService.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")

public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PutMapping("/set-quiz-result")
    public ResponseEntity<String> getResult(
            @RequestParam("isToxic") boolean isToxic,
            @RequestParam("isSunny") int isSunny,
            @RequestParam("isAirPurifying") boolean isAirPurifying,
            @RequestParam("matureSize") double matureSize,
            @RequestParam("difficulty") int difficulty,
            @RequestParam("userId") int userId) {
        quizService.createNewQuizRecord(userId, isToxic, isSunny, isAirPurifying, matureSize, difficulty);
        return ResponseEntity.ok("New quiz result created");
    }
}