package Game.Anagram;

import static Dictionary.dictionaryApp.anagramModeScene;
import static Dictionary.dictionaryApp.dictionaryScene;

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
  void exit(ActionEvent event) {
    Stage stage = (Stage) exitBtn.getScene().getWindow();
    stage.setScene(dictionaryScene);
  }

  @FXML
  void newGame(ActionEvent event) {
    Stage stage = (Stage) exitBtn.getScene().getWindow();
    stage.setScene(anagramModeScene);
  }

}
