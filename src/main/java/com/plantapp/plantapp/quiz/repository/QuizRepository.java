package com.plantapp.plantapp.quiz.repository;

import com.plantapp.plantapp.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findByUserId(int userId);
}
