package Dictionary;

import Implement.Box.deleteWarning;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class deleteWordController extends ChoiceBox {
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    lblMsg.setText("Bạn có chắc muốn xóa " + deleteWarning.Word + " khỏi từ điển?");
  }
  @FXML
  public void clickYes(ActionEvent e) {
    deleteWarning.closeStage(true);
  }
  @FXML
  public void clickNo(ActionEvent e) {
    deleteWarning.closeStage(false);
  }
}