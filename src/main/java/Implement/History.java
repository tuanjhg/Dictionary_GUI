package Implement;

import java.util.ArrayList;
import java.util.List;

public class History {
  private static final List<String> his = new ArrayList<>();
  public static void add(String word) {
    his.add(0, word);
  }

  public static void delete(String word) {
    while (his.contains(word)) {
      his.remove(word);
    }
  }

  public static String[] getList() {
    if (his.isEmpty()) {
      return new String[]{};
    }
    return his.toArray(new String[(int)his.size()]);
  }
}
