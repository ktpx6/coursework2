package com.example.exam.service;

import com.example.exam.exception.BadRequestException;
import com.example.exam.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {
    private ExaminerServiceImpl examinerService;
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = mock(JavaQuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void getQuestions_shouldReturnExactAmountOfUniqueRussianQuestions() {
        when(questionService.getAllQuestions()).thenReturn(List.of(
                new Question("Что такое наследование?", "Механизм передачи свойств."),
                new Question("Что такое интерфейс?", "Контракт, который должен реализовать класс."),
                new Question("Что такое абстракция?", "Способ сокрытия деталей.")
        ));

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Что такое наследование?", "Механизм передачи свойств."),
                new Question("Что такое интерфейс?", "Контракт, который должен реализовать класс.")
        );

        List<Question> questions = examinerService.getQuestions(2);
        assertEquals(2, questions.size(), "Должен вернуть два уникальных вопроса.");
        assertTrue(questions.stream().distinct().count() == questions.size(), "Все вопросы должны быть уникальны.");
    }

    @Test
    void getQuestions_shouldThrowExceptionIfAmountExceedsAvailableRussianQuestions() {
        when(questionService.getAllQuestions()).thenReturn(List.of(
                new Question("Что такое инкапсуляция?", "Сокрытие деталей реализации.")
        ));

        assertThrows(BadRequestException.class, () -> examinerService.getQuestions(2),
                "Должен выбросить исключение при запросе большего количества вопросов.");
    }

    @Test
    void getQuestions_shouldReturnUniqueQuestionsWhenAmountIsLessThanAvailable() {
        when(questionService.getAllQuestions()).thenReturn(List.of(
                new Question("Что такое конструктор?", "Метод для инициализации объекта."),
                new Question("Что такое пакет?", "Структура для организации классов."),
                new Question("Что такое статический метод?", "Метод, относящийся к классу, а не объекту.")
        ));

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Что такое конструктор?", "Метод для инициализации объекта."),
                new Question("Что такое пакет?", "Структура для организации классов.")
        );

        List<Question> questions = examinerService.getQuestions(2);
        assertEquals(2, questions.size(), "Должен вернуть точное количество уникальных вопросов.");
        assertEquals(2, questions.stream().distinct().count(), "Вопросы должны быть уникальными.");
    }

    @Test
    void getQuestions_shouldHandleMultipleRandomSelections() {
        when(questionService.getAllQuestions()).thenReturn(List.of(
                new Question("Что такое JVM?", "Виртуальная машина Java."),
                new Question("Что такое JDK?", "Набор инструментов для разработки на Java."),
                new Question("Что такое JRE?", "Среда выполнения Java.")
        ));

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Что такое JVM?", "Виртуальная машина Java."),
                new Question("Что такое JDK?", "Набор инструментов для разработки на Java.")
        );

        List<Question> questions = examinerService.getQuestions(2);
        assertEquals(2, questions.size(), "Должен вернуть точное количество вопросов.");
        assertTrue(questions.stream().allMatch(q -> q.getQuestion().startsWith("Что такое")),
                "Каждый вопрос должен соответствовать добавленным вопросам.");
    }
}
