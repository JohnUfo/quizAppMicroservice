package online.muydinov.questionservice.repository;

import online.muydinov.questionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT id FROM questions WHERE category = :category ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> getRandomQuestionsByCategoryAndLimit(@Param("category") String category, @Param("numQuestions") int numQ);
}