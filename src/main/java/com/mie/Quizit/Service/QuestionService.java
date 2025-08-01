package com.mie.Quizit.Service;

import com.mie.Quizit.DTO.QuestionRequestDTO;
import com.mie.Quizit.model.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(QuestionRequestDTO dto);
    List<Question> getAllQuestions();
    Question getQuestionById(Long id);
    void deleteQuestion(Long id);
}

