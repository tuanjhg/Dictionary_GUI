package Dictionary.Utilities;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public abstract class ChoiceBox implements Initializable {
  @FXML
  public static Button btnYes;
  @FXML
  public static Button btnNo;
  @FXML
  ImageView qMark;

  @FXML
  Label lblMsg;

  public abstract void initialize(URL url, ResourceBundle rb);
  public abstract void clickYes(ActionEvent e);
  public abstract void clickNo(ActionEvent e);
}
