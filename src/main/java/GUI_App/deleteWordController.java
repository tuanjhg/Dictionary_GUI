package GUI_App;

import Implement.Open.OpenDeleteWarning;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class deleteWordController implements Initializable {
  @FXML
  public static Button btnYes;
  @FXML
  public static Button btnNo;
  @FXML
  ImageView qMark;

  @FXML
  Label lblMsg;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lblMsg.setText("Bạn có chắc muốn xóa " + OpenDeleteWarning.Word + " khỏi từ điển?");
  }
  @FXML
  public void clickYes(ActionEvent e) {
    OpenDeleteWarning.closeStage(true);
  }
  @FXML
  public void clickNo(ActionEvent e) {
    OpenDeleteWarning.closeStage(false);
  }
}