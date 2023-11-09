package Game.Anagram;

import static Dictionary.Dictionary.dictionaryApp.anagramModeScene;
import static Dictionary.Dictionary.dictionaryApp.gameSelectionScene;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class anagramMainMenuController implements Initializable {
  @FXML
  private Button exitBtn = new Button();

  @FXML
  private Button newAnagramGameBtn = new Button();

  String getFile(String path) {
    return new File(path).toURI().toString();
  }

  ImageView getImage(String img) {
    ImageView ret = new ImageView(new Image(getFile(
        "src/main/resources/Images/" + img + ".png")));
    ret.setFitHeight(105);
    ret.setFitWidth(230);
    return ret;
  }

  public void initialize(URL url, ResourceBundle rb) {
    newAnagramGameBtn.setGraphic(getImage("newGame"));
    exitBtn.setGraphic(getImage("exitToDict"));
  }

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
