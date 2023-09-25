package GUI_App;

import Implement.Input.API.Definition;
import Implement.Input.API.DictionaryEntry;
import Implement.Input.API.Meaning;
import Implement.Input.API.Phonetic;
import Implement.Input.AddFromAPI;
import Implement.Input.AddFromFile;
import Implement.Bookmark;
import Implement.DictionaryMap;
import Implement.History;
import Implement.MutableBoolean;
import Implement.Open.OpenAdd;
import Implement.Open.OpenDeleteWarning;
import Implement.Open.OpenInfo;
import Implement.Trie;
import Implement.TrieNode;
import Implement.WordFormatter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
  private ScrollPane scrollMeaning;
  @FXML
  private String currentMenu = "Search";
  private String currentWord;
  private MediaPlayer player;
  final String IMGPath = "C:\\Users\\Admin\\Desktop\\OOP_Project\\src\\main\\resources\\Images\\";
  boolean noSound = true;

  void setEditor(boolean type) {
    txtEditor.setVisible(type);
    imgTick.setVisible(type);
    imgCross.setVisible(type);
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
    return IMGPath + name + ".png";
  }
  public void getSuggestion(String[] suggestion) {
    searchBar.setText("");
    suggestWord.getItems().clear();
    suggestWord.getItems().addAll(suggestion);
  }
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    scrollMeaning.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
    setEditor(false);
    setSound();
    AddFromFile.add();
    getSuggestion(DictionaryMap.getKey());
    suggestWord
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            new ChangeListener<String>() {
              @Override
              public void changed(
                  ObservableValue<? extends String> observableValue, String s, String t1) {
                currentWord = suggestWord.getSelectionModel().getSelectedItem();
                if (currentWord == null || currentWord.isEmpty()) {
                  return;
                }
                if (currentWord.equals("Thêm...")) {
                  try {
                    String word = WordFormatter.normalize(searchBar.getText());
                    OpenAdd.start(new Stage(), word);
                    if (OpenAdd.added.isValue()) {
                      OpenInfo.start(new Stage(), "Đã thêm " + word + " vào từ điển.");
                    }
                  } catch (Exception err) {
                    System.out.println("Unknown Error.");
                  }
                  return;
                }
                TrieNode node = Trie.find(currentWord);
                noSound = true; setSound();
                searchBar.setText(currentWord);
                lblWord.setText(currentWord);
                spelling.setText(node.spelling);
                meaning.setText(node.meaning);
                scrollMeaning.setContent(meaning);
                if (!node.bookmarked) {
                  bookmarkStar.setImage(new Image(getImgPath("starBlanked")));
                } else {
                  bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
                }
                if (!currentMenu.equals("History")) {
                  History.add(currentWord);
                }
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
                Media media = new Media(i.getAudio());
                player = new MediaPlayer(media);
                noSound = false;
                break;
              }
            }
          }
          setSound();
          StringBuilder wordMeaning = new StringBuilder();
          for (Meaning i : entry.getMeanings()) {
            wordMeaning.append("☆   ").append(i.getPartOfSpeech()).append("\n");
            for (Definition j : i.getDefinitions()) {
              wordMeaning.append("          »   ").append(j.getDefinition()).append("\n");
              if (!j.getExample().isBlank()) {
                wordMeaning.append("\n               • Example:   ").append(j.getExample()).append("\n\n");
              }
            }
            if (!i.getSynonyms().isEmpty()) { // If i has synonyms
              wordMeaning.append("          »   Synonyms: ");
              for (String j : i.getSynonyms()) {
                wordMeaning.append(j).append(", ");
              }
              wordMeaning.delete(wordMeaning.length() - 2, wordMeaning.length() - 1);
              wordMeaning.append("\n");
            }
            if (!i.getAntonyms().isEmpty()) { // If i has antonyms
              wordMeaning.append("          »   Antonyms: ");
              for (String j : i.getAntonyms()) {
                wordMeaning.append(j).append(", ");
              }
              wordMeaning.delete(wordMeaning.length() - 2, wordMeaning.length() - 1);
              wordMeaning.append("\n");
            }
          }
          meaning.setText(wordMeaning.toString());
          scrollMeaning.setContent(meaning);
        } else {
          lblWord.setText(""); spelling.setText(""); meaning.setText("");
          try {
            OpenInfo.start(new Stage(), "Không tìm thấy " + word + " trong từ điển.");
          } catch (Exception err) {
            System.out.println("Lỗi không xác định");
          }
        }
      }
    }
  }

  void untoggleAll() {
    imgSearch.setImage(new Image(getImgPath("searchUntoggle")));
    imgBookmark.setImage(new Image(getImgPath("starUntoggle")));
    imgHistory.setImage(new Image(getImgPath("hisUntoggle")));
    imgAPI.setImage(new Image(getImgPath("apiUntoggle")));
  }
  public void menuSearch(MouseEvent e) {
    untoggleAll();
    imgSearch.setImage(new Image(getImgPath("searchToggle")));
    searchBar.setVisible(true); suggestWord.setVisible(true);
    bookmarkStar.setVisible(true); recycleBin.setVisible(true); imgEditor.setVisible(true);
    getSuggestion(DictionaryMap.getKey());
    currentMenu = "Search";
  }
  public void menuBookmark(MouseEvent e) {
    untoggleAll();
    imgBookmark.setImage(new Image(getImgPath("starToggle")));
    searchBar.setVisible(true); suggestWord.setVisible(true);
    bookmarkStar.setVisible(true); recycleBin.setVisible(true); imgEditor.setVisible(true);
    getSuggestion(Bookmark.getList());
    currentMenu = "Bookmark";
  }
  public void menuHistory(MouseEvent e) {
    untoggleAll();
    imgHistory.setImage(new Image(getImgPath("hisToggle")));
    searchBar.setVisible(false); suggestWord.setVisible(true);
    bookmarkStar.setVisible(true); recycleBin.setVisible(true); imgEditor.setVisible(true);
    getSuggestion(History.getList());
    currentMenu = "History";
  }
  public void menuAPI(MouseEvent e) {
    untoggleAll();
    imgAPI.setImage(new Image(getImgPath("apiToggle")));
    searchBar.setVisible(true); suggestWord.setVisible(false);
    bookmarkStar.setVisible(false); recycleBin.setVisible(false); imgEditor.setVisible(false);
    currentMenu = "API";
  }
  public void changeBookmarkState(MouseEvent e) {
    String word = lblWord.getText();
    if (word.equals("Từ điển") || word.isBlank()) {
      return;
    }
    TrieNode node = Trie.find(word);
    if (!node.bookmarked) {
      bookmarkStar.setImage(new Image(getImgPath("starUntoggle")));
      node.bookmarked = true;
      Bookmark.add(word);
    } else {
      bookmarkStar.setImage(new Image(getImgPath("starBlanked")));
      node.bookmarked = false;
      Bookmark.delete(word);
    }
    if (currentMenu.equals("Bookmark")) {
      getSuggestion(Bookmark.getList());
    }
  }

  public void deleteWord(MouseEvent e) {
    String word = lblWord.getText();
    if (word.equals("Từ điển")) {
      return;
    }
    try {
      MutableBoolean deleted = new MutableBoolean();
      OpenDeleteWarning.start(new Stage(), deleted, word);
      if (!deleted.isValue()) {
        return;
      }
    } catch (Exception err) {
      System.out.println("Lỗi không xác định");
    }
    try {
      OpenInfo.start(new Stage(), "Đã xóa " + word + " khỏi từ điển.");
    } catch (Exception err) {
      System.out.println("Lỗi không xác định");
    }
    Trie.delete(word);
    DictionaryMap.delete(word);
    Bookmark.delete(word);
    History.delete(word);
    if (currentMenu.equals("Search")) {
      getSuggestion(DictionaryMap.getKey());
    } else if (currentMenu.equals("Bookmark")) {
      getSuggestion(Bookmark.getList());
    } else if (currentMenu.equals("History")) {
      getSuggestion(History.getList());
    }
    searchBar.setText("");
    lblWord.setText("");
    spelling.setText("");
    meaning.setText("");
    bookmarkStar.setImage(new Image(getImgPath("starBlanked")));
  }
  public void playMedia(MouseEvent e) {
    if (player != null) {
      if (player.getStatus() == Status.PLAYING) {
        player.stop();
      }
      player.seek(Duration.ZERO);
      player.play();
    }
  }
  public void openEditor(MouseEvent e) {
    if (lblWord.getText().equals("Từ điển")) {
      return;
    }
    setEditor(true);
    txtEditor.setText(meaning.getText());
    imgSearch.setDisable(true); imgBookmark.setDisable(true); imgHistory.setDisable(true);
    imgAPI.setDisable(true); imgSpeaker.setDisable(true);
    bookmarkStar.setDisable(true); recycleBin.setDisable(true);
    imgSearch.setOpacity(0.3); imgBookmark.setOpacity(0.3); imgHistory.setOpacity(0.3);
    imgAPI.setOpacity(0.3); imgSpeaker.setOpacity(0.3); meaning.setOpacity(0);
    bookmarkStar.setOpacity(0.3); recycleBin.setOpacity(0.3);
    if (currentMenu.equals("Search")) {
      imgSearch.setOpacity(1);
    } else if (currentMenu.equals("Bookmark")) {
      imgBookmark.setOpacity(1);
    } else if (currentMenu.equals("History")) {
      imgHistory.setOpacity(1);
    }
  }
  void closeEditor() {
    setEditor(false);
    imgSearch.setDisable(false); imgBookmark.setDisable(false); imgHistory.setDisable(false);
    imgAPI.setDisable(false); imgSpeaker.setDisable(false);
    bookmarkStar.setDisable(false); recycleBin.setDisable(false);
    imgSearch.setOpacity(1); imgBookmark.setOpacity(1); imgHistory.setOpacity(1);
    imgAPI.setOpacity(1); imgSpeaker.setOpacity(1); meaning.setOpacity(1);
    bookmarkStar.setOpacity(1); recycleBin.setOpacity(1);
  }
  public void exitEditor(MouseEvent e) {
    closeEditor();
  }
  public void saveEditor(MouseEvent e) {
    String newMeaning = txtEditor.getText();
    meaning.setText(newMeaning);
    Trie.add(lblWord.getText(), "", newMeaning);
    closeEditor();
  }
}