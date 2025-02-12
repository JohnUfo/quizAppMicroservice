package online.muydinov.questionservice.service;

import lombok.RequiredArgsConstructor;
import online.muydinov.questionservice.dto.ResponseDTO;
import online.muydinov.questionservice.entity.Question;
import online.muydinov.questionservice.repository.QuestionRepository;
import online.muydinov.questionservice.wrapper.QuestionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        return questionRepository.findById(id).map(question -> {
            question.setTitle(updatedQuestion.getTitle());
            question.setOption1(updatedQuestion.getOption1());
            question.setOption2(updatedQuestion.getOption2());
            question.setOption3(updatedQuestion.getOption3());
            question.setOption4(updatedQuestion.getOption4());
            question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
            question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            question.setCategory(updatedQuestion.getCategory());
            return questionRepository.save(question);
        }).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public ResponseEntity<List<Long>> getQuestionsForQuiz(String category, Integer numQuestions) {
        List<Long> questionsId = questionRepository.getRandomQuestionsByCategoryAndLimit(category, numQuestions);
        return new ResponseEntity<>(questionsId, OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Long> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Long id : questionIds) {
            questions.add(questionRepository.findById(id).get());
        }

        for (Question question : questions) {
            QuestionWrapper.QuestionWrapperBuilder wrapper = QuestionWrapper.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .option1(question.getOption1())
                    .option2(question.getOption2())
                    .option3(question.getOption3())
                    .option4(question.getOption4())
                    .difficultyLevel(question.getDifficultyLevel())
                    .category(question.getCategory());
            wrappers.add(wrapper.build());
        }

        return new ResponseEntity<>(wrappers, OK);
    }

    public ResponseEntity<Integer> getScore(List<ResponseDTO> responseDto) {

        int right = 0;

        for (ResponseDTO responseDTO : responseDto) {
            Question question = questionRepository.findById(responseDTO.getId()).get();
            if (responseDTO.getResponse().equals(question.getCorrectAnswer())) {
                right++;
            }
        }
        return new ResponseEntity<>(right, OK);
    }
}