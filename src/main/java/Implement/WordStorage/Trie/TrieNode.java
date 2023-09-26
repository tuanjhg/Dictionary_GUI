package Implement.WordStorage.Trie;

import java.util.TreeMap;

public class TrieNode {
  TreeMap<Character, TrieNode> child = new TreeMap<>();
  Boolean isEndOfWord, bookmarked;
  String fullWord, meaning, spelling;
  int numPrefix;
  TrieNode() {
    isEndOfWord = bookmarked = false;
    fullWord = meaning = spelling = "";
    numPrefix = 0;
  }

  public TreeMap<Character, TrieNode> getChild() {
    return child;
  }

  public void setChild(TreeMap<Character, TrieNode> child) {
    this.child = child;
  }

  public Boolean getEndOfWord() {
    return isEndOfWord;
  }

  public void setEndOfWord(Boolean endOfWord) {
    isEndOfWord = endOfWord;
  }

  public Boolean getBookmarked() {
    return bookmarked;
  }

  public void setBookmarked(Boolean bookmarked) {
    this.bookmarked = bookmarked;
  }

  public String getFullWord() {
    return fullWord;
  }

  public void setFullWord(String fullWord) {
    this.fullWord = fullWord;
  }

  public String getMeaning() {
    return meaning;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public String getSpelling() {
    return spelling;
  }

  public void setSpelling(String spelling) {
    this.spelling = spelling;
  }

  public int getNumPrefix() {
    return numPrefix;
  }

  public void setNumPrefix(int numPrefix) {
    this.numPrefix = numPrefix;
  }
}