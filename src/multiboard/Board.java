package multiboard;

import java.awt.*;
import java.util.function.Predicate;

/**
 * Board class for PA 1.
 */
public abstract class Board<T extends Cell> {

  private final int width;
  private final int height;

  /**
   * Constructor for Board class.
   *
   * @param width  width to set board to.
   * @param height height to set board to.
   */
  public Board(int width, int height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Getter for board width.
   *
   * @return width of board as int.
   */
  public int width() {
    return width;
  }

  /**
   * Getter for board height.
   *
   * @return height of board as int.
   */
  public int height() {
    return height;
  }

  /**
   * Ticks the designated cell if it is not null.
   *
   * @param col column for cell
   * @param row row for cell
   */
  public void tick(int col, int row) {
    if (this.has(col, row)) {
      this.get(col, row).tick();
    }
  }

  /**
   * Clicks the designated cell if it is not null.
   *
   * @param col column for cell
   * @param row row for cell
   */
  public void click(int col, int row) {
    if (this.has(col, row)) {
      this.get(col, row).tick();
    }
  }

  /**
   * Getter for cell in row, column.
   *
   * @param col column to search
   * @param row row to search
   * @return cell in designated space
   */
  public abstract Cell get(int col, int row);

  /**
   * Sets cell in designated row, column.
   *
   * @param col  column
   * @param row  row
   * @param cell cell to set
   */
  public abstract void set(int col, int row, T cell);

  /**
   * Checks if designated cell location is not null.
   *
   * @param col column to check
   * @param row row to check
   * @return true if location is not null.
   */
  public boolean has(int col, int row) {
    return get(col, row) != null;
  }

  /**
   * Finds locations of cells that are not null.
   *
   * @return iterable of non-null cell locations.
   */
  public abstract Iterable<Point> locations();

  /**
   * Tterates and finds cells for which the predicate tests true.
   *
   * @param cellPredicate cell to test
   * @return iterable of cell type
   */
  public abstract Iterable<T> queryCells(Predicate<T> cellPredicate);

  /**
   * Resets board configuration.
   */
  public abstract void reset();


}
