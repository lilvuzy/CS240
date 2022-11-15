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

    // Append to next available spot in heap.
      items[size] = item;

    // Percolate item to proper position.
    minHeapPercolateUp(size);
    size++;
  }

  /**
   * Remove the minimum item in the heap.
   *
   * @return item removed.
   */
  public Comparable remove() {

    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove, there are no elements in the heap.");
    }
    Comparable minElement = items[0];

    // Move last heap item to head.
    items[0] = items[size];

    // Percolate new head down to proper position.
    minHeapPercolateDown(0);

    // return value
    return minElement;
  }

  /**
   * Percolate up newly added value to proper position in min heap.
   *
   * @param index starting index.
   */
  private void minHeapPercolateUp(int index) {

  }

  /**
   * Percolate down newly added value to proper position in min heap.
   *
   * @param index starting index.
   */
  private void minHeapPercolateDown(int index) {

  }

  /**
   * Main method for MinHeap testing.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {

  }
}
