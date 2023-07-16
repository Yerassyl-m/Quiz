package com.company;

import java.io.IOException;

public class QuizMaker {
    public static void main(String[] args) throws IOException {
        Quiz quiz = Quiz.loadFromFile("JavaQuiz.txt");
        quiz.setName("Java quiz");
        quiz.start();
    }
}