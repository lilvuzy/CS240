package multiboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * ArrayBoard class for multiboard.
 */
public abstract class ArrayBoard<T extends Cell> extends Board<T> {

  Object[][] board;

  /**
   * Constructor for ArrayBoard.
   *
   * @param width  board width
   * @param height board height
   */
  public ArrayBoard(int width, int height) {
    super(width, height);
    board = new Object[width][height];
  }

  @Override
  public T get(int col, int row) {

    if ((col >= this.width() || col < 0) || (row >= this.height() || row < 0)) {
      return null;
    }
    return (T) board[col][row];
  }

  @Override
  public void set(int col, int row, Cell cell) {
    if ((col < this.width() && row < this.height()) && (col >= 0 && row >= 0)) {
      board[col][row] = cell;
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public Iterable<Point> locations() {
    ArrayList<Point> locations = new ArrayList<Point>();
    for (int i = 0; i < width(); i++) {
      for (int j = 0; j < height(); j++) {
        if (get(i, j) != null) {
          locations.add(new Point(i, j));
        }
      }
    }
    return locations;
  }



  @Override
  public Iterable<T> queryCells(Predicate<T> cellPredicate) {

    ArrayList<T> passingCells = new ArrayList<T>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (cellPredicate.test((T) board[i][j])) {
          passingCells.add((T) board[i][j]);
        }
      }
    }
    Iterator<T> iterator = new ArrayList<T>().iterator();
    //ArrayIterator<T> iterator = locations().iterator();
    return () -> iterator;
  }


  /**
   * ArrayIterator class.
   *
   * @param <T> cell bound generic
   */
  public class ArrayIterator<T> implements Iterator<T> {
    int col = 0;
    int row = 0;

    int maxCol = width() - 1;
    int maxRow = height() - 1;

    @Override
    public boolean hasNext() {
      if (col + 1 >= width() && row + 1 >= height()) {
        return false;
      }
      return true;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      T cell = (T) get(col, row);

      if (col < board[row].length - 1) {
        col++;
      }
      if (col >= board[row].length - 1) {
        col = 0;
        row++;
      }
      return cell;
    }

    @Override
    public void remove() {
      if (board[col][row] == null) {
        throw new IllegalStateException();
      }
      board[col][row] = null;
    }
  }
}
