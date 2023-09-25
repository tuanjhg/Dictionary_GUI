package Implement;

import java.util.TreeMap;

public class TrieNode {
  public TreeMap<Character, TrieNode> child = new TreeMap<>();
  public Boolean isEndOfWord, bookmarked;
  public String fullWord, meaning, spelling;
  int numPrefix;
  TrieNode() {
    isEndOfWord = bookmarked = false;
    fullWord = meaning = spelling = "";
    numPrefix = 0;
  }
}