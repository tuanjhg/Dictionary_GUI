package Game;

import static Dictionary.dictionaryApp.scene4;
import static Dictionary.dictionaryApp.scoreControl;

import Implement.WordFormatter;
import Implement.WordStorage.DictionaryMap;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class gameController implements Initializable {

  @FXML
  private HBox selected;

  @FXML
  private GridPane storage;

  @FXML
  private Label lblScore;

  @FXML
  private Label timerLabel;

  private Timeline timeline;
  private int countdownSeconds = 5;
  int score;

  int numCol;
  List<Character> letter = new ArrayList<>();
  List<selectedButton> answer = new ArrayList<>();

  public int getScore() {
    return score;
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    selected.setSpacing(10);
    newGame();
    initializeTimer();
  }

  //Chat GPT
  private void initializeTimer() {
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
      if (countdownSeconds > 0) {
        countdownSeconds--;
        updateTimeLabel();
      } else {
        scoreControl.setFinalScore(score + "");
        Stage stage = (Stage) timerLabel.getScene().getWindow();
        stage.setScene(scene4);
        timeline.stop();
      }
    });
    timeline = new Timeline(keyFrame);
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void updateTimeLabel() {
    int minutes = countdownSeconds / 60;
    int seconds = countdownSeconds % 60;
    String timeString = String.format("%02d:%02d", minutes, seconds);
    timerLabel.setText(timeString);
  }
  // End of Chat GPT

  String getFile(String path) {
    return new File(path).toURI().toString();
  }
  ImageView getLetterImage(Character c) {
    ImageView ret = new ImageView(new Image(getFile(
        "src/main/resources/Images/Letter/letter-" + c.toString() + ".png")));
    ret.setFitHeight(40);
    ret.setFitWidth(40);
    return ret;
  }

  void newGame() {
    score = 0;
    nextRound();
  }
  void nextRound() {
    lblScore.setText(score + "");
    letter.clear();
    String tmp = "";
    while (!(tmp.length() >= 1 && tmp.length() <= 7)) {
      tmp = DictionaryMap.getRandom();
    }
    for (int i = 0; i < tmp.length(); i++) {
      char c = tmp.charAt(i);
      letter.add(c);
    }
    System.out.println(tmp);
    Collections.shuffle(letter);
    reset();
  }
  void reset() {
    numCol = 0;
    selected.getChildren().clear();
    storage.getColumnConstraints().clear();
    storage.getChildren().clear();
    answer.clear();
    ColumnConstraints constraints = new ColumnConstraints();
    constraints.setPercentWidth(100.0 / letter.size());
    for (int i = 0; i < letter.size(); i++) {
      storage.getColumnConstraints().add(constraints);
    }
    for (Character c : letter) {
      addButton(c);
    }
  }

  void addButton(Character c) {
    givenButton btn = new givenButton(c);
    btn.setPrefWidth(60); btn.setPrefHeight(60);
    btn.setMinWidth(60); btn.setMinHeight(60);
    btn.setMaxWidth(60); btn.setMaxHeight(60);
    btn.setGraphic(getLetterImage(c));
    btn.setOnAction(event -> handleClick(btn));
    storage.add(btn, numCol++, 0);
    GridPane.setHalignment(btn, HPos.CENTER);
  }

  void selectGivenButton(givenButton button) {
    selectedButton btn = new selectedButton(button);
    btn.setPrefWidth(60); btn.setPrefHeight(60);
    btn.setMinWidth(60); btn.setMinHeight(60);
    btn.setMaxWidth(60); btn.setMaxHeight(60);
    btn.setGraphic(getLetterImage(button.getC()));
    btn.setOnAction(event -> handleClick(btn));
    selected.getChildren().add(btn);
    button.setVisible(false);
    answer.add(btn);
  }

  void selectSelectedButton(selectedButton button) {
    givenButton btn = button.getParentButton();
    btn.setVisible(true);
    selected.getChildren().remove(button);
    answer.remove(button);
  }

  void handleClick(Button button) {
    if (button instanceof givenButton) {
      givenButton btn = (givenButton) button;
      selectGivenButton(btn);
    } else {
      selectedButton btn = (selectedButton) button;
      selectSelectedButton(btn);
    }
  }

  @FXML
  void verifyWord(MouseEvent event) {
    StringBuilder tmp = new StringBuilder();
    for (selectedButton i : answer) {
      tmp.append(i.getParentButton().getC().toString());
    }
    String res = WordFormatter.normalize(tmp.toString());
    if (DictionaryMap.exist(res)) {
      score++;
      nextRound();
    }
    reset();
  }
}