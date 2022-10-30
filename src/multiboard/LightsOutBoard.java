package multiboard;

import java.awt.*;
import java.util.function.Predicate;
import java.util.Random;

/**
 * LightsOutBoard class for PA1.
 *
 * @param <T> generic cell
 */
public class LightsOutBoard<T extends Cell> extends ArrayBoard<T> {

  private Random random;

  /**
   * Contructor for LightsOutBoard.
   *
   * @param seed seedfor random number generator
   */
  public LightsOutBoard(long seed) {
    super(5, 5);
    random = new Random(seed);
    for (int i = 0; i < width(); i++) {
      for (int j = 0; j < height(); j++) {
        board[i][j] = new OnOffCell();
      }
    }
    reset();
  }

  /**
   * Generates a random board of on-cells.
   */
  public void reset() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        OnOffCell temp = (OnOffCell) get(i, j);
        if (temp.isOn()) {
          temp.tick();
        }
      }
    }

    for (int i = 0; i < 10; i++) {
      click(random.nextInt(5), random.nextInt(5));
    }

  }

  @Override
  public void click(int col, int row) {
    // tick direct cell
    get(col, row).tick();
    // tick left
    if (get(col - 1, row) != null) {
      get(col - 1, row).tick();
    }
    // tick right
    if (get(col + 1, row) != null) {
      get(col + 1, row).tick();
    }
    // tick up
    if (get(col, row - 1) != null) {
      get(col, row - 1).tick();
    }
    // tick down
    if (get(col, row + 1) != null) {
      get(col, row + 1).tick();
    }
  }

}
