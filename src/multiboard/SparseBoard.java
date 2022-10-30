package multiboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * SparseBoard class for PA1.
 *
 * @param <T> generic cell type
 */
public class SparseBoard<T extends Cell> extends Board<T> {

  HashMap<Point, T> board;


  /**
   * Constructor for SparseBoard.
   *
   * @param width board width
   * @param height board height
   */
  public SparseBoard(int width, int height) {
    super(width, height);
    board = new HashMap();
  }

  @Override
  public T get(int col, int row) {
    return board.get(new Point(col, row));
  }

  @Override
  public void set(int col, int row, T cell) {
    board.put(new Point(col, row), cell);
  }

  @Override
  public void reset() {
    board.clear();
  }

  @Override
  public Iterable<T> queryCells(Predicate<T> cellPredicate) {
    Iterator<T> iterator = new ArrayList<T>().iterator();
    return () -> iterator;
    // return null;
  }

  @Override
  public Iterable<Point> locations() {
    return board.keySet();
  }

  /**
   * Iterate through sparseboard elements.
   */
  private class SparseIterator implements Iterator<T> {

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public T next() {
      return null;
    }


  }
}
