package Game.Anagram;

import static Dictionary.Dictionary.dictionaryApp.anagramGameControl;
import static Dictionary.Dictionary.dictionaryApp.anagramGameScene;
import static Dictionary.Dictionary.dictionaryApp.anagramMainMenuScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class anagramModeController {
  @FXML
  private Label difficulty;

  @FXML
  void easyMode(ActionEvent event) {
    Stage stage = (Stage) difficulty.getScene().getWindow();
    stage.setScene(anagramGameScene);
    anagramGameControl.startGame(10, 2, 4);
  }

  @FXML
  void hardMode(ActionEvent event) {
    Stage stage = (Stage) difficulty.getScene().getWindow();
    stage.setScene(anagramGameScene);
    anagramGameControl.startGame(30, 4, 5);
  }

  @FXML
  void mediumMode(ActionEvent event) {
    Stage stage = (Stage) difficulty.getScene().getWindow();
    stage.setScene(anagramGameScene);
    anagramGameControl.startGame(60, 5, 7);
  }

  @FXML
  void toMenu(ActionEvent event) {
    Stage stage = (Stage) difficulty.getScene().getWindow();
    stage.setScene(anagramMainMenuScene);
  }
}
