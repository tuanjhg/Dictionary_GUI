package Implement.Input;

import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import Implement.WordFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddFromFile {
  public static void add() {
    try {
      String Path = "src/main/resources/dictionaries.txt";
      File readerObject = new File(Path);
      Scanner fileScan = new Scanner(readerObject);
      while (fileScan.hasNext()) {
        String word = fileScan.nextLine();
        String spelling = fileScan.nextLine();
        String audio = fileScan.nextLine();
        StringBuilder meaning = new StringBuilder();
        while (fileScan.hasNext()) {
          String tmp = fileScan.nextLine();
          meaning.append(tmp).append("\n");
          if (tmp.isBlank()) {
            break;
          }
        }
        if (word.isBlank()) {
          continue;
        }
        Trie.add(word, spelling, meaning.toString(), audio);
        DictionaryMap.add(word, meaning.toString()); // Thêm vào Map
      }
    } catch (FileNotFoundException e)
    {
      System.out.println("Unknown Error");
    }
  }
}