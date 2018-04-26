package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.tree.BinaryTree;
import sample.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Controller {
    @FXML
    private RadioButton firstAnswer;
    @FXML
    private RadioButton secondAnswer;
    @FXML
    private RadioButton thirdAnswer;
    @FXML
    private Label questionLabel;
    @FXML
    private Button previousButton;
    @FXML
    private ToggleGroup answerToggleGroup;

    private BinaryTree tree;
    private Integer questionNumber = 1;

    @FXML
    public void initialize() {
        createQuestions();
        showQuestion(tree.getCurrent());
    }

    private void createQuestions() {
        TreeNode head = new TreeNode(
                new Question("как дела?", null, "бог тебе в помощь"),
                null
        );
        Map<String, TreeNode> children = new HashMap<>();
        children.put("Хорошо", new TreeNode(new Question(null, "Не может быть такого", "Небо поможет нам, но не тебе))))00)"), head));
        children.put("Нормельно", new TreeNode(new Question(null, "Ну ок", "Хватит меня нажимать"), head));
        children.put("Плохо", new TreeNode(new Question("Че так?", null, "Тебе скучно?"), head));
        head.setChildren(children);

        children = new HashMap<>();
        children.put("Хз", new TreeNode(new Question(null, "Ты сначала определись, а потом проходи тест", "Все равно не помогу"), children.get("Плохо")));
        children.put("Потому что", new TreeNode(new Question(null, "Ну и иди ты", "Ну тебя нах"), children.get("Плохо")));
        head.getChildren().get("Плохо").setChildren(children);

        tree = new BinaryTree();
        tree.setTree(head);
    }

    public void previousButtonClicked() {
        questionNumber -= 2;
        showQuestion(tree.prev());
    }

    public void answerButtonClicked() {
        TreeNode nextQuestion;
        if (answerToggleGroup.getSelectedToggle() != null) {
            RadioButton selected = (RadioButton) answerToggleGroup.getSelectedToggle();
            nextQuestion = tree.next(selected.getText());

            showQuestion(nextQuestion);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран ответ");
            alert.setContentText("Для прохождения теста выберите один из предложенных вариантов ответа");
            alert.showAndWait();
        }
    }

    private void showQuestion(TreeNode questionNode) {
        if (questionNode.getParent() == null) {
            previousButton.setDisable(true);
        } else {
            previousButton.setDisable(false);
        }

        if (questionNode.getValue().getQuestion() == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Тест пройден!");
            alert.setHeaderText(questionNode.getValue().getResult());
            alert.setContentText("Вы хотите пройти тест заново? Если нет, то программа будет закрыта.");
            Optional<ButtonType> buttonTypeOptional = alert.showAndWait();
            if (buttonTypeOptional.get() == ButtonType.OK) {
                tree.startFromHead();
                questionNumber = 1;
                showQuestion(tree.getCurrent());
            } else {
                Stage stage = (Stage) questionLabel.getScene().getWindow();
                stage.close();
            }
        } else {
            questionLabel.setText("Вопрос " + questionNumber + ". " + questionNode.getValue().getQuestion());
            questionNumber++;
            Map<String, TreeNode> children = questionNode.getChildren();
            String[] answers = children.keySet().toArray(new String[0]);
            firstAnswer.setText(answers[0]);
            secondAnswer.setText(answers[1]);
            if (answers.length == 2) {
                thirdAnswer.setDisable(true);
                thirdAnswer.setVisible(false);
            } else {
                thirdAnswer.setDisable(false);
                thirdAnswer.setText(answers[2]);
                thirdAnswer.setVisible(true);
            }
            answerToggleGroup.selectToggle(null);
        }
    }

    public void startTestAgain() {
        questionNumber = 1;
        tree.startFromHead();
        showQuestion(tree.getCurrent());
    }

    public void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Помощь");
        alert.setHeaderText("Помощь к вопросу: \"" + tree.getCurrent().getValue().getQuestion() + "\"");
        alert.setContentText(tree.getCurrent().getValue().getHelp());
        alert.showAndWait();
    }
}
