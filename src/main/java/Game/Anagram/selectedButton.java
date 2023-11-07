package Game.Anagram;

import javafx.scene.control.Button;

public class selectedButton extends Button {
  private givenButton parentButton;

  public selectedButton(givenButton parentButton) {
    this.parentButton = parentButton;
  }

  public givenButton getParentButton() {
    return parentButton;
  }

  public void setParentButton(givenButton parentButton) {
    this.parentButton = parentButton;
  }
}
