package Dictionary;

import static Dictionary.dictionaryApp.anagramMainMenuScene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public abstract class baseMenu implements Initializable {
  @FXML
  protected Button imgSearch = new Button();
  @FXML
  protected Button imgBookmark = new Button();
  @FXML
  protected Button imgHistory = new Button();
  @FXML
  protected Button imgAPI = new Button();
  @FXML
  protected Button imgTranslate = new Button();
  @FXML
  protected Button imgGame = new Button();
  @FXML
  protected ImageView imgToggle = new ImageView();

  void setStyle(Node x, String style) {
    x.getStyleClass().add(style);
  }

  String getFile(String path) {
    return new File(path).toURI().toString();
  }

  protected ImageView getImage(String img) {
    ImageView ret = new ImageView(new Image(getFile(
        "src/main/resources/Images/" + img + ".png")));
    ret.setFitHeight(40);
    ret.setFitWidth(40);
    return ret;
  }

  public void initialize(URL url, ResourceBundle rb) {
    imgSearch.setGraphic(getImage("search"));
    imgBookmark.setGraphic(getImage("bookmark"));
    imgHistory.setGraphic(getImage("history"));
    imgAPI.setGraphic(getImage("api"));
    imgTranslate.setGraphic(getImage("translate"));
    imgGame.setGraphic(getImage("game"));
    setStyle(imgSearch, "toHandCursor"); setStyle(imgBookmark, "toHandCursor");
    setStyle(imgHistory, "toHandCursor"); setStyle(imgAPI, "toHandCursor");
    setStyle(imgTranslate, "toHandCursor"); setStyle(imgGame, "toHandCursor");
  }

  abstract void menuSearch(ActionEvent e) throws IOException;
  abstract void menuHistory(ActionEvent e) throws IOException;
  abstract void menuBookmark(ActionEvent e) throws IOException;
  abstract void menuAPI(ActionEvent e) throws IOException;
  abstract void menuTranslate(ActionEvent e) throws IOException;
  @FXML
  void menuGame(ActionEvent event) throws IOException {
    Stage stage = (Stage) imgSearch.getScene().getWindow();
    stage.setScene(anagramMainMenuScene);
  }
}
