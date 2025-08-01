package com.mie.Quizit.controller;

import com.mie.Quizit.DTO.QuestionRequestDTO;
import com.mie.Quizit.Service.QuestionService;
import com.mie.Quizit.model.Question;
import com.mie.Quizit.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createQuestion(@RequestBody QuestionRequestDTO dto) {
        return  new ResponseEntity<>(new ApiResponse("Success", questionService.createQuestion(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
