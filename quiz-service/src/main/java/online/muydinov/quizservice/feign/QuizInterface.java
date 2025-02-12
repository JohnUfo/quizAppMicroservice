package online.muydinov.quizservice.feign;

import online.muydinov.quizservice.dto.ResponseDTO;
import online.muydinov.quizservice.wrapper.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions);

    @PostMapping("/questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Long> questionIds);

    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDTO> responseDTO);
}
