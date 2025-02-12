package online.muydinov.quizservice.controller;

import lombok.RequiredArgsConstructor;
import online.muydinov.quizservice.dto.QuizDto;
import online.muydinov.quizservice.dto.ResponseDTO;
import online.muydinov.quizservice.service.QuizService;
import online.muydinov.quizservice.wrapper.QuestionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id) {
        return quizService.getQuestionsByQuizId(id);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long quizId, @RequestBody List<ResponseDTO> responseDto) {
        return quizService.submitQuiz(quizId, responseDto);
    }
}
