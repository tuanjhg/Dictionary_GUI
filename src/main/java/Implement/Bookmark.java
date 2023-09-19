package Implement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bookmark {
  private static List<String> saved = new ArrayList<>();
  public static void add(String word) {
    saved.add(word);
  }

  public static void delete(String word) {
    saved.remove(word);
  }

  public static String[] getList() {
    if (saved.isEmpty()) {
      String[] ret = new String[]{};
      return ret;
    }
    Collections.sort(saved);
    String[] ret = saved.toArray(new String[saved.size()]);
    return ret;
  }
}
