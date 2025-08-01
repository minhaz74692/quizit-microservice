package com.mie.Quizit.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {
    private String title;
    private String category;
    private String difficultyLevel;
    private List<String> options;
    private String correctAnswer;
    private String description;
}
