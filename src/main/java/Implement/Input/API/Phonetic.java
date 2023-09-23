package Implement.Input.API;

public class Phonetic {
  private String text = new String();
  private String audio = new String();

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getAudio() {
    return audio;
  }

  public void setAudio(String audio) {
    this.audio = audio;
  }
}