package madzip;

import java.util.NoSuchElementException;

/**
 * MinHeap class for heap lab.
 * Project includes starting code pulled from ZyBook CS240 Chapter 14.
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
    items[0] = items[size - 1];

    // Percolate new head down to proper position.
    minHeapPercolateDown(0);

    // Decrement heap size.
    size--;

    // return value
    return minElement;
  }

  /**
   * Percolate up newly added value to proper position in min heap.
   *
   * @param index starting index.
   */
  private void minHeapPercolateUp(int index) {
    while (index > 0) {
      int parentIndex = (index - 1) / 2;
      if (items[index].compareTo(items[parentIndex]) > 0) {
        return;
      }
      else {
        Comparable tempItem = items[parentIndex];
        items[parentIndex] = items[index];
        items[index] = tempItem;
        index = parentIndex;
      }
    }
  }

  /**
   * Percolate down newly added value to proper position in min heap.
   *
   * @param index starting index.
   */
  private void minHeapPercolateDown(int index) {
    // TO-DO
    int childIndex = 2 * index + 1;
    Comparable value = items[index];

    while (childIndex < size) {
      // Find the max among the node and all the node's children
      Comparable minValue = value;
      int minIndex = -1;
      for (int i = 0; i < 2 && i + childIndex < size; i++) {
        if (items[i + childIndex].compareTo(minValue) < 0) {
          minValue = items[i + childIndex];
          minIndex = i + childIndex;
        }
      }

      // If process is complete and the item has been percolated down.
      if (minValue == value) {
        return;
      }
      else {
        // Store minIndex value.
        Comparable tempItem = items[minIndex];

        // Move the current item down.
        items[minIndex] = items[index];

        // Move the compared item up.
        items[index] = tempItem;
        index = minIndex;
        childIndex = 2 * index + 1;
      }
    }
  }

  /**
   * Main method for MinHeap testing.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    MinHeap newHeap = new MinHeap(10);

    for (int i = 0; i < 10; i++) {
      newHeap.add(i);
    }
    System.out.println(newHeap.remove());
    System.out.println(newHeap.remove());
    System.out.println(newHeap.remove());
  }
}
