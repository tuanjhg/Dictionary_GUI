package Game.Quiz.controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenController {
    public static void goMainMenu(Class<?> controllerClass, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(controllerClass.getResource("/Game/Quiz/Main.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void goQuestionSelect(Class<?> controllerClass, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(controllerClass.getResource("/Game/Quiz/QuestionSelect.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void goQuestion(Class<?> controllerClass, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(controllerClass.getResource("/Game/Quiz/QuestionView.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void goCurrentWinning(Class<?> controllerClass, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(controllerClass.getResource("/Game/Quiz/CurrentWinning.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void goGameOver(Class<?> controllerClass, ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(controllerClass.getResource("/Game/Quiz/GameOver.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
