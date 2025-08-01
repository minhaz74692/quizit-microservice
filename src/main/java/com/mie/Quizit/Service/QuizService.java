package com.mie.Quizit.Service;

import com.mie.Quizit.Repository.QuestionRepository;
import com.mie.Quizit.Repository.QuizRepository;
import com.mie.Quizit.model.Question;
import com.mie.Quizit.model.QuestionWrapper;
import com.mie.Quizit.model.Quiz;
import com.mie.Quizit.model.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser =  questionsFromDB.stream()
                .map(q ->  QuestionWrapper.builder()
                        .id(q.getId())
                        .title(q.getTitle())
                        .description(q.getDescription())
                        .difficultyLevel(q.getDifficultyLevel())
                        .category(q.getCategory())
                        .options(q.getOptions())
                        .build()
                ).collect(Collectors.toList());

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getQuizResult(Long id,List<QuizResponse> response) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int result = 0;
        int i = 0;

        for(QuizResponse q:response){

            if(q.getResponse().equals(questions.get(i).getCorrectAnswer()))
                result++;
            i++;
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}
