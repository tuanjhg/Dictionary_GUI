package Implement;

public class WordFormatter {
  public static String normalize(String s) {
    if (s.equals("")) return "";
    StringBuilder res = new StringBuilder(s);
    for (int i = 0; i < res.length(); i++) {
      char c = res.charAt(i);
      res.setCharAt(i, Character.toLowerCase(c));
    }
    char first = res.charAt(0);
    res.setCharAt(0, Character.toUpperCase(first));
    return res.toString();
  }
}
