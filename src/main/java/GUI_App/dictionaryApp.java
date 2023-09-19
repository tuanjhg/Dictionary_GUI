package GUI_App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class dictionaryApp extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("mainMenu.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 900, 600);
    stage.setResizable(false);
    stage.setTitle("Từ điển");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}