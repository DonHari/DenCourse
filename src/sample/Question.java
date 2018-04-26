package sample;

public class Question {
    private String question;
    private String result;
    private String help;

    Question(String question, String result, String help) {
        this.question = question;
        this.result = result;
        this.help = help;
    }

    public String getResult() {
        return result;
    }

    public String getQuestion() {
        return question;
    }

    public String getHelp() {
        return help;
    }
}
