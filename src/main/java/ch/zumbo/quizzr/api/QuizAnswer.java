package ch.zumbo.quizzr.api;

class QuizAnswer {
    private long id;
    private String answer;

    long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
