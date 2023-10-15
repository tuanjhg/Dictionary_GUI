package Implement.Input.SingleWord;

import java.util.ArrayList;
import java.util.List;

public class Meaning {
  private String partOfSpeech = new String();
  private List<Definition> definitions = new ArrayList<>();
  private List<String> synonyms = new ArrayList<>();
  private List<String> antonyms = new ArrayList<>();

  public String getPartOfSpeech() {
    return partOfSpeech;
  }

  public void setPartOfSpeech(String partOfSpeech) {
    this.partOfSpeech = partOfSpeech;
  }

  public List<Definition> getDefinitions() {
    return definitions;
  }

  public void setDefinitions(List<Definition> definitions) {
    this.definitions = definitions;
  }

  public List<String> getSynonyms() {
    return synonyms;
  }

  public void setSynonyms(List<String> synonyms) {
    this.synonyms = synonyms;
  }

  public List<String> getAntonyms() {
    return antonyms;
  }

  public void setAntonyms(List<String> antonyms) {
    this.antonyms = antonyms;
  }
}