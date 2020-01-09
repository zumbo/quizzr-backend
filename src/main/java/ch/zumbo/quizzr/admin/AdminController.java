package ch.zumbo.quizzr.admin;

import ch.zumbo.quizzr.data.Question;
import ch.zumbo.quizzr.data.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions/{id}")
    public AdminQuestion getQuestion(@PathVariable long id) {
        Question question = questionRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new AdminQuestion(question);
    }

    @GetMapping("/questions")
    public List<AdminQuestion> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<AdminQuestion> adminQuestions = new ArrayList<>();
        for (Question question : questions) {
            adminQuestions.add(new AdminQuestion(question));
        }
        return adminQuestions;
    }

    @PostMapping("/questions")
    public AdminQuestion insertQuestion(@RequestBody AdminQuestion question) {
        return new AdminQuestion(questionRepository.save(question.asQuestion()));
    }

    @PutMapping("/questions/{id}")
    public AdminQuestion updateQuestion(@PathVariable long id, @RequestBody AdminQuestion question) {
        return new AdminQuestion(questionRepository.save(question.asQuestion()));
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable long id) {
        questionRepository.deleteById(id);
    }


}
