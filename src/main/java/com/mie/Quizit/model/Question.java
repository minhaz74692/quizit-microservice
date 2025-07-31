package com.mie.Quizit.model;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    private String difficultyLevel;

    // Store 4 options as a JSONB array
    @Type(JsonType.class)
    @Column( columnDefinition = "jsonb")
    private List<String> options;

    private String correctAnswer;

    @Column(columnDefinition = "TEXT")
    private String description;
}

