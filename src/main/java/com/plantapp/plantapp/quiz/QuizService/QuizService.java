package com.plantapp.plantapp.quiz.QuizService;

import com.plantapp.plantapp.quiz.model.Quiz;

public class QuizService {

    public String returnDBQuery(Quiz quiz) {
        return String.format("SELECT * FROM plant WHERE" +
                " toxicity=%s and " +
                "sun=%s and " +
                "air_purifying=%s and " +
                "mature_size=%s and " +
                "care_difficulty=%s",
                quiz.hasAnimals(), quiz.isSunny(), quiz.isAirpurifyingImportant(), quiz.matureSize(), quiz.isExperienced());
    }
}
