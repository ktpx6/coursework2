package com.example.exam.service;

import com.example.exam.model.Question;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {
    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public Question addQuestion(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        if (!questions.contains(newQuestion)) {
            questions.add(newQuestion);
        }
        return newQuestion;
    }

    @Override
    public Question removeQuestion(String question, String answer) {
        Question questionToRemove = new Question(question, answer);
        questions.remove(questionToRemove);
        return questionToRemove;
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return questions.get(random.nextInt(questions.size()));
    }
}
