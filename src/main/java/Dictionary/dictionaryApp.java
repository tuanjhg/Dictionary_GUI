package Dictionary;

import Game.scoreController;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Game.gameController;
import java.io.IOException;

public class dictionaryApp extends Application {

  public static FXMLLoader fxmlLoader1, fxmlLoader2, fxmlLoader3, fxmlLoader4;
  public static Scene scene1, scene2, scene3, scene4;
  public static mainController mControl;
  public static translateController tControl;
  public static gameController gameControl;
  public static scoreController scoreControl;
  String getFile(String path) {
    return new File(path).toURI().toString();
  }
  @Override
  public void start(Stage stage) throws IOException {
    fxmlLoader1 = new FXMLLoader(dictionaryApp.class.getResource("mainMenu.fxml"));
    fxmlLoader2 = new FXMLLoader(dictionaryApp.class.getResource("translateMenu.fxml"));
    fxmlLoader3 = new FXMLLoader(dictionaryApp.class.getResource("game.fxml"));
    fxmlLoader4 = new FXMLLoader(dictionaryApp.class.getResource("finalscore.fxml"));
    Parent root1, root2, root3, root4;
    root1 = fxmlLoader1.load(); root2 = fxmlLoader2.load();
    root3 = fxmlLoader3.load(); root4 = fxmlLoader4.load();
    mControl = fxmlLoader1.getController(); tControl = fxmlLoader2.getController();
    mControl.tControl = tControl; tControl.mControl = mControl;
    gameControl = fxmlLoader3.getController(); scoreControl = fxmlLoader4.getController();
    scene1 = new Scene(root1, 900, 600); scene2 = new Scene(root2, 900, 600);
    scene3 = new Scene(root3, 900, 600); scene4 = new Scene(root4, 900, 600);
    scene1.getStylesheets().add(getFile("src/main/resources/style.css"));
    scene2.getStylesheets().add(getFile("src/main/resources/style.css"));
    stage.setResizable(false);
    stage.setTitle("LingoBench");
    stage.getIcons().add(new Image(getFile("src/main/resources/Images/dictionaryIcon.png")));
    stage.setScene(scene2); stage.show(); stage.setScene(scene1); stage.show();
    stage.setScene(scene4); stage.show(); stage.setScene(scene3); stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}