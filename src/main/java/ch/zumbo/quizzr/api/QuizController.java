package ch.zumbo.quizzr.api;

import ch.zumbo.quizzr.data.AnswerRepository;
import ch.zumbo.quizzr.data.Question;
import ch.zumbo.quizzr.data.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api")
@CrossOrigin
public class QuizController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/question")
    public QuizQuestion getQuestion() {
        long count = questionRepository.count();
        int index = (int)(Math.random() * count);
        Page<Question> questionPage =
                questionRepository.findAll(PageRequest.of(index, 1));
        Question question;
        if (questionPage.hasContent()) {
            question = questionPage.getContent().get(0);
        } else {
            throw new NoSuchElementException("No question found");
        }

        return new QuizQuestion(question);
    }

    @PostMapping("/checkAnswer")
    public boolean checkAnswer(@RequestBody QuizAnswer quizAnswer) {
        Question question = questionRepository.findById(quizAnswer.getId()).orElseThrow(NoSuchElementException::new);
        return question.getAnswer().equals(quizAnswer.getAnswer());
    }

}
