package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static Dictionary.dictionaryApp.*;

public class MainMenuController {
  @FXML
  private Button exitBtn = new Button();

  @FXML
  private Button newAnagramGameBtn = new Button();
  @FXML
  private Button newQuizGameBtn = new Button();
  @FXML
  void exit(ActionEvent event) {
    Stage stage = (Stage) exitBtn.getScene().getWindow();
    stage.setScene(dictionaryScene);
  }

  @FXML
  void newAnagramGame(ActionEvent event) {
    Stage stage = (Stage) newAnagramGameBtn.getScene().getWindow();
    stage.setScene(anagramModeScene);
  }
  @FXML
  void newQuizGame(ActionEvent event) {
    Stage stage = (Stage) newQuizGameBtn.getScene().getWindow();
    stage.setScene(quizGameScene);
  }
}
