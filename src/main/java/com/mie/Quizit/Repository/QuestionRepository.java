package com.mie.Quizit.Repository;

import com.mie.Quizit.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM questions q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numOfQ", nativeQuery = true)
    List<Question> getRandomQuestionByCategory(@Param("category") String category, @Param("numOfQ") int numOfQ);
}

