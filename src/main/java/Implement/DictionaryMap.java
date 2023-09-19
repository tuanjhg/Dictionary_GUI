package Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.util.Pair;

public class DictionaryMap {
  private static TreeMap<String, String> map = new TreeMap<>();
  public static void add(String word, String meaning) {
    map.put(word, meaning);
  }

  public static void delete(String word) {
    map.remove(word);
  }
  /**
   * In ra toàn bộ những từ trong từ điển.
   * @return
   */
  public static List<Pair<String, String>> getAll() {
    List<Pair<String, String>> ret = new ArrayList<>();
    for (Map.Entry<String, String> pair : map.entrySet()) {
      Pair<String, String> element = new Pair<>(pair.getKey(), pair.getValue());
      ret.add(element);
    }
    return ret;
  }

  public static String[] getKey() {
    String[] ret = map.keySet().toArray(new String[map.size()]);
    return ret;
  }

  public static Boolean exist(String word) {
    return map.containsKey(word);
  }

  public static String lookUp(String word) {
    if (!map.containsKey(word)) {
      return "NotExist";
    }
    return map.get(word);
  }
}
