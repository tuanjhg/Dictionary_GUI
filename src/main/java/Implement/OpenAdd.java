package Implement;

import GUI_App.dictionaryApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenAdd {
  static Stage stage;
  public static String AddWord;
  public static void start(Stage curStage, String str) throws IOException {
    stage = curStage;
    AddWord = str;
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("addWord.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 270, 350);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage
        .getIcons()
        .add(
            new Image(
                "C:\\Users\\Admin\\Desktop\\OOP_Project\\src\\main\\resources\\Images\\plusIcon.png"));
    stage.setResizable(false);
    stage.setTitle("Thêm từ");
    stage.setScene(scene);
    stage.showAndWait();
  }

  public static void closeStage(String word, String phonetic, String type, String meaning) {
    Trie.add(word, phonetic, type, meaning);
    DictionaryMap.add(word, meaning);
    History.add(word);
    stage.close();
  }
}
