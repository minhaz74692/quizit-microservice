package com.mie.Quizit.Service;

import com.mie.Quizit.Repository.QuestionRepository;
import com.mie.Quizit.Repository.QuizRepository;
import com.mie.Quizit.model.Question;
import com.mie.Quizit.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
private  final QuestionRepository questionRepository;
    public ResponseEntity<String> createQuiz(String category, Integer numOfQ, String title) {

         List<Question> questions = questionRepository.getRandomQuestionByCategory(category, numOfQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);
       return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
