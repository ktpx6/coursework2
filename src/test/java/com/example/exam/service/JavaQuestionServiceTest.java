package com.example.exam.service;

import com.example.exam.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void addQuestion_shouldAddUniqueRussianQuestion() {
        Question question = questionService.addQuestion("Что такое ООП?", "Объектно-ориентированное программирование.");
        assertTrue(questionService.getAllQuestions().contains(question), "Вопрос должен быть добавлен.");
    }

    @Test
    void addQuestion_shouldNotAllowDuplicateRussianQuestion() {
        questionService.addQuestion("Что такое ООП?", "Объектно-ориентированное программирование.");
        questionService.addQuestion("Что такое ООП?", "Объектно-ориентированное программирование.");
        assertEquals(1, questionService.getAllQuestions().size(), "Дубликаты вопросов не должны добавляться.");
    }

    @Test
    void removeQuestion_shouldRemoveSpecificRussianQuestion() {
        questionService.addQuestion("Что такое полиморфизм?", "Способность объекта принимать различные формы.");
        questionService.removeQuestion("Что такое полиморфизм?", "Способность объекта принимать различные формы.");
        assertTrue(questionService.getAllQuestions().isEmpty(), "Вопрос должен быть удален.");
    }

    @Test
    void getAllQuestions_shouldReturnAllAddedRussianQuestions() {
        questionService.addQuestion("Что такое наследование?", "Механизм получения свойств одного класса другим.");
        questionService.addQuestion("Что такое инкапсуляция?", "Сокрытие деталей реализации.");
        List<Question> questions = questionService.getAllQuestions();
        assertEquals(2, questions.size(), "Должен вернуть все добавленные вопросы.");
    }

    @Test
    void getRandomQuestion_shouldReturnOneRandomRussianQuestion() {
        questionService.addQuestion("Что такое класс?", "Шаблон для создания объектов.");
        questionService.addQuestion("Что такое объект?", "Экземпляр класса.");
        Question randomQuestion = questionService.getRandomQuestion();
        assertNotNull(randomQuestion, "Случайный вопрос не должен быть пустым.");
        assertTrue(questionService.getAllQuestions().contains(randomQuestion), "Случайный вопрос должен быть из списка.");
    }
}
