package Game.Anagram;

import static Dictionary.Dictionary.dictionaryApp.anagramModeScene;
import static Dictionary.Dictionary.dictionaryApp.dictionaryScene;
import static Dictionary.Dictionary.dictionaryApp.gameSelectionScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class anagramMainMenuController {
  @FXML
  private Button exitBtn = new Button();

  @FXML
  private Button newGameBtn = new Button();

  @FXML
  void exitGame(ActionEvent event) {
    Stage stage = (Stage) exitBtn.getScene().getWindow();
    stage.setScene(gameSelectionScene);
  }

  @FXML
  void newGame(ActionEvent event) {
    Stage stage = (Stage) exitBtn.getScene().getWindow();
    stage.setScene(anagramModeScene);
  }

}
