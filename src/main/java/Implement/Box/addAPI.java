package Implement.Box;

import Dictionary.Dictionary.dictionaryApp;
import Implement.MutableBoolean;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addAPI {
  static Stage stage;
  public static MutableBoolean added;
  public static String Word;
  public static void start(Stage curStage, MutableBoolean curAdd, String word) throws IOException {
    Word = word;
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("addAPIBox.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 340, 150);
    stage = curStage;
    added = curAdd;
    added.setValue(false);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage
        .getIcons()
        .add(
            new Image(
                new File("src/main/resources/Images/plusIcon.png").toURI().toString()));
    stage.setResizable(false);
    stage.setTitle("Thêm từ");
    stage.setScene(scene);
    stage.showAndWait();
  }

  public static void closeStage(boolean type) {
    added.setValue(type);
    stage.close();
  }
}
