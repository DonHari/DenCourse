package sample;

public class Question implements Comparable<Question> {

    /**
     * Используется для определения уникальности. С его помощью можно расположить вопросы в нужном порядке
     */
    private Integer id;
    /**
     * Сам вопрос
     */
    private String question;
    /**
     * Ответ, который поведет по левой ветке
     */
    private String leftAnswer;
    /**
     * Ответ, который поведет по правой ветке
     */
    private String rightAnswer;
    /**
     * Результат ответа на вопрос. Используется только в случае, если вопрос последний и после него ничего не идет
     */
    private String result;

    public Question(Integer id, String question, String leftAnswer, String rightAnswer) {
        this.question = question;
        this.leftAnswer = leftAnswer;
        this.rightAnswer = rightAnswer;
        this.id = id;
    }

    public Question(Integer id, String result) {
        this.id = id;
        this.result = result;
    }

    @Override
    public int compareTo(Question question) {
        return this.id - question.getId();
    }

    public Integer getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public String getQuestion() {
        return question;
    }

    public String getLeftAnswer() {
        return leftAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

}
