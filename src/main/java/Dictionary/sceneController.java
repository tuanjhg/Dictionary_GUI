package Dictionary;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sceneController {
  private FXMLLoader fxmlLoader;
  private Stage stage;
  private Scene scene;
  public void sceneSearch(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("mainMenu.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(fxmlLoader.load(), 900, 600);
    stage.setScene(scene);
    stage.show();
  }
  public void sceneTranslate(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(dictionaryApp.class.getResource("translateMenu.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(fxmlLoader.load(), 900, 600);
    stage.setScene(scene);
    stage.show();
  }
}
