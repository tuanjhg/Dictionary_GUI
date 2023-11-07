package Dictionary;

import Game.Anagram.anagramMainMenuController;
import Game.Anagram.anagramModeController;
import Game.Anagram.anagramScoreController;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Game.Anagram.anagramGameController;
import java.io.IOException;

public class dictionaryApp extends Application {

  public static FXMLLoader fxmlDictionary, fxmlTranslate;
  public static FXMLLoader fxmlAnagramGame, fxmlAnagramScore, fxmlAnagramMain, fxmlAnagramMode;
  public static Scene dictionaryScene, translateScene;
  public static Scene anagramGameScene, anagramScoreScene, anagramMainMenuScene, anagramModeScene;
  public static mainController dictionaryControl;
  public static translateController translateControl;
  public static anagramGameController anagramGameControl;
  public static anagramScoreController anagramScoreControl;
  public static anagramMainMenuController anagramMainMenuController;
  public static anagramModeController anagramModeController;
  String getFile(String path) {
    return new File(path).toURI().toString();
  }
  @Override
  public void start(Stage stage) throws IOException {
    fxmlDictionary = new FXMLLoader(dictionaryApp.class.getResource("mainMenu.fxml"));
    fxmlTranslate = new FXMLLoader(dictionaryApp.class.getResource("translateMenu.fxml"));
    fxmlAnagramGame = new FXMLLoader(dictionaryApp.class.getResource("/Game/Anagram/game.fxml"));
    fxmlAnagramScore = new FXMLLoader(dictionaryApp.class.getResource("/Game/Anagram/finalscore.fxml"));
    fxmlAnagramMain = new FXMLLoader(dictionaryApp.class.getResource("/Game/Anagram/mainmenu.fxml"));
    fxmlAnagramMode = new FXMLLoader(dictionaryApp.class.getResource("/Game/Anagram/selectMode.fxml"));

    Parent dictRoot, transRoot;
    Parent anagramGameRoot, anagramScoreRoot, anagramMainMenuRoot, anagramModeRoot;

    dictRoot = fxmlDictionary.load(); transRoot = fxmlTranslate.load();
    anagramGameRoot = fxmlAnagramGame.load(); anagramScoreRoot = fxmlAnagramScore.load();
    anagramMainMenuRoot = fxmlAnagramMain.load(); anagramModeRoot = fxmlAnagramMode.load();

    dictionaryControl = fxmlDictionary.getController(); translateControl = fxmlTranslate.getController();
    dictionaryControl.tControl = translateControl; translateControl.mControl = dictionaryControl;

    anagramGameControl = fxmlAnagramGame.getController();
    anagramScoreControl = fxmlAnagramScore.getController();
    anagramMainMenuController = fxmlAnagramMain.getController();
    anagramModeController = fxmlAnagramMode.getController();

    dictionaryScene = new Scene(dictRoot, 900, 600);
    translateScene = new Scene(transRoot, 900, 600);

    anagramGameScene = new Scene(anagramGameRoot, 900, 600);
    anagramScoreScene = new Scene(anagramScoreRoot, 900, 600);
    anagramMainMenuScene = new Scene(anagramMainMenuRoot, 900, 600);
    anagramModeScene = new Scene(anagramModeRoot, 900, 600);

    dictionaryScene.getStylesheets().add(getFile("src/main/resources/style.css"));
    translateScene.getStylesheets().add(getFile("src/main/resources/style.css"));

    stage.setResizable(false);
    stage.setTitle("LingoBench");
    stage.getIcons().add(new Image(getFile("src/main/resources/Images/dictionaryIcon.png")));
    stage.setScene(anagramMainMenuScene); stage.show();
    stage.setScene(anagramModeScene); stage.show();
    stage.setScene(anagramGameScene); stage.show();
    stage.setScene(anagramScoreScene); stage.show();
    stage.setScene(translateScene); stage.show();
    stage.setScene(dictionaryScene); stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}