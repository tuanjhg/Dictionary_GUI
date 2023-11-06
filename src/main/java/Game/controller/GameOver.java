package Game.controller;

import Dictionary.dictionaryApp;
import Game.models.QuizModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameOver implements Initializable {
    private QuizModel model;

    @FXML
    private Label winningLabel;
    @FXML
    public void goMainMenu(ActionEvent event) throws IOException {
        ScreenController.goMainMenu(getClass(),event);
    }
    @FXML
    public void resetButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to reset the game? Your save will be reset to its initial status. This can not be undone.",
                ButtonType.YES, ButtonType.NO
        );
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Rest Confirmation");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            model.reset();
        }
        else {
            event.consume();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            model = dictionaryApp.getQuizModel();
            String scoreStr = model.getWinningStr();
            winningLabel.setText(scoreStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
