module com.example.oop_project {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.google.gson;
  requires org.apache.httpcomponents.httpcore;
  requires org.apache.httpcomponents.httpclient;
  requires org.fxmisc.richtext;

  opens GUI_App to javafx.fxml;
  opens Implement.Input.API to com.google.gson;
  exports GUI_App;
}