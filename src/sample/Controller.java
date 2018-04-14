package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.tree.BinaryTree;
import sample.tree.TreeNode;

import java.util.Optional;

public class Controller {
    @FXML
    private RadioButton leftAnswer;
    @FXML
    private RadioButton rightAnswer;
    @FXML
    private Label questionLabel;
    @FXML
    private Button previousButton;

    private BinaryTree tree;
    private Integer questionNumber = 1;

    //вызывается при создании окна. Окно уже существует, но его пока что не видно (но это не точно)
    @FXML
    public void initialize() {
        createQuestions();
        showQuestion(tree.getCurrent());
    }

    /**
     * Метод для создания дерева
     */
    private void createQuestions() {
        tree = new BinaryTree();
        tree.add(new Question(4, "Как дела?", "Плохо", "Хорошо"));//head
        tree.add(new Question(2, "Че так?", "Хз", "В лолец хочу"));//head.left
        tree.add(new Question(1, "Ну и иди нах со своей неопределенностью"));//head.left.left
        tree.add(new Question(3, "Иди катай"));//head.left.right
        tree.add(new Question(5, "Пиздишь. В этой стране не бывает хорошего настроения"));//head.right
    }

    public void previousButtonClicked() {
        questionNumber -= 2;
        showQuestion(tree.prev());
    }

    public void answerButtonClicked() {
        TreeNode nextQuestion;
        if(leftAnswer.isSelected()) {
            nextQuestion = tree.next(BinaryTree.Direction.LEFT);
        } else if(rightAnswer.isSelected()) {
            nextQuestion = tree.next(BinaryTree.Direction.RIGHT);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран ответ");
            alert.setContentText("Необходимо выбрать ответ, чтобы двигаться дальше!");
            alert.showAndWait();
            return;
        }
        showQuestion(nextQuestion);
    }

    private void showQuestion(TreeNode questionNode) {
        if(questionNode.getParent() == null) {
            previousButton.setDisable(true);
        } else {
            previousButton.setDisable(false);
        }

        //show result
        if(questionNode.getValue().getQuestion() == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Тест пройден!");
            alert.setHeaderText(questionNode.getValue().getResult());
            alert.setContentText("Вы хотите пройти тест заново? Если нет, то программа будет закрыта.");
            Optional<ButtonType> buttonTypeOptional = alert.showAndWait();
            if(buttonTypeOptional.get() == ButtonType.OK) {
                tree.startFromHead();
                questionNumber = 1;
                showQuestion(tree.getCurrent());
            } else {
                Stage stage = (Stage) questionLabel.getScene().getWindow();
                stage.close();
            }
        } else {//show next question
            questionLabel.setText("Вопрос " + questionNumber + ". " + questionNode.getValue().getQuestion());
            questionNumber++;
            leftAnswer.setText(questionNode.getValue().getLeftAnswer());
            rightAnswer.setText(questionNode.getValue().getRightAnswer());
            leftAnswer.setSelected(false);
            rightAnswer.setSelected(false);
        }
    }

    public void startTestAgain() {
        questionNumber = 1;
        tree.startFromHead();
        showQuestion(tree.getCurrent());
    }
}
