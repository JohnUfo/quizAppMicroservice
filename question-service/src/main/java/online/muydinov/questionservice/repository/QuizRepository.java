package online.muydinov.questionservice.repository;

import online.muydinov.questionservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}