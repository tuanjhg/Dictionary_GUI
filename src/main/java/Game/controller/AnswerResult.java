package Game.controller;

import Dictionary.dictionaryApp;
import Game.models.Question;
import Game.models.QuizModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AnswerResult {
    private QuizModel model;

    @FXML
    private Label winningLabel;
    @FXML
    private Label isCorrectLabel;
    @FXML
    private Label correctAnsLabel;
    @FXML
    private HBox bottomHBox;

    @FXML
    public void goMainMenu(ActionEvent event)throws IOException {
        ScreenController.goMainMenu(getClass(), event);
    }

    @FXML
    public void goQuestionSelect(ActionEvent event) {
        ScreenController.goQuestionSelect(getClass(), event);
    }

    public void validAnswerInit(Question question) {
        model = dictionaryApp.getQuizModel();
        isCorrectLabel.setText("Correct");

        String scoreStr = model.getWinningStr();
        winningLabel.setText(scoreStr);
        checkGameOverStatus();
    }

    public void invalidAnswerInit(Question question) {
        model = dictionaryApp.getQuizModel();
        isCorrectLabel.setText("Incorrect");

        String scoreStr = model.getWinningStr();
        winningLabel.setText(scoreStr);

        String correctAnsStr = "The correct answer is " + question.getAnswer() + ".";
        correctAnsLabel.setText(correctAnsStr);
        checkGameOverStatus();
    }

    private void checkGameOverStatus() {
        if (model.getRemainingQuestionCount() == 0) {
            bottomHBox.getChildren().clear();
            Button button = new Button("Next");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ScreenController.goGameOver(getClass(), event);
                }
            });
            bottomHBox.getChildren().add(button);
        }
    }
}

