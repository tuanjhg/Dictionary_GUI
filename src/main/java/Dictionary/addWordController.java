package Dictionary;

import Implement.Box.addBox;
import Implement.WordFormatter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class addWordController implements Initializable {

  @FXML
  private TextField tfEng;
  @FXML
  TextField tfPhonetic;
  @FXML
  TextField tfVie;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    String temp = addBox.AddWord;
    tfEng.setText(temp);
  }
  @FXML
  public void clickAdd(ActionEvent e) {
    String word = WordFormatter.normalize(tfEng.getText());
    String phonetic = WordFormatter.normalize(tfPhonetic.getText());
    String meaning = WordFormatter.normalize(tfVie.getText());
    if (word.isBlank() || phonetic.isBlank() || meaning.isBlank()) {
      return;
    }
    addBox.closeStage(word, phonetic, meaning);
  }
}