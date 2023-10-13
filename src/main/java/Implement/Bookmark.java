package Implement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bookmark {
  private static final List<String> saved = new ArrayList<>();
  public static void add(String word) {
    saved.add(word);
  }

  public static void delete(String word) {
    saved.remove(word);
  }
  public static boolean find(String word) {
    return saved.contains(word);
  }

  public static String[] getList() {
    if (saved.isEmpty()) {
      return new String[]{};
    }
    Collections.sort(saved);
    return saved.toArray(new String[(int)saved.size()]);
  }
}
