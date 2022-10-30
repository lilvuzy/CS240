package lab01.pair;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable collection of Pair objects.
 *
 * @author Michael S. Kirkpatrick and Nathan Sprague
 * @version V2, 8/2021
 */
public class PairList<T, U> implements Iterable<Pair<T, U>> {

  public static final int CAPACITY = 10;

  private final Pair<T, U>[] pairs;
  private int size;

  /**
   * Create a collection that will store items added as pairs.
   */
  public PairList() {
    this.size = 0;
    this.pairs = new Pair[CAPACITY];
  }

  /**
   * Create a new Pair and add it to the collection if there's space.
   *
   * @param first The first object.
   * @param second The second object.
   * @return True if the pair was added to the array, False otherwise.
   */
  public boolean addPair(T first, U second) {
    if (size >= CAPACITY) {
      return false;
    }
    pairs[size] = new Pair<>(first, second);
    size++;

    return true;
  }


  @Override
  public Iterator<Pair<T, U>> iterator() {
    return new PairListIterator();
  }

  /*
   * Implement an Iterator here based on the API documentation at <a
   * https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/
   * Iterator.html Throw the exceptions as specified.
   * 
   * IMPLEMENTATION ADVICE:
   *
   * You will need two instance variables: one to keep track of the current
   * index, and one to keep track of whether a removal should be allowed.
   * (The remove method removes the element most recently returned by next.
   * If remove is called before next has been called, or if it is called
   * twice in a row, the remove method must throw an IllegalStateException.)
   * 
   * I recommend drawing a picture of an array to help you reason through the
   * behavior of the required methods.
   * 
   */
  private class PairListIterator implements Iterator<Pair<T, U>> {
    private int index = 0;
    private boolean canRemove = false;

    @Override
    public boolean hasNext() {
      return index < size;
    }

    /**
     * Return the next Pair in the iterator.
     */
    @Override
    public Pair<T, U> next() {

      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Pair<T, U> tempPair = pairs[index];
      index++;
      canRemove = true;
      return tempPair;
    }

    /**
     * Remove the previous Pair returned by next().
     */
    @Override
    public void remove() {
      // The remove method should shift all pairs one position to the left to prevent
      // a gap in the array.
      if (!canRemove) {
        throw new IllegalStateException();
      }
      for (int i = index - 1; i < size - 1; i++) {

        Pair<T, U> temporaryPair = new Pair<>(pairs[i + 1].getFirst(), pairs[i + 1].getSecond());
        pairs[i] = temporaryPair;
        temporaryPair.toString();

      }
      size--;
      pairs[size] = null;
      canRemove = false;

    }
  }
}