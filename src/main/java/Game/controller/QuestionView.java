package Game.controller;

import Dictionary.dictionaryApp;
import Game.models.Question;
import Game.models.QuizModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionView implements Initializable {
    private QuizModel model;
    private Question question;

    @FXML private Label questionLabel;
    @FXML private TextField textfield;

    @FXML
    public void goMainMenu(ActionEvent event) throws IOException {
        ScreenController.goMainMenu(getClass(),event);

    }
    @FXML
    public void centerButton(ActionEvent event) {
        submitAnswer(event);
    }

    @FXML
    public void onEnter(ActionEvent event) throws IOException {
        submitAnswer(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = dictionaryApp.getQuizModel();
        question = model.getActiveQuestion();
        questionLabel.setText(question.toString());

    }

    public void submitAnswer(ActionEvent event) {
        String input = textfield.getText();
        boolean isCorrect = model.answerQuestion(question, input);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Game/AnswerResult.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            AnswerResult controller = loader.getController();

            if (isCorrect) {
                controller.validAnswerInit(question);
            } else {
                controller.invalidAnswerInit(question);
            }

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
