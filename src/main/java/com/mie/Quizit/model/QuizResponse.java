package com.mie.Quizit.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizResponse {
    private Long id;
    private String response;
}
