package Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Trie Node.
 */

public class Trie {
  private static final TrieNode root = new TrieNode();
  public static void add(String word, String spelling, String meaning) {
    TrieNode cur = root;
    int addition = 1;
    if (DictionaryMap.exist(word)) addition = 0;
    for (char c : word.toCharArray()) {
      cur.child.putIfAbsent(c, new TrieNode());
      cur = cur.child.get(c);
      cur.numPrefix += addition;
    }
    cur.isEndOfWord = true;
    cur.fullWord = word;
    if (!meaning.isBlank()) cur.meaning = meaning;
    if (!spelling.isBlank()) cur.spelling = spelling;
  }

  public static TrieNode find(String word) {
    TrieNode cur = root;
    for (char c : word.toCharArray()) {
      if (!cur.child.containsKey(c)) {
        return new TrieNode();
      }
      else {
        cur = cur.child.get(c);
      }
    }
    return cur;
  }

  public static void deleteNode(TrieNode node, StringBuilder word, int level, int lastPrefix) {
    if (level == word.length() - 1) return;
    char c = word.charAt(level);
    deleteNode(node.child.get(c), word, level + 1, lastPrefix - 1);
    if (lastPrefix <= 0) node.child.remove(c);
  }

  public static void delete(String word) {
    TrieNode cur = root;
    int lastPrefix = 0, depth = 0;
    for (char c : word.toCharArray()) {
      if (!cur.child.containsKey(c)) {
        return;
      }
      cur = cur.child.get(c);
      cur.numPrefix--;
      depth++;
      if (cur.numPrefix > 0) {
        lastPrefix = depth;
      }
    }
    if (cur.numPrefix > 0) {
      cur.isEndOfWord = false;
      cur.meaning = "";
      return;
    }
    cur = root;
    deleteNode(cur, new StringBuilder(word), 0, lastPrefix);
  }

  public static void getWordFromPrefix(TrieNode node, List<String> list) {
    if (node.isEndOfWord){
      list.add(node.fullWord);
    }
    for (Map.Entry<Character, TrieNode> temp : node.child.entrySet()) {
        getWordFromPrefix(temp.getValue(), list);
    }
  }

  public static ArrayList<String> getPrefix(String prefix) {
    TrieNode cur = root;
    ArrayList<String> ret = new ArrayList<>();
    for (char c : prefix.toCharArray()) {
      if (!cur.child.containsKey(c)) {
        return ret;
      }
      cur = cur.child.get(c);
    }
    getWordFromPrefix(cur, ret);
    return ret;
  }
}
