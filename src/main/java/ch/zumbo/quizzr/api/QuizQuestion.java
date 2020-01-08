package ch.zumbo.quizzr.api;

import ch.zumbo.quizzr.data.Answer;
import ch.zumbo.quizzr.data.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizQuestion {
    private long id;
    private String question;
    private List<String> answers;

    QuizQuestion(Question question) {
        id = question.getId();
        this.question = question.getText();
        List<String> unrandomizedAnswers = new ArrayList<>();
        unrandomizedAnswers.add(question.getAnswer());
        List<String> wrongAnswers =
                question.getWrongAnswers().stream().map(Answer::getText).collect(Collectors.toList());
        unrandomizedAnswers.addAll(wrongAnswers);
        answers = new ArrayList<>();
        while (unrandomizedAnswers.size() > 0) {
            int index = (int) (Math.random() * unrandomizedAnswers.size());
            answers.add(unrandomizedAnswers.get(index));
            unrandomizedAnswers.remove(index);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

}
