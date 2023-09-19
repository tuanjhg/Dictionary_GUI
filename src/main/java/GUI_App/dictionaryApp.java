package GUI_App;

import Implement.DictionaryMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class dictionaryApp extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("deleteWord.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 340, 150);
    stage.setResizable(false);
    stage.setTitle("Dictionary");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}