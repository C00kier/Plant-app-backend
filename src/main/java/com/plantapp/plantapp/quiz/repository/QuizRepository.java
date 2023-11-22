package com.plantapp.plantapp.quiz.repository;

import com.plantapp.plantapp.quiz.model.Quiz;
import com.plantapp.plantapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    public Quiz findByUserId(int userId);

}
