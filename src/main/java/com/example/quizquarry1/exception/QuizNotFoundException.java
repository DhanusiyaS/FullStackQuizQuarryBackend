package com.example.quizquarry1.exception;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(String text) {
        super(text);
    }
}