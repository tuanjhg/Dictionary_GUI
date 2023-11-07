package Dictionary.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class addAPIController extends ChoiceBox {
  boolean added = false;

  public boolean isAdded() {
    return added;
  }

  @FXML
  public void clickYes(ActionEvent e) {
    super.clickYes(e);
    added = true;
  }

  @FXML
  public void clickNo(ActionEvent e) {
    super.clickNo(e);
    added = false;
  }
}
