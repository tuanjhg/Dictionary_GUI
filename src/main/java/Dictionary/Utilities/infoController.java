package Dictionary.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class infoController {
  @FXML
  private Button btnOK;

  @FXML
  private ImageView imgIcon;

  @FXML
  private Label msg;

  public void setPrompt(String MSG) {
    msg.setText(MSG);
  }
  @FXML
  public void clickOK(ActionEvent e) {
    Stage stage = (Stage) msg.getScene().getWindow();
    stage.close();
  }
}
