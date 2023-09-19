package GUI_App;

import Implement.AddFromFile;
import Implement.DictionaryMap;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class deleteWordController{
  @FXML
  Button btnYes;
  @FXML
  Button btnNo;
  @FXML
  ImageView qMark;
  @FXML
  Label lblMsg;
  String getImgPath(String name) {
    return "C:\\Users\\Admin\\Desktop\\OOP_Project\\src\\main\\resources\\Images\\" + name + ".png";
  }
  void setLblMsg(String word) {
    lblMsg.setText(word);
  }
}