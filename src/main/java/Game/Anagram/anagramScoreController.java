package Game.Anagram;

import static Dictionary.dictionaryApp.anagramGameControl;
import static Dictionary.dictionaryApp.anagramGameScene;
import static Dictionary.dictionaryApp.anagramMainMenuScene;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class anagramScoreController implements Initializable {
  @FXML private Label lblScore = new Label();
  public void initialize(URL url, ResourceBundle rb) {
    setFinalScore("0");
  }

  public void setFinalScore(String x) {
    lblScore.setText(x);
  }

  @FXML
  void retry(MouseEvent event) {
    Stage stage = (Stage) lblScore.getScene().getWindow();
    stage.setScene(anagramGameScene);
    anagramGameControl.startGame(
        anagramGameControl.getTimeLimit(), anagramGameControl.getMinLength(), anagramGameControl.getMaxLength());
  }

  @FXML
  void toMenu(MouseEvent event) {
    Stage stage = (Stage) lblScore.getScene().getWindow();
    stage.setScene(anagramMainMenuScene);
  }
}
