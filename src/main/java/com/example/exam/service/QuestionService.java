package com.example.exam.service;

import com.example.exam.model.Question;
import java.util.List;

public interface QuestionService {
    Question addQuestion(String question, String answer);
    Question removeQuestion(String question, String answer);
    List<Question> getAllQuestions();
    Question getRandomQuestion();
}
