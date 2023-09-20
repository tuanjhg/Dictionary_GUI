package GUI_App;

import Implement.OpenAdd;
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
  TextField tfType;
  @FXML
  TextField tfVie;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    String temp = new String(OpenAdd.AddWord);
    tfEng.setText(temp);
  }
  @FXML
  public void clickAdd(ActionEvent e) {
    String word = new String(WordFormatter.normalize(tfEng.getText()));
    String phonetic = new String(WordFormatter.normalize(tfPhonetic.getText()));
    String type = new String(WordFormatter.normalize(tfType.getText()));
    String meaning = new String(WordFormatter.normalize(tfVie.getText()));
    if (word.isBlank() || phonetic.isBlank() || type.isBlank() || meaning.isBlank()) {
      return;
    }
    OpenAdd.closeStage(word, phonetic, type, meaning);
  }
}