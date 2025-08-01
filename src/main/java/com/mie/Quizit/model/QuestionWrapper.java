package com.mie.Quizit.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class QuestionWrapper {
    private Long id;
    private String title;
    private String category;
    private String difficultyLevel;
    private List<String> options;
    private String description;
}
