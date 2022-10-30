package multiboard;

import java.awt.*;

/**
 * Cell interface for multiboard PA.
 *
 * @author Yavuz Yavuzer
 */
public interface Cell {

  /**
   * Interface tick method.
   */
  void tick();

  /**
   * Interface color method.
   *
   * @return color of cell
   */
  Color color();
}
