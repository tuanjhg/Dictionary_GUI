package Dictionary.Dictionary;

import static Dictionary.Dictionary.dictionaryApp.anagramMainMenuScene;
import static Dictionary.Dictionary.dictionaryApp.dictionaryControl;
import static Dictionary.Dictionary.dictionaryApp.translateControl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class gameSelectionController extends baseMenu implements Initializable {
  @FXML
  private Button anagramBtn = new Button();
  @FXML
  private Button quizBtn = new Button();
  public void initialize(URL url, ResourceBundle rb) {
    super.initialize(url, rb);
    anagramBtn.setGraphic(getGameImg("src/main/resources/Images/anagram.png"));
  }
  ImageView getGameImg(String path) {
    ImageView ret = new ImageView(new Image(getFile(path)));
    ret.setFitHeight(225);
    ret.setFitWidth(225);
    return ret;
  }

  @FXML
  void menuAPI(ActionEvent event) throws IOException {
    mainMenu();
    switchToAPI();
    dictionaryControl.switchToAPI();
    translateControl.switchToAPI();
  }

  @FXML
  void menuBookmark(ActionEvent event) throws IOException {
    mainMenu();
    switchToBookmark();
    dictionaryControl.switchToBookmark();
    translateControl.switchToBookmark();
  }

  @FXML
  void menuHistory(ActionEvent event) throws IOException {
    mainMenu();
    switchToHistory();
    dictionaryControl.switchToHistory();
    translateControl.switchToHistory();
  }

  @FXML
  void menuSearch(ActionEvent event) throws IOException {
    mainMenu();
    switchToSearch();
    dictionaryControl.switchToSearch();
    translateControl.switchToSearch();
  }

  @FXML
  void menuTranslate(ActionEvent event) throws IOException {
    translateMenu();
    switchToTranslate();
    dictionaryControl.switchToTranslate();
    translateControl.switchToTranslate();
  }

  @FXML
  void menuGame(ActionEvent event) throws IOException {
    switchToGameSelection();
    dictionaryControl.switchToGameSelection();
    translateControl.switchToGameSelection();
  }

  @FXML
  void anagram(ActionEvent event) {
    Stage stage = (Stage) anagramBtn.getScene().getWindow();
    stage.setScene(anagramMainMenuScene);
  }

  @FXML
  void quiz(ActionEvent event) {
    /**
     * Viết code chuyển sang menu game của bạn vào đây
     * Giống như phương thức anagram ở trên
     */
  }
}
