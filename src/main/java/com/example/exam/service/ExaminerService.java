package com.example.exam.service;

import com.example.exam.model.Question;
import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}
