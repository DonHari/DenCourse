package sample;

public class Question {
    private String question;
    private String result;
    private String help;

    Question(String question, String help) {
        this.question = question;
        this.help = help;
    }

    Question(String result){
        this.result = result;
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
