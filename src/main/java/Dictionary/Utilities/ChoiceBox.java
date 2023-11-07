package Dictionary.Utilities;

import static Dictionary.Dictionary.dictionaryApp.infoScene;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChoiceBox {
  @FXML public static Button btnYes = new Button();
  @FXML public static Button btnNo = new Button();

  @FXML Label lblMsg;

  public void setPrompt(String msg) {
    lblMsg.setText(msg);
  }

  @FXML
  public void clickYes(ActionEvent e) {
    Stage stage = (Stage) lblMsg.getScene().getWindow();
    stage.close();
    Stage stage1 = new Stage();
    stage1.setScene(infoScene);
    stage1.initModality(Modality.APPLICATION_MODAL);
    stage1
        .getIcons()
        .add(
            new Image(
                new File("src/main/resources/Images/infoIcon.png").toURI().toString()));
    stage1.setTitle("Thông báo");
    stage1.show();
  }

  @FXML
  public void clickNo(ActionEvent e) {
    Stage stage = (Stage) lblMsg.getScene().getWindow();
    stage.close();
  }
}
