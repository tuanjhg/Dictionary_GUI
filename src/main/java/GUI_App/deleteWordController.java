package GUI_App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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