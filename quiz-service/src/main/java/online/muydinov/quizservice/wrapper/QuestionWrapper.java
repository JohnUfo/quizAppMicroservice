package online.muydinov.quizservice.wrapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionWrapper {
    private Long id;
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficultyLevel;
    private String category;
}
