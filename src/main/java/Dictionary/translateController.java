package Dictionary;

import Implement.Input.Paragraph.Translator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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

public class translateController extends baseMenu implements Initializable {
  @FXML private ImageView swap;

  @FXML private TextArea input = new TextArea("");

  @FXML private TextArea result = new TextArea("");

  @FXML private Label lanEn, lanVi, lanEn1, lanVi1, lanFr, lanFr1, lanIt, lanIt1;

  String lanFrom = "en", lanTo = "vi";

  mainController mControl = dictionaryApp.dictionaryControl;
  private final TranslateTransition transition = new TranslateTransition(Duration.millis(130));

  void setStyle(Node x, String style) {
    x.getStyleClass().add(style);
  }
  void removeStyle(Node x, String style) {
    x.getStyleClass().remove(style);
  }

  public void initialize(URL url, ResourceBundle rb) {
    super.initialize(url, rb);
    Font font =  Font.loadFont(("file:src/main/resources/Font/Roboto-Regular.ttf"),23);
    Font lanFont =  Font.loadFont(("file:src/main/resources/Font/Roboto-Bold.ttf"),15);
    input.setFont(font); result.setFont(font);
    lanEn.setFont(lanFont); lanVi.setFont(lanFont); lanEn1.setFont(lanFont); lanVi1.setFont(lanFont);
    lanFr.setFont(lanFont); lanFr1.setFont(lanFont); lanIt.setFont(lanFont); lanIt1.setFont(lanFont);
    setStyle(lanEn, "lan"); setStyle(lanEn1, "lan");
    setStyle(lanVi, "lan"); setStyle(lanVi1, "lan");
    setStyle(lanFr, "lan"); setStyle(lanFr1, "lan");
    setStyle(lanIt, "lan"); setStyle(lanIt1, "lan");
    setStyle(imgAPI, "toHandCursor"); setStyle(swap, "toHandCursor");
    setStyle(imgBookmark, "toHandCursor"); setStyle(imgSearch, "toHandCursor");
    setStyle(imgHistory, "toHandCursor"); setStyle(imgTranslate, "toHandCursor");
    setStyle(input, "translateArea"); setStyle(result, "translateArea");
    unfocusAllLan("from"); unfocusAllLan("to");
    focusLan(lanEn); focusLan(lanVi1);
    transition.setNode(imgToggle);
  }

  void toggleMenu(Node img) {
    transition.setToY(img.getLayoutY() - 10 - imgToggle.getLayoutY());
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
    stage.setScene(dictionaryApp.dictionaryScene);
  }

  @FXML
  void menuAPI(ActionEvent event) throws IOException {
    mainMenu();
    mControl.switchToAPI();
  }

  @FXML
  void menuBookmark(ActionEvent event) throws IOException {
    mainMenu();
    mControl.switchToBookmark();
  }

  @FXML
  void menuHistory(ActionEvent event) throws IOException {
    mainMenu();
    mControl.switchToHistory();
  }

  @FXML
  void menuSearch(ActionEvent event) throws IOException {
    mainMenu();
    mControl.switchToSearch();
  }

  @FXML
  void menuTranslate(ActionEvent event) throws IOException {}

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

  void fromFr() {
    lanFrom = "fr"; unfocusAllLan("from"); focusLan(lanFr);
  }

  void fromIt() {
    lanFrom = "it"; unfocusAllLan("from"); focusLan(lanIt);
  }

  void toEn() {
    lanTo = "en"; unfocusAllLan("to"); focusLan(lanEn1);
  }

  void toVi() {
    lanTo = "vi"; unfocusAllLan("to"); focusLan(lanVi1);
  }

  void toFr() {
    lanTo = "fr"; unfocusAllLan("to"); focusLan(lanFr1);
  }

  void toIt() {
    lanTo = "it"; unfocusAllLan("to"); focusLan(lanIt1);
  }

  void swap() {
    String lFrom = new String(lanFrom);
    String lTo = new String(lanTo);
    switch (lFrom) {
      case "vi" -> toVi();
      case "en" -> toEn();
      case "fr" -> toFr();
      case "it" -> toIt();
    }
    switch (lTo) {
      case "vi" -> fromVi();
      case "en" -> fromEn();
      case "fr" -> fromFr();
      case "it" -> fromIt();
    }
    String tmp = new String(input.getText());
    input.setText(result.getText());
    result.setText(tmp);
  }

  @FXML
  void fromEng(MouseEvent e) {
    input.setText(""); fromEn(); trans();
  }

  @FXML
  void fromVie(MouseEvent e) {
    input.setText(""); fromVi(); trans();
  }

  @FXML
  void fromFre(MouseEvent e) {
    input.setText(""); fromFr(); trans();
  }

  @FXML
  void fromIta(MouseEvent e) {
    input.setText(""); fromIt(); trans();
  }

  @FXML
  void toEng(MouseEvent e) {
    toEn(); trans();
  }

  @FXML
  void toVie(MouseEvent e) {
    toVi(); trans();
  }

  @FXML
  void toFre(MouseEvent e) {
    toFr(); trans();
  }

  @FXML
  void toIta(MouseEvent e) {
    toIt(); trans();
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
      removeStyle(lanFr, "focus");
      removeStyle(lanIt, "focus");
    } else {
      removeStyle(lanEn1, "focus");
      removeStyle(lanVi1, "focus");
      removeStyle(lanFr1, "focus");
      removeStyle(lanIt1, "focus");
    }
  }

  void focusLan(Node node) {
    setStyle(node, "focus");
  }
}
