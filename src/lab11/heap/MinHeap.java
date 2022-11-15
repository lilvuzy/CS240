package lab11.heap;

/**
 * MinHeap class for heap lab.
 *
 * @param <T> generic parameter extending comparable.
 */
public class MinHeap<T extends Comparable> {
  public T type;
  public final Comparable[] items;
  private int size;
  private final int capacity;

  /**
   * Constructor for MinHeap.
   *
   * @param capacity capacity to initialize the array to.
   */
  public MinHeap(int capacity) {
    items = new Comparable[capacity];
    this.capacity = capacity;
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

  public void add(T item) {
    if (size == capacity) {
      throw new IllegalStateException("Heap is full, cannot add any more items.");
    }

    // Append item to heap

    // Percolate item to proper position.
  }
}
