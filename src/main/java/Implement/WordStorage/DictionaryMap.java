package Implement.WordStorage;
import java.util.TreeMap;

public class DictionaryMap {
  private static final TreeMap<String, String> map = new TreeMap<>();
  public static void add(String word, String meaning) {
    map.put(word, meaning);
  }


  public static void delete(String word) {
    map.remove(word);
  }
  public static String[] getKey() {
    return map.keySet().toArray(new String[map.size()]);
  }

  public static Boolean exist(String word) {
    return map.containsKey(word);
  }
}
