package Dictionary.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class deleteWordController extends ChoiceBox {
  boolean deleted = false;

  public boolean isDeleted() {
    return deleted;
  }

  @FXML
  public void clickYes(ActionEvent e) {
    super.clickYes(e);
    deleted = true;
  }

  @FXML
  public void clickNo(ActionEvent e) {
    super.clickNo(e);
    deleted = false;
  }
}
