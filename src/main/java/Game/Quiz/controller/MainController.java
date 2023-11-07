package Game.Quiz.controller;

import Dictionary.dictionaryApp;
import Game.Quiz.models.QuizModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Dictionary.dictionaryApp.anagramMainMenuScene;
import static Dictionary.dictionaryApp.dictionaryScene;

public class MainController implements Initializable {
    private static QuizModel model;

    @FXML
    public void showQuestionSelectView(ActionEvent event){
        ScreenController.goQuestionSelect( getClass(),event);
    }

    @FXML
    public void showCurrentWinningView(ActionEvent event) throws IOException {
        ScreenController.goCurrentWinning(getClass(),event);
    }

    @FXML
    public void resetButtonClick(ActionEvent event) throws IOException{
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

    @FXML
    public void quitButtonClick(ActionEvent event) throws IOException {
        if (model != null) {
            model.save();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(anagramMainMenuScene);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = dictionaryApp.getQuizModel();
    }

}
