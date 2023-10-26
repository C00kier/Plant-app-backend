package com.plantapp.plantapp.quiz.QuizService;

import com.plantapp.plantapp.quiz.model.Quiz;
import com.plantapp.plantapp.quiz.repository.QuizRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public void createNewQuizRecord(int userId, boolean isToxic, int isSunny, boolean isAirPurifying, double matureSize, int difficulty) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Quiz quiz = new Quiz(isToxic, isSunny, isAirPurifying, matureSize, difficulty, user);
            quizRepository.save(quiz);
        }
    }
    public Quiz getQuizByUserId(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            return quizRepository.findByUser(optionalUser.get());
        }
        return null;
    }

}
