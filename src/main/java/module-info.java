module com.example.oop_project {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.google.gson;
  requires org.apache.httpcomponents.httpcore;
  requires org.apache.httpcomponents.httpclient;
  requires javafx.media;
  requires org.json;

  opens Dictionary.Dictionary to javafx.fxml;
  opens Implement.Input.SingleWord to com.google.gson;
  exports Dictionary.Dictionary;
  exports Game.Anagram;
  opens Game.Anagram to javafx.fxml;
  exports Game.Quiz.controller;
  opens Game.Quiz.controller to javafx.fxml;
    exports Game;
    opens Game to javafx.fxml;
  exports Dictionary.Utilities;
  opens Dictionary.Utilities to javafx.fxml;
}