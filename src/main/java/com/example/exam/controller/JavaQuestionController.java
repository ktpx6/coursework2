package com.example.exam.controller;

import com.example.exam.model.Question;
import com.example.exam.service.JavaQuestionService;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService questionService;

    public JavaQuestionController(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.addQuestion(question, answer);
    }

    @DeleteMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.removeQuestion(question, answer);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
