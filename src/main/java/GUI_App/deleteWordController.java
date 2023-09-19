package GUI_App;

import Implement.OpenDeleteWarning;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class deleteWordController{
  @FXML
  public static Button btnYes;
  @FXML
  public static Button btnNo;
  @FXML
  ImageView qMark;

  @FXML
  Label lblMsg;

  @FXML
  public void clickYes(ActionEvent e) {
    OpenDeleteWarning.closeStage(true);
  }
  @FXML
  public void clickNo(ActionEvent e) {
    OpenDeleteWarning.closeStage(false);
  }
}