package Implement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddFromFile {
  public static void add() {
    try {
      String Path = "C:\\Users\\Admin\\Desktop\\OOP_Project\\src\\main\\resources\\dictionaries.txt";
      File readerObject = new File(Path);
      Scanner fileScan = new Scanner(readerObject);
      while (fileScan.hasNext()) {
        String word = WordFormatter.normalize(fileScan.nextLine()); // Từ tiếng Anh
        String spelling = fileScan.nextLine();
        String type = WordFormatter.normalize(fileScan.nextLine());
        String meaning = WordFormatter.normalize(fileScan.nextLine()); // Nghĩa tiếng Việt
        Trie.add(word, spelling, type, meaning);
        DictionaryMap.add(word, meaning); // Thêm vào Map
      }
    } catch (FileNotFoundException e)
    {
      System.out.println("Unknown Error");
    }
  }
}
