package Dictionary.Utilities;

import Implement.History;
import Implement.WordFormatter;
import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class addWordController {

  @FXML
  private TextField tfEng;
  @FXML
  TextField tfPhonetic;
  @FXML
  TextField tfVie;

  public void setWord(String WORD) {
    tfEng.setText(WORD);
  }

  @FXML
  public void clickAdd(ActionEvent e) {
    String word = WordFormatter.normalize(tfEng.getText());
    String phonetic = WordFormatter.normalize(tfPhonetic.getText());
    String meaning = WordFormatter.normalize(tfVie.getText());
    if (word.isBlank() || phonetic.isBlank() || meaning.isBlank()) {
      return;
    }
    Trie.add(word, phonetic, meaning, "");
    DictionaryMap.add(word, meaning);
    History.add(word);
    Stage stage = (Stage) tfEng.getScene().getWindow();
    stage.close();
  }
}