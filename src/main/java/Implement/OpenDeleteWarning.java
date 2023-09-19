package Implement;

import GUI_App.dictionaryApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenDeleteWarning {
  static Stage stage;
  public static MutableBoolean deleted;
  public static void start(Stage curStage, MutableBoolean curDel) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("deleteWord.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 340, 150);
    stage = curStage;
    deleted = curDel;
    deleted.setValue(false);
    stage.setResizable(false);
    stage.setTitle("Chú ý");
    stage.setScene(scene);
    stage.showAndWait();
  }

  public static void closeStage(boolean type) {
    deleted.setValue(type);
    stage.close();
  }
}
