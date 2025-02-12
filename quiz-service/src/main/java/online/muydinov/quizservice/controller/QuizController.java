package online.muydinov.quizservice.controller;

import lombok.RequiredArgsConstructor;
import online.muydinov.questionservice.dto.ResponseDTO;
import online.muydinov.questionservice.service.QuizService;
import online.muydinov.questionservice.wrapper.QuestionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
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
