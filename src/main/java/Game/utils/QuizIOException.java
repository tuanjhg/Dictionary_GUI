package Game.utils;

import javafx.scene.control.Alert;

import java.io.IOException;

public class QuizIOException  extends IOException {
    private static final long serialVersionUID = 1L;

    public QuizIOException(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("A IO exception occured");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

