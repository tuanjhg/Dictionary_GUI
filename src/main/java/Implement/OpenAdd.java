package Implement;

import GUI_App.dictionaryApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenAdd {
  static Stage stage;
  public static String AddWord;
  public static void start(Stage curStage, String str) throws IOException {
    stage = curStage;
    AddWord = str;
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("addWord.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 270, 350);
    stage.setResizable(false);
    stage.setTitle("Thêm từ");
    stage.setScene(scene);
    stage.showAndWait();
  }

  public static void closeStage(String word, String phonetic, String type, String meaning) {
    DictionaryMap.add(word, meaning);
    Trie.add(word, phonetic, type, meaning);
    stage.close();
  }
}
