package Game.Quiz.controller;

import Dictionary.dictionaryApp;
import Game.Quiz.models.QuizModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CurrentWinning implements Initializable {
    private QuizModel model;

    @FXML
    private Label winningLabel;

    @FXML
    public void goMainMenu(ActionEvent event) throws IOException {
        ScreenController.goMainMenu(getClass(),event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = dictionaryApp.getQuizModel();
        String scoreStr = model.getWinningStr();
        winningLabel.setText(scoreStr);

    }


}
