package online.muydinov.quizservice.dto;

import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer numQuestions;
    String title;
}
