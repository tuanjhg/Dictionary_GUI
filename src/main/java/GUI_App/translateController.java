package GUI_App;

import Implement.Input.Paragraph.Translator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class translateController implements Initializable {

  @FXML private ImageView imgAPI;

  @FXML private ImageView imgBookmark;

  @FXML private ImageView imgHistory;

  @FXML private ImageView imgSearch;

  @FXML private ImageView imgTranslate;

  @FXML private ImageView imgToggle;

  @FXML private Label btnTranslate = new Label();

  @FXML private TextArea input = new TextArea();

  @FXML private TextArea result = new TextArea();

  mainController mControl = dictionaryApp.mControl;
  private final TranslateTransition transition = new TranslateTransition(Duration.millis(130));

  void setStyle(Node x, String style) {
    x.getStyleClass().add(style);
  }

  public void initialize(URL url, ResourceBundle rb) {
    Font font =  Font.loadFont(("file:src/main/resources/Font/Roboto-Regular.ttf"),23);
    Font bold =  Font.loadFont(("file:src/main/resources/Font/Roboto-Bold.ttf"),23);
    input.setFont(font); result.setFont(font); btnTranslate.setFont(bold);
    setStyle(btnTranslate, "translateButton"); setStyle(imgAPI, "toHandCursor");
    setStyle(imgBookmark, "toHandCursor"); setStyle(imgSearch, "toHandCursor");
    setStyle(imgHistory, "toHandCursor"); setStyle(imgTranslate, "toHandCursor");
    transition.setNode(imgToggle);
  }
  public ImageView getImgAPI() {
    return imgAPI;
  }

  public ImageView getImgBookmark() {
    return imgBookmark;
  }

  public ImageView getImgHistory() {
    return imgHistory;
  }

  public ImageView getImgSearch() {
    return imgSearch;
  }

  void toggleMenu(ImageView img) {
    transition.setToY(img.getLayoutY() - 1.5 - imgToggle.getLayoutY());
    transition.play();
  }

  public void switchToSearch() {
    toggleMenu(imgSearch);
  }
  public void switchToBookmark() {
    toggleMenu(imgBookmark);
  }
  public void switchToHistory() {
    toggleMenu(imgHistory);
  }
  public void switchToAPI() {
    toggleMenu(imgAPI);
  }
  String getFile(String path) {
    return new File(path).toURI().toString();
  }
  void mainMenu() throws IOException{
    Stage stage = (Stage) btnTranslate.getScene().getWindow();
    stage.setScene(dictionaryApp.scene1);
  }

  @FXML
  void menuAPI(MouseEvent event) throws IOException {
    mainMenu();
    mControl.switchToAPI();
  }

  @FXML
  void menuBookmark(MouseEvent event) throws IOException {
    mainMenu();
    mControl.switchToBookmark();
  }

  @FXML
  void menuHistory(MouseEvent event) throws IOException {
    mainMenu();
    mControl.switchToHistory();
  }

  @FXML
  void menuSearch(MouseEvent event) throws IOException {
    mainMenu();
    mControl.switchToSearch();
  }


  @FXML
  void translate(MouseEvent event) {
    String from = input.getText();
    if (input.getText().isBlank()) {
      return;
    }
    String to = Translator.translate(from, "en", "vi");
    result.setText(to);
  }

  void init() {
    toggleMenu(imgTranslate);
    input.setText("");
    result.setText("");
  }
}
