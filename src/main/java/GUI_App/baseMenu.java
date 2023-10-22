package GUI_App;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class baseMenu {
  @FXML
  protected ImageView imgSearch = new ImageView();
  @FXML
  protected ImageView imgBookmark = new ImageView();
  @FXML
  protected ImageView imgHistory = new ImageView();
  @FXML
  protected ImageView imgAPI = new ImageView();
  @FXML
  protected ImageView imgTranslate = new ImageView();
  @FXML
  protected ImageView imgToggle = new ImageView();

  public ImageView getImgSearch() {
    return imgSearch;
  }

  public void setImgSearch(ImageView imgSearch) {
    this.imgSearch = imgSearch;
  }

  public ImageView getImgBookmark() {
    return imgBookmark;
  }

  public void setImgBookmark(ImageView imgBookmark) {
    this.imgBookmark = imgBookmark;
  }

  public ImageView getImgHistory() {
    return imgHistory;
  }

  public void setImgHistory(ImageView imgHistory) {
    this.imgHistory = imgHistory;
  }

  public ImageView getImgAPI() {
    return imgAPI;
  }

  public void setImgAPI(ImageView imgAPI) {
    this.imgAPI = imgAPI;
  }

  public ImageView getImgTranslate() {
    return imgTranslate;
  }

  public void setImgTranslate(ImageView imgTranslate) {
    this.imgTranslate = imgTranslate;
  }

  public ImageView getImgToggle() {
    return imgToggle;
  }

  public void setImgToggle(ImageView imgToggle) {
    this.imgToggle = imgToggle;
  }

  abstract void menuSearch(MouseEvent e) throws IOException;
  abstract void menuHistory(MouseEvent e) throws IOException;
  abstract void menuBookmark(MouseEvent e) throws IOException;
  abstract void menuAPI(MouseEvent e) throws IOException;
  abstract void menuTranslate(MouseEvent e) throws IOException;
}
