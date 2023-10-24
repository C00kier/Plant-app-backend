package com.plantapp.plantapp.quiz.QuizDBManager;

import java.sql.Connection;

public class QuizDBManager {

    private final Connection conn;

    public QuizDBManager (Connection conn){
        this.conn=conn;
    }
}
