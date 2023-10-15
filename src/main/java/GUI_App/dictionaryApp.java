package GUI_App;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class dictionaryApp extends Application {

  public static FXMLLoader fxmlLoader1, fxmlLoader2;
  public static Scene scene1, scene2;
  public static mainController mControl;
  public static translateController tControl;
  String getFile(String path) {
    return new File(path).toURI().toString();
  }
  @Override
  public void start(Stage stage) throws IOException {
    fxmlLoader1 = new FXMLLoader(dictionaryApp.class.getResource("mainMenu.fxml"));
    fxmlLoader2 = new FXMLLoader(dictionaryApp.class.getResource("translateMenu.fxml"));
    Parent root1, root2;
    root1 = (Parent) fxmlLoader1.load(); root2 = (Parent) fxmlLoader2.load();
    mControl = fxmlLoader1.getController(); tControl = fxmlLoader2.getController();
    mControl.tControl = tControl; tControl.mControl = mControl;
    scene1 = new Scene(root1, 900, 600); scene2 = new Scene(root2, 900, 600);
    scene1.getStylesheets().add(getFile("src/main/resources/style.css"));
    scene2.getStylesheets().add(getFile("src/main/resources/style.css"));
    stage.setResizable(false);
    stage.setTitle("LingoBench");
    stage.getIcons().add(new Image(getFile("src/main/resources/Images/dictionaryIcon.png")));
    stage.setScene(scene2); stage.show(); stage.setScene(scene1); stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}