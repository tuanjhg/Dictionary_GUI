package GUI_App;

import Implement.AddFromFile;
import Implement.Bookmark;
import Implement.DictionaryMap;
import Implement.History;
import Implement.MutableBoolean;
import Implement.OpenAdd;
import Implement.OpenDeleteWarning;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class mainController implements Initializable{
  @FXML
  private TextField searchBar;
  @FXML
  private Label lblWord;
  @FXML
  private Label spelling;
  @FXML
  private Label type;
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
  private ImageView bookmarkStar;
  @FXML
  private ImageView recycleBin;
  @FXML
  private String currentMenu = "Search";
  String currentWord;

  String getImgPath(String name) {
    return "C:\\Users\\Admin\\Desktop\\OOP_Project\\src\\main\\resources\\Images\\" + name + ".png";
  }
  public void getSuggestion(String[] suggestion) {
    searchBar.setText("");
    suggestWord.getItems().clear();
    suggestWord.getItems().addAll(suggestion);
  }
  @Override
  public void initialize(URL url, ResourceBundle rb) {
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
                  } catch (Exception err) {
                    System.out.println("Unknown Error.");
                  } finally{
                    return;
                  }
                }
                TrieNode node = Trie.find(currentWord);
                searchBar.setText(currentWord);
                lblWord.setText(currentWord);
                spelling.setText(node.spelling);
                type.setText("•   " + node.type);
                meaning.setText("•   " + node.meaning);
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
  }

  @FXML
  public void findPrefix(KeyEvent e) {
    suggestWord.getItems().clear();
    String word = searchBar.getText();
    if (word == null || word.isEmpty()) {
      getSuggestion(DictionaryMap.getKey());
    } else if (!word.isBlank()) {
      List<String> suggestion = Trie.getPrefix(WordFormatter.normalize(word));
      if (suggestion.isEmpty()) {
        suggestion.add("Thêm...");
      }
      suggestWord.getItems().addAll(suggestion.toArray(new String[(int)suggestion.size()]));
    }
  }

  public void menuSearch(MouseEvent e) {
    imgSearch.setImage(new Image(getImgPath("searchToggle")));
    imgBookmark.setImage(new Image(getImgPath("starUntoggle")));
    imgHistory.setImage(new Image(getImgPath("hisUntoggle")));
    getSuggestion(DictionaryMap.getKey());
    currentMenu = "Search";
  }
  public void menuBookmark(MouseEvent e) {
    imgSearch.setImage(new Image(getImgPath("searchUntoggle")));
    imgBookmark.setImage(new Image(getImgPath("starToggle")));
    imgHistory.setImage(new Image(getImgPath("hisUntoggle")));
    getSuggestion(Bookmark.getList());
    currentMenu = "Bookmark";
  }
  public void menuHistory(MouseEvent e) {
    imgSearch.setImage(new Image(getImgPath("searchUntoggle")));
    imgBookmark.setImage(new Image(getImgPath("starUntoggle")));
    imgHistory.setImage(new Image(getImgPath("hisToggle")));
    getSuggestion(History.getList());
    currentMenu = "History";
  }
  public void changeBookmarkState(MouseEvent e) {
    String word = lblWord.getText();
    if (word.equals("Nghĩa của từ")) {
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
    if (word.equals("Nghĩa của từ")) {
      return;
    }
    try {
      MutableBoolean deleted = new MutableBoolean();
      OpenDeleteWarning.start(new Stage(), deleted);
      if (!deleted.isValue()) {
        return;
      }
    } catch (Exception err) {
      System.out.println("Unknown Error");
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
    type.setText("");
    meaning.setText("");
    bookmarkStar.setImage(new Image(getImgPath("starBlanked")));
  }
}