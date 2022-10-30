package multiboard;

import java.awt.*;

/**
 * OnOffCell class for PA 1.
 *
 * @author Yavuz Yavuzer
 */
public class OnOffCell implements Cell {
  private boolean state;

  /**
   * Constructor for OnOffCell. Initiates state to off (false).
   */
  public OnOffCell() {
    state = false;
  }

  /**
   * Changes state to opposite value.
   */
  public void tick() {
    state = !state;
  }

  /**
   * Returns a color based on state of cell.
   *
   * @return yellow if state is on, black if state is off.
   */
  public Color color() {
    if (state) {
      return Color.YELLOW;
    }
    return Color.BLACK;
  }

  /**
   * Returns the state of the cell in boolean format. On is true, off is false.
   *
   * @return true if state is on, false if state is off.
   */
  public boolean isOn() {
    return state;
  }

}
