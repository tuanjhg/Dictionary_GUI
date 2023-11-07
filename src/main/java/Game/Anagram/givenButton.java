package Game.Anagram;

import javafx.scene.control.Button;

public class givenButton extends Button {
  private Character c;

  public Character getC() {
    return c;
  }

  public void setC(Character c) {
    this.c = c;
  }

  public givenButton(Character c) {
    this.c = c;
  }
}
