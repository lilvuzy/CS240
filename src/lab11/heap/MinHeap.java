package lab11.heap;

import java.util.NoSuchElementException;

/**
 * MinHeap class for heap lab.
 *
 * @param <T> generic parameter extending comparable.
 */
public class MinHeap<T extends Comparable> {
  public final Comparable[] items;
  private int size;
  /**
   * Constructor for MinHeap.
   *
   * @param capacity capacity to initialize the array to.
   */
  public MinHeap(int capacity) {
    items = new Comparable[capacity];
    size = 0;
  }

  /**
   * Get the number of items currently in the heap.
   *
   * @return size variable
   */
  public int size() {
    return size;
  }

  /**
   * Check if heap is empty.
   *
   * @return true if the current size is 0.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Add item if there is space available.
   *
   * @param item item to add.
   */
  public void add(T item) {
    if (size == items.length) {
      throw new IllegalStateException("Heap is full, cannot add any more elements.");
    }

    // Append item to heap

    // Percolate item to proper position.
  }

  /**
   * Remove the minimum item in the heap.
   *
   * @return item removed.
   */
  public T remove() {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove, there are no elements in the heap.");
    }
    // remove minimum value
    // return value
    //
    return null;
  }

  /**
   * Main method for MinHeap testing.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {

  }
}
