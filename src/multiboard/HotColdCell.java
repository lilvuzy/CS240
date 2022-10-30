package multiboard;

import java.awt.*;

/**
 * HotColdCell class for PA1.
 */
public class HotColdCell implements Cell {

  private final double temp;
  private Color color = Color.BLACK;

  /**
   * Constructor for HotColdCell.
   *
   * @param temp temperature value
   */
  public HotColdCell(double temp) {
    this.temp = temp;
  }

  /**
   * Reveals color that reflects temperature.
   */
  public void tick() {
    int rgVal = (int) Math.round(255 - (255 * temp));
    color = new Color(rgVal, rgVal, 255);
  }

  /**
   *  Get color of cell.
   *
   * @return color that reflects
   */
  public Color color() {
    return color;
  }
}
