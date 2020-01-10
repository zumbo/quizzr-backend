package ch.zumbo.quizzr.admin;

import ch.zumbo.quizzr.data.Answer;
import ch.zumbo.quizzr.data.Question;

import java.util.ArrayList;
import java.util.List;

class AdminQuestion {
    private long id;
    private String text;
    private String answer;
    private List<String> wrongAnswers;

    AdminQuestion() {
        // Wird von Jackson-Deserialisierung benötigt.
    }

    AdminQuestion(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.answer = question.getAnswer();
        this.wrongAnswers = new ArrayList<>();
        for (Answer wrongAnswer : question.getWrongAnswers()) {
            this.wrongAnswers.add(wrongAnswer.getText());
        }
    }

    Question asQuestion() {
        Question question = new Question();
        question.setId(id);
        question.setText(text);
        question.setAnswer(answer);
        for (String wrongAnswerText : wrongAnswers) {
            Answer wrongAnswer = new Answer();
            wrongAnswer.setQuestionId(id);
            wrongAnswer.setText(wrongAnswerText);
            question.getWrongAnswers().add(wrongAnswer);
        }
        return question;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }
}
