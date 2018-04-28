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
        TreeNode head = new TreeNode(new Question("Какой вид оплаты тарифного плана для Вас предпочтительней?", ""), null);
        TreeNode node1 = new TreeNode(new Question("Что Вас больше интересует?", "Вам задали этот вопрос, потому что Вас интересует предоплата"), head);
        TreeNode node2 = new TreeNode(new Question("Вас интересуют звонки за границу?", "Вам задали этот вопрос, потому что Вас интересует контрактный вид оплаты "), head);
        Map<String, TreeNode> children = new HashMap<>();
        children.put("Предоплата", node1);
        children.put("Контракт", node2);
        head.setChildren(children);

        TreeNode node11 = new TreeNode(new Question("Вам нужен скоростной интернет?", "Вам задали этот вопрос, потому что Вас интересует предоплата и интернет"), node1);
        TreeNode node12 = new TreeNode(new Question("Вас интересуют звонки внутри страны?", "Вам задали этот вопрос, потому что Вас интересует предоплата и звонки"), node1);
        children = new HashMap<>();
        children.put("Интернет", node11);
        children.put("Звонки", node12);
        node1.setChildren(children);


        TreeNode node111 = new TreeNode(new Question("Какое количество МБ вам необходимо?", "Вам задали этот вопрос, потому что Вас интересует предоплата и скоростной интернет"), node11);
        TreeNode node112 = new TreeNode(new Question("Вы будете проводить время в мессенджерах?", "Вам задали этот вопрос, потому что Вас интересует предоплата и Вам не нужен скоростной интернет"), node11);
        children = new HashMap<>();
        children.put("Да", node111);
        children.put("Нет", node112);
        node11.setChildren(children);

        TreeNode node1111 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Безлим Соцсети\""), node111);
        TreeNode node1112 = new TreeNode(new Question("Вы будете просматривать видео?", "Вам задали этот вопрос, потому что Вас интересует предоплата, интернет, большое кол-во МБ"), node111);
        children = new HashMap<>();
        children.put("До 1 ГБ", node1111);
        children.put("Больше 1 ГБ", node1112);
        node111.setChildren(children);


        TreeNode node11111 = new TreeNode(new Question("Вы будете качать медиа файлы?", "Вам задали этот вопрос, потому что Вас интересует предоплата, интернет и не интересует просмотр видео"), node1111);
        TreeNode node11112 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Безлим Видео\""), node1111);
        children = new HashMap<>();
        children.put("Нет", node11111);
        children.put("Да", node11112);
        node1112.setChildren(children);

        TreeNode node111111 = new TreeNode(new Question("Вам подойдет оператор \"Lifecell\". Т.П. - \"Безумный год\""), node11111);
        TreeNode node111112 = new TreeNode(new Question("Вам подойдет оператор \"Lifecell\". Т.П. - \"Оптимальный смартфон\""), node11111);
        children = new HashMap<>();
        children.put("Да", node111111);
        children.put("Нет", node111112);
        node11111.setChildren(children);

        TreeNode node1121 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Все вместе\""), node112);
        TreeNode node1122 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Всегда Онлайн\""), node112);
        children = new HashMap<>();
        children.put("Нет", node1121);
        children.put("Да", node1122);
        node112.setChildren(children);

        TreeNode node121 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Соседние страны\""), node12);
        TreeNode node122 = new TreeNode(new Question("Вы заинтерисованы в звонках внутри сети?", "Вам задали этот вопрос, потому что Вас интересует предоплата, звонки внутри страны"), node12);
        children = new HashMap<>();
        children.put("Нет", node121);
        children.put("Да", node122);
        node12.setChildren(children);

        TreeNode node1221 = new TreeNode(new Question("Вам подойдет оператор \"Vodafone\". Т.П. - \"Unlim talk\""), node122);
        TreeNode node1222 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Абсолютная свобода\""), node122);
        TreeNode node1223 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Безлим разговоры\""), node122);
        children = new HashMap<>();
        children.put("Нет", node1221);
        children.put("50 на 50", node1222);
        children.put("Да", node1223);
        node122.setChildren(children);

        TreeNode node21 = new TreeNode(new Question("Что для Вас наиболее важно?","Вам задали этот вопрос, потому что Вас интересует контракт и не интересны звонки за кордон"), node2);
        TreeNode node22 = new TreeNode(new Question("Вам подойдет оператор \"Vodafone\". Т.П. - \"Vodafone RED XL\""), node2);
        children = new HashMap<>();
        children.put("Нет", node21);
        children.put("Да", node22);
        node2.setChildren(children);

        TreeNode node211 = new TreeNode(new Question("Вам подойдет оператор \"Киевстар\". Т.П. - \"Комфортные разговоры\""), node21);
        TreeNode node212 = new TreeNode(new Question("Вы совершаете много звонков на номера других операторов связи?", "Вам задали этот вопрос, потому что Вас интересует контракт и интересны звонки по стране"), node21);
        TreeNode node213 = new TreeNode(new Question("Вам подойдет оператор \"Lifecell\". Т.П. - \"Лайфхак контракт\""), node21);
        children = new HashMap<>();
        children.put("Звонки внутри сети", node211);
        children.put("Звонки внутри и вне сети", node212);
        children.put("Интернет", node213);
        node21.setChildren(children);

        TreeNode node2121 = new TreeNode(new Question("Вам подойдет оператор \"Lifecell\". Т.П. - \"Контракт свобода\""), node212);
        TreeNode node2122 = new TreeNode(new Question("Вам подойдет оператор \"Lifecell\". Т.П. - \"Оптимальный контракт\""), node212);
        children = new HashMap<>();
        children.put("Нет", node2121);
        children.put("Да", node2122);
        node212.setChildren(children);

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
