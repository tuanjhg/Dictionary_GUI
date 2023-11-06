module com.example.oop_project {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.google.gson;
  requires org.apache.httpcomponents.httpcore;
  requires org.apache.httpcomponents.httpclient;
  requires javafx.media;
  requires org.json;

  opens Dictionary to javafx.fxml;
  opens Implement.Input.SingleWord to com.google.gson;
  exports Dictionary;
  opens Game to javafx.fxml;
  exports Game;
  opens Game.controller to javafx.fxml;
  exports Game.controller;
}