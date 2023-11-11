package Implement.Output;

import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import Implement.WordStorage.Trie.TrieNode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ExportToFile {
  public void export() {
    try {
      String Path = "src/main/resources/dictionaries.txt";
      File file = new File(Path);
      file.createNewFile();
      FileWriter writer = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(writer);
      String[] word = DictionaryMap.getKey();
      for (String tmp : word) {
        TrieNode node = Trie.find(tmp);
        bufferedWriter.write(node.getFullWord() + '\n');
        bufferedWriter.write(node.getSpelling() + '\n');
        bufferedWriter.write(node.getAudio() + '\n');
        bufferedWriter.write(node.getMeaning() + '\n');
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
