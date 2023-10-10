package GUI_App;

import Implement.Input.API.Definition;
import Implement.Input.API.DictionaryEntry;
import Implement.Input.API.Meaning;
import Implement.Input.API.Phonetic;
import Implement.Input.AddFromAPI;
import Implement.Input.AddFromFile;
import Implement.Bookmark;
import Implement.OpenBox.addAPI;
import Implement.WordStorage.DictionaryMap;
import Implement.History;
import Implement.MutableBoolean;
import Implement.OpenBox.addBox;
import Implement.OpenBox.deleteWarning;
import Implement.OpenBox.infoBox;
import Implement.WordStorage.Trie.Trie;
import Implement.WordStorage.Trie.TrieNode;
import Implement.WordFormatter;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class mainController implements Initializable{
  
  @FXML
  private TextField searchBar;
  @FXML
  private TextField tfPhonetic;
  @FXML
  private TextArea txtEditor;
  @FXML
  private Label lblWord;
  @FXML
  private Label spelling;
  @FXML
  private Label meaning;
  @FXML
  private ListView<String> suggestWord;
  @FXML
  private ImageView imgSearch;
  @FXML
  private ImageView imgBookmark;
  @FXML
  private ImageView imgHistory;
  @FXML
  private ImageView imgAPI;
  @FXML
  private ImageView bookmarkStar;
  @FXML
  private ImageView recycleBin;
  @FXML
  private ImageView imgSpeaker;
  @FXML
  private ImageView imgEditor;
  @FXML
  private ImageView imgTick;
  @FXML
  private ImageView imgCross;
  @FXML
  private ImageView imgToggle;
  @FXML
  private ImageView imgAdd;
  @FXML
  private ScrollPane scrollMeaning;
  private String currentMenu = "Search";
  private String currentWord;
  private MediaPlayer player;
  final String IMGPath = "src/main/resources/Images/";
  private boolean noSound = true;
  String apiAudio;
  private TranslateTransition transition = new TranslateTransition(Duration.millis(130));

  void setEditor(boolean type) {
    txtEditor.setVisible(type);
    tfPhonetic.setVisible(type);
    imgTick.setVisible(type);
    imgCross.setVisible(type);
  }
  void setStyle(ImageView x) {
    x.getStyleClass().add("imageViewStyle");
  }
  void setSound() {
    imgSpeaker.setDisable(noSound);
    if (noSound) {
      imgSpeaker.setOpacity(0.3);
    } else {
      imgSpeaker.setOpacity(1);
    }
  }
  String getImgPath(String name) {
    return new File(IMGPath + name + ".png").toURI().toString();
  }
  public void getSuggestion(String[] suggestion) {
    searchBar.setText("");
    suggestWord.getItems().clear();
    suggestWord.getItems().addAll(suggestion);
  }
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    scrollMeaning.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
    setStyle(bookmarkStar); setStyle(recycleBin); setStyle(imgEditor);
    setStyle(imgCross); setStyle(imgTick); setStyle(imgSpeaker); imgAdd.setVisible(false);
    imgSearch.toFront(); imgBookmark.toFront(); imgHistory.toFront(); imgAPI.toFront();
    setEditor(false); transition.setNode(imgToggle);
    setSound();
    AddFromFile.add();
    getSuggestion(DictionaryMap.getKey());
    suggestWord
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (observableValue, s, t1) -> {
              currentWord = suggestWord.getSelectionModel().getSelectedItem();
              if (currentWord == null || currentWord.isEmpty()) {
                return;
              }
              if (currentWord.equals("Thêm...")) {
                try {
                  String word = WordFormatter.normalize(searchBar.getText());
                  addBox.start(new Stage(), word);
                  if (addBox.added.isValue()) {
                    infoBox.start(new Stage(), "Đã thêm " + word + " vào từ điển.");
                  }
                } catch (Exception err) {
                  System.out.println("Unknown Error.");
                }
                return;
              }
              TrieNode node = Trie.find(currentWord);
              apiAudio = node.getAudio(); noSound = apiAudio.isBlank(); setSound();
              searchBar.setText(currentWord);
              lblWord.setText(currentWord);
              spelling.setText(node.getSpelling());
              meaning.setText(node.getMeaning());
              scrollMeaning.setContent(meaning);
              imgAdd.setVisible(false);
              if (!node.getBookmarked()) {
                bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
              } else {
                bookmarkStar.setImage(new Image(getImgPath("star")));
              }
              if (!currentMenu.equals("History")) {
                History.add(currentWord);
              }
            });
    Tooltip.install(imgSearch, new Tooltip("Tìm kiếm"));
    Tooltip.install(imgBookmark, new Tooltip("Bookmark"));
    Tooltip.install(imgHistory, new Tooltip("Lịch sử"));
    Tooltip.install(imgAPI, new Tooltip("API"));
  }

  @FXML
  public void findPrefix(KeyEvent e) {
    String word = searchBar.getText();
    if (!currentMenu.equals("API")) {
      suggestWord.getItems().clear();
      if (word == null || word.isEmpty()) {
        getSuggestion(DictionaryMap.getKey());
      } else if (!word.isBlank()) {
        List<String> suggestion = Trie.getPrefix(WordFormatter.normalize(word));
        if (suggestion.isEmpty()) {
          suggestion.add("Thêm...");
        }
        suggestWord.getItems().addAll(suggestion.toArray(new String[(int)suggestion.size()]));
      }
    } else {
      if (e.getCharacter().equals("\r")) {
        if (word.isBlank()) {
          return;
        }
        noSound = true;
        DictionaryEntry entry = AddFromAPI.get(word);
        if (entry != null) {
          lblWord.setText(WordFormatter.normalize(word));
          for (Phonetic i : entry.getPhonetics()) {
            if (!i.getText().isBlank()) {
              spelling.setText(i.getText());
              if (!i.getAudio().isBlank()) {
                apiAudio = i.getAudio();
                noSound = false;
                break;
              }
            }
          }
          setSound(); imgAdd.setVisible(true);
          StringBuilder apiMeaning = new StringBuilder();
          for (Meaning i : entry.getMeanings()) {
            apiMeaning.append("☆   ").append(i.getPartOfSpeech()).append("\n");
            for (Definition j : i.getDefinitions()) {
              apiMeaning.append("          »   ").append(j.getDefinition()).append("\n");
              if (!j.getExample().isBlank()) {
                apiMeaning.append("\n               • Example:   ").append(j.getExample()).append("\n\n");
              }
            }
            if (!i.getSynonyms().isEmpty()) { // If i has synonyms
              apiMeaning.append("          »   Synonyms: ");
              for (String j : i.getSynonyms()) {
                apiMeaning.append(j).append(", ");
              }
              apiMeaning.delete(apiMeaning.length() - 2, apiMeaning.length() - 1);
              apiMeaning.append("\n");
            }
            if (!i.getAntonyms().isEmpty()) { // If i has antonyms
              apiMeaning.append("          »   Antonyms: ");
              for (String j : i.getAntonyms()) {
                apiMeaning.append(j).append(", ");
              }
              apiMeaning.delete(apiMeaning.length() - 2, apiMeaning.length() - 1);
              apiMeaning.append("\n");
            }
          }
          meaning.setText(apiMeaning.toString());
          scrollMeaning.setContent(meaning);
        } else {
          lblWord.setText(""); spelling.setText(""); meaning.setText("");
          try {
            infoBox.start(new Stage(), "Không tìm thấy " + word + " trong API từ điển.");
          } catch (Exception err) {
            System.out.println("Lỗi không xác định");
          }
        }
      }
    }
  }

  void toggleMenu(ImageView img) {
    transition.setToY(img.getLayoutY() - 1.5 - imgToggle.getLayoutY());
    transition.play();
  }

  void menuInit() {
    imgSearch.setImage(new Image(getImgPath("search")));
    imgBookmark.setImage(new Image(getImgPath("bookmark")));
    imgHistory.setImage(new Image(getImgPath("history")));
    imgAPI.setImage(new Image(getImgPath("api")));
    suggestWord.setVisible(true); bookmarkStar.setVisible(true);
    recycleBin.setVisible(true); imgEditor.setVisible(true);
  }

  public void menuSearch(MouseEvent e) {
    menuInit();
    toggleMenu(imgSearch);
    searchBar.setVisible(true);
    getSuggestion(DictionaryMap.getKey());
    currentMenu = "Search";
  }

  public void menuBookmark(MouseEvent e) {
    menuInit();
    toggleMenu(imgBookmark);
    searchBar.setVisible(true);
    getSuggestion(Bookmark.getList());
    currentMenu = "Bookmark";
  }

  public void menuHistory(MouseEvent e) {
    menuInit();
    toggleMenu(imgHistory);
    searchBar.setVisible(false);
    getSuggestion(History.getList());
    currentMenu = "History";
  }

  public void menuAPI(MouseEvent e) {
    menuInit();
    toggleMenu(imgAPI);
    searchBar.setVisible(true); suggestWord.setVisible(false);
    bookmarkStar.setVisible(false); recycleBin.setVisible(false); imgEditor.setVisible(false);
    currentMenu = "API";
  }

  public void changeBookmarkState(MouseEvent e) {
    String word = lblWord.getText();
    if (word.equals("LingoBench") || word.isBlank()) {
      return;
    }
    TrieNode node = Trie.find(word);
    if (!node.getBookmarked()) {
      bookmarkStar.setImage(new Image(getImgPath("star")));
      node.setBookmarked(true);
      Bookmark.add(word);
    } else {
      bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
      node.setBookmarked(false);
      Bookmark.delete(word);
    }
    if (currentMenu.equals("Bookmark")) {
      getSuggestion(Bookmark.getList());
    }
  }

  public void deleteWord(MouseEvent e) {
    String word = lblWord.getText();
    if (word.equals("LingoBench")) {
      return;
    }
    try {
      MutableBoolean deleted = new MutableBoolean();
      deleteWarning.start(new Stage(), deleted, word);
      if (!deleted.isValue()) {
        return;
      }
    } catch (Exception err) {
      System.out.println("Lỗi không xác định DeleteWord");
    }
    try {
      infoBox.start(new Stage(), "Đã xóa " + word + " khỏi từ điển.");
    } catch (Exception err) {
      System.out.println("Lỗi không xác định");
    }
    Trie.delete(word);
    DictionaryMap.delete(word); Bookmark.delete(word); History.delete(word);
    switch (currentMenu) {
      case "Search" -> getSuggestion(DictionaryMap.getKey());
      case "Bookmark" -> getSuggestion(Bookmark.getList());
      case "History" -> getSuggestion(History.getList());
    }
    searchBar.setText("");
    lblWord.setText("");
    spelling.setText("");
    meaning.setText("");
    bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
  }

  public void playMedia(MouseEvent e) {
    Media media = new Media(apiAudio); player = new MediaPlayer(media);
    if (player != null) {
      if (player.getStatus() == Status.PLAYING) {
        player.stop();
      }
      player.seek(Duration.ZERO);
      player.play();
    }
  }

  void iconDisable(boolean b) {
    imgSearch.setDisable(b); imgBookmark.setDisable(b); imgHistory.setDisable(b);
    imgAPI.setDisable(b); imgSpeaker.setDisable(b);
    bookmarkStar.setDisable(b); recycleBin.setDisable(b);
    meaning.setOpacity(1); spelling.setOpacity(1);
    double opacity = 1;
    if (b) {
      opacity = 0.3;
      meaning.setOpacity(0); spelling.setOpacity(0);
    }
    imgSearch.setOpacity(opacity); imgBookmark.setOpacity(opacity); imgHistory.setOpacity(opacity);
    imgAPI.setOpacity(opacity); imgSpeaker.setOpacity(opacity);
    bookmarkStar.setOpacity(opacity); recycleBin.setOpacity(opacity);
  }

  public void openEditor(MouseEvent e) {
    if (lblWord.getText().equals("LingoBench")) {
      return;
    }
    setEditor(true);
    txtEditor.setText(meaning.getText());
    tfPhonetic.setText(spelling.getText());
    iconDisable(true);
    switch (currentMenu) {
      case "Search" -> imgSearch.setOpacity(1);
      case "Bookmark" -> imgBookmark.setOpacity(1);
      case "History" -> imgHistory.setOpacity(1);
    }
  }

  void closeEditor() {
    setEditor(false);
    iconDisable(false);
  }

  public void exitEditor(MouseEvent e) {
    closeEditor();
  }

  public void saveEditor(MouseEvent e) {
    String newMeaning = txtEditor.getText();
    String newSpelling = tfPhonetic.getText();
    meaning.setText(newMeaning);
    spelling.setText(newSpelling);
    Trie.add(lblWord.getText(), newSpelling, newMeaning, "");
    closeEditor();
  }

  public void addFromAPI(MouseEvent e) {
    try {
      MutableBoolean added = new MutableBoolean();
      addAPI.start(new Stage(), added, lblWord.getText());
      if (!added.isValue()) {
        return;
      }
      Trie.add(lblWord.getText(), spelling.getText(), meaning.getText(), apiAudio);
      DictionaryMap.add(lblWord.getText(), meaning.getText());
      History.add(lblWord.getText());
    } catch (Exception err) {
      System.out.println("Lỗi không xác định");
    }
    try {
      infoBox.start(new Stage(), "Đã thêm " + lblWord.getText() + " vào từ điển.");
    } catch (Exception err) {
      System.out.println("Lỗi không xác định");
    }
  }
}