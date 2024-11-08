package com.example.exam.controller;

import com.example.exam.model.Question;
import com.example.exam.service.ExaminerService;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public List<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }
}
