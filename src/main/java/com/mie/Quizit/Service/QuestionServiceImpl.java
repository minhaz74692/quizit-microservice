package com.mie.Quizit.Service;

import com.mie.Quizit.DTO.QuestionRequestDTO;
import com.mie.Quizit.Repository.QuestionRepository;
import com.mie.Quizit.exception.ResourceNotFoundException;
import com.mie.Quizit.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question createQuestion(QuestionRequestDTO dto) {
        Question question = Question.builder()
                .title(dto.getTitle())
                .category(dto.getCategory())
                .difficultyLevel(dto.getDifficultyLevel())
                .options(dto.getOptions())
                .correctAnswer(dto.getCorrectAnswer())
                .description(dto.getDescription())
                .build();

        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }
}
