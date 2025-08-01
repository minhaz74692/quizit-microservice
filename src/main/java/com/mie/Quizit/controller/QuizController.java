package com.mie.Quizit.controller;

import com.mie.Quizit.Service.QuizService;
import com.mie.Quizit.model.QuestionWrapper;
import com.mie.Quizit.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numOfQ, @RequestParam String title){
        return quizService.createQuiz(category, numOfQ, title);
    }


    @GetMapping("get/{id}")
    public  ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Long id){
        return  quizService.getQuizById(id);
    }
}
