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
import javafx.scene.input.KeyEvent;
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

  @FXML private ImageView swap;


  @FXML private TextArea input = new TextArea("");

  @FXML private TextArea result = new TextArea("");

  @FXML private Label lanEn, lanVi, lanEn1, lanVi1;

  String lanFrom = "en", lanTo = "vi";

  mainController mControl = dictionaryApp.mControl;
  private final TranslateTransition transition = new TranslateTransition(Duration.millis(130));

  void setStyle(Node x, String style) {
    x.getStyleClass().add(style);
  }
  void removeStyle(Node x, String style) {
    x.getStyleClass().remove(style);
  }

  public void initialize(URL url, ResourceBundle rb) {
    Font font =  Font.loadFont(("file:src/main/resources/Font/Roboto-Regular.ttf"),23);
    Font bold =  Font.loadFont(("file:src/main/resources/Font/Roboto-Bold.ttf"),23);
    Font lanFont =  Font.loadFont(("file:src/main/resources/Font/Roboto-Bold.ttf"),15);
    input.setFont(font); result.setFont(font);
    lanEn.setFont(lanFont); lanVi.setFont(lanFont); lanEn1.setFont(lanFont); lanVi1.setFont(lanFont);
    setStyle(lanEn, "lan"); setStyle(lanVi, "lan");
    setStyle(lanEn1, "lan"); setStyle(lanVi1, "lan");
    setStyle(imgAPI, "toHandCursor"); setStyle(swap, "toHandCursor");
    setStyle(imgBookmark, "toHandCursor"); setStyle(imgSearch, "toHandCursor");
    setStyle(imgHistory, "toHandCursor"); setStyle(imgTranslate, "toHandCursor");
    setStyle(input, "translateArea"); setStyle(result, "translateArea");
    unfocusAllLan("from"); unfocusAllLan("to");
    focusLan(lanEn); focusLan(lanVi1);
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
    Stage stage = (Stage) input.getScene().getWindow();
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

  void trans() {
    if (input.getText().isBlank()) {
      result.setText("");
      return;
    }
    String from = input.getText();
    Translator tran = new Translator();
    tran.translate(from, lanFrom, lanTo, new Translator.Callback() {
      @Override
      public void onSuccess(String str) {
        result.setText(str);
      }
    });
  }

  void fromEn() {
    lanFrom = "en"; unfocusAllLan("from"); focusLan(lanEn);
  }

  void fromVi() {
    lanFrom = "vi"; unfocusAllLan("from"); focusLan(lanVi);
  }

  void toEn() {
    lanTo = "en"; unfocusAllLan("to"); focusLan(lanEn1);
  }

  void toVi() {
    lanTo = "vi"; unfocusAllLan("to"); focusLan(lanVi1);
  }

  void swap() {
    String lFrom = new String(lanFrom);
    String lTo = new String(lanTo);
    switch (lFrom) {
      case "vi" -> toVi();
      case "en" -> toEn();
    }
    switch (lTo) {
      case "vi" -> fromVi();
      case "en" -> fromEn();
    }
    String tmp = new String(input.getText());
    input.setText(result.getText());
    result.setText(tmp);
  }

  @FXML
  void fromEng(MouseEvent e) {
    toVi();
    fromEn();
  }

  @FXML
  void fromVie(MouseEvent e) {
    toEn();
    fromVi();
  }

  @FXML
  void toEng(MouseEvent e) {
    fromVi();
    toEn();
  }

  @FXML
  void toVie(MouseEvent e) {
    fromEn();
    toVi();
  }

  @FXML
  void onKeyPress(KeyEvent e) {
    trans();
  }

  @FXML
  void swapLan(MouseEvent e) {
    swap();
  }
  void init() {
    toggleMenu(imgTranslate); input.setText(""); result.setText("");
    unfocusAllLan("from"); focusLan(lanEn);
  }

  void unfocusAllLan(String type) {
    if (type.equals("from")) {
      removeStyle(lanEn, "focus");
      removeStyle(lanVi, "focus");
    } else {
      removeStyle(lanEn1, "focus");
      removeStyle(lanVi1, "focus");
    }
  }

  void focusLan(Node node) {
    setStyle(node, "focus");
  }
}
