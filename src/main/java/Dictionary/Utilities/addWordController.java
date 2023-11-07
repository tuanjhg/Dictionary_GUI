package Dictionary.Utilities;

import Implement.History;
import Implement.WordFormatter;
import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class addWordController {

  @FXML
  private TextField tfWord;
  @FXML
  TextField tfPhonetic;
  @FXML
  TextField tfMeaning;

  public void setWord(String WORD) {
    tfWord.setText(WORD);
  }

  @FXML
  public void clickAdd(ActionEvent e) {
    String word = WordFormatter.normalize(tfWord.getText());
    String phonetic = WordFormatter.normalize(tfPhonetic.getText());
    String meaning = WordFormatter.normalize(tfMeaning.getText());
    if (word.isBlank() || phonetic.isBlank() || meaning.isBlank()) {
      return;
    }
    Trie.add(word, phonetic, meaning, "");
    DictionaryMap.add(word, meaning);
    History.add(word);
    Stage stage = (Stage) tfWord.getScene().getWindow();
    stage.close();
  }
}