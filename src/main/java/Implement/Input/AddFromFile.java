package Implement.Input;

import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AddFromFile {
  public static void add() {
    try {
      String Path = "src/main/resources/dictionaries.txt";
      File file = new File(Path);
      file.createNewFile();
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String word, spelling, audio;
      StringBuilder meaning;
      while ((word = bufferedReader.readLine()) != null) {
        spelling = bufferedReader.readLine();
        audio = bufferedReader.readLine();
        meaning = new StringBuilder();
        String tmp;
        while ((tmp = bufferedReader.readLine()) != null) {
          meaning.append(tmp).append("\n");
          if (tmp.isBlank()) {
            meaning.deleteCharAt(meaning.length() - 1);
            break;
          }
        }
        if (word.isBlank()) {
          continue;
        }
        Trie.add(word, spelling, meaning.toString(), audio);
        DictionaryMap.add(word, meaning.toString()); // Thêm vào Map
      }
      bufferedReader.close();
    } catch (Exception e)
    {
      System.out.println("Unknown Error");
    }
  }
}