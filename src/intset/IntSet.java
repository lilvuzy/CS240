package intset;

import java.util.Iterator;

/**
 * IntSet interface for PA2 intset.
 */
public interface IntSet extends Iterable<Integer> {

  /**
   * Checks if set is empty.
   *
   * @return true if set has no non-sentinel nodes
   */
  boolean isEmpty();

  /**
   * Size method for IntSet.
   *
   * @return number of integers spanning set
   */
  int size();

  /**
   * Interval count for IntSet.
   *
   * @return number of intervals in set.
   */
  int getIntervalCount();

  /**
   * Get interval at a specifiec index.
   *
   * @param i index to get interval
   * @return interval at index i
   */
  Interval getInterval(int i);

  /**
   * Check if intset contains a given number.
   *
   * @param num number to check
   * @return true if contained in an interval in set
   */
  boolean contains(int num);

  /**
   * Add a number to the intset.
   *
   * @param toAdd number to add
   * @return true if added, false if not
   */
  boolean add(int toAdd);

  /**
   * Remove number from intset.
   *
   * @param toRemove number to remove
   */
  void remove(int toRemove);

  /**
   * Custom iterator for intset.
   */
  abstract class IntIterable implements Iterable<Integer> {
    abstract class IntIterator implements Iterator {

    }

  }

}
