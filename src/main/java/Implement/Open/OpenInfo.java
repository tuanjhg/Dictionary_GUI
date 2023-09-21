package Implement.Open;

import GUI_App.dictionaryApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenInfo {
  static Stage stage;
  public static String message;
  public static void start(Stage curStage, String str) throws IOException {
    stage = curStage;
    message = str;
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("info.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 340, 150);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage
        .getIcons()
        .add(
            new Image(
                "C:\\Users\\Admin\\Desktop\\OOP_Project\\src\\main\\resources\\Images\\infoIcon.png"));
    stage.setResizable(false);
    stage.setTitle("Info");
    stage.setScene(scene);
    stage.showAndWait();
  }
  public static void closeStage() {
    stage.close();
  }
}
