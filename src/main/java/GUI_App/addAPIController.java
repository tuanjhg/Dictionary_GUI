package GUI_App;

import Implement.OpenBox.addAPI;
import Implement.OpenBox.deleteWarning;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class addAPIController implements Initializable {
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
    lblMsg.setText("Bạn có chắc muốn thêm " + addAPI.Word + " vào từ điển?");
  }
  @FXML
  public void clickYes(ActionEvent e) {
    addAPI.closeStage(true);
  }
  @FXML
  public void clickNo(ActionEvent e) {
    addAPI.closeStage(false);
  }
}