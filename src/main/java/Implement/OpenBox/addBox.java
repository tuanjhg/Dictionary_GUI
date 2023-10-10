package Implement.OpenBox;

import GUI_App.dictionaryApp;
import Implement.WordStorage.DictionaryMap;
import Implement.History;
import Implement.MutableBoolean;
import Implement.WordStorage.Trie.Trie;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addBox {
  static Stage stage;
  public static MutableBoolean added;
  public static String AddWord;
  public static void start(Stage curStage, String str) throws IOException {
    stage = curStage;
    AddWord = str;
    added = new MutableBoolean();
    added.setValue(false);
    FXMLLoader fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("addWord.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 270, 350);
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

  public static void closeStage(String word, String phonetic, String meaning) {
    Trie.add(word, phonetic, meaning, "");
    DictionaryMap.add(word, meaning);
    History.add(word);
    added.setValue(true);
    stage.close();
  }
}
