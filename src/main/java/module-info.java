module com.example.oop_project {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;

  opens GUI_App to javafx.fxml;
  exports GUI_App;
}