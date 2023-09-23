package Implement.Input.API;

import java.util.ArrayList;
import java.util.List;

public class DictionaryEntry {
  private String word = new String();
  private List<Phonetic> phonetics = new ArrayList<>();
  private List<Meaning> meanings = new ArrayList<>();

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public List<Phonetic> getPhonetics() {
    return phonetics;
  }

  public void setPhonetics(List<Phonetic> phonetics) {
    this.phonetics = phonetics;
  }

  public List<Meaning> getMeanings() {
    return meanings;
  }

  public void setMeanings(List<Meaning> meanings) {
    this.meanings = meanings;
  }
}
