package ch.zumbo.quizzr.admin;

import ch.zumbo.quizzr.data.Question;
import ch.zumbo.quizzr.data.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions/{id}")
    public Question getQuestion(long id) {
        return questionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @PostMapping("/questions")
    public Question insertQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @PutMapping("/questions/{id}")
    public Question updateQuestion(long id, @RequestBody Question question) {
        question.setId(id);
        return questionRepository.save(question);
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(long id) {
        questionRepository.deleteById(id);
    }


}
