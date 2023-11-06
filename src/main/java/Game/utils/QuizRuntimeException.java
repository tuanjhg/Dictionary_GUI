package Game.utils;

import javafx.scene.control.Alert;

public class QuizRuntimeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public QuizRuntimeException(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("A runtime exception occured");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
