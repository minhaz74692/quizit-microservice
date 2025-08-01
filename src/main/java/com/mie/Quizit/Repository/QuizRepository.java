package com.mie.Quizit.Repository;

import com.mie.Quizit.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
