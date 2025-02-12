package online.muydinov.quizservice.service;

import lombok.RequiredArgsConstructor;
import online.muydinov.quizservice.dto.ResponseDTO;
import online.muydinov.quizservice.entity.Quiz;
import online.muydinov.quizservice.feign.QuizInterface;
import online.muydinov.quizservice.repository.QuizRepository;
import online.muydinov.quizservice.wrapper.QuestionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {

        List<Long> questions = quizInterface.getQuestionsForQuiz(category, numQuestions).getBody();
        Quiz quiz = new Quiz(null, title, questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("success", CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuizId(Long id) {
//        return quizRepository.findById(id)
//                .map(quiz -> quiz.getQuestions().stream()
//                        .map(q -> QuestionWrapper.builder()
//                                .id(q.getId())
//                                .title(q.getTitle())
//                                .option1(q.getOption1())
//                                .option2(q.getOption2())
//                                .option3(q.getOption3())
//                                .option4(q.getOption4())
//                                .build())
//                        .toList())
//                .map(questions -> new ResponseEntity<>(questions, OK))
//                .orElseGet(() -> ResponseEntity.notFound().build());
        return new ResponseEntity<>(OK);
    }

    public ResponseEntity<Integer> submitQuiz(Long quizId, List<ResponseDTO> responseDto) {
//        Quiz quiz = quizRepository.findById(quizId).get();
//        List<Question> questions = quiz.getQuestions();
        int right = 0, i = 0;
//
//        for (ResponseDTO responseDTO : responseDto) {
//            if (responseDTO.getResponse().equals(questions.get(i).getCorrectAnswer())) {
//                right++;
//                System.out.println(responseDTO.getResponse());
//            }
//            i++;
//        }
        return new ResponseEntity<>(right, OK);
    }
}
