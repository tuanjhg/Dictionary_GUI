package Game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class scoreController implements Initializable {
  @FXML private Label lblScore = new Label();
  public void initialize(URL url, ResourceBundle rb) {
    setFinalScore("0");
  }

  public void setFinalScore(String x) {
    lblScore.setText(x);
  }
}
