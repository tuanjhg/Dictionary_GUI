package GUI_App;

import Implement.Box.addAPI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class addAPIController extends ChoiceBox {
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