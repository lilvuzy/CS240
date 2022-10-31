package bettersort;

import java.util.Random;

import static bettersort.MergeSortImproved.mergeSubsortAdaptive;

public class IntrospectiveSort {
  public static <T extends Comparable<T>> void introspectiveSort(T[] items) {
    if (items.length > 0) {
      introspectiveSort(items, 0, items.length - 1, 0);
    }
  }

  /**
   * Recursive helper method for quicksort.
   *
   * @param items The items to sort
   * @param left The starting index of the region to sort
   * @param right The ending index of the region to sort.
   */
  private static <T extends Comparable<T>> void introspectiveSort(T[] items, int left, int right, int level) {
    if (left < right) {
      if (level > 2 * Math.floor(Math.log(items.length) / Math.log(2))) {
        mergeSubsortAdaptive(items, left, right);
      } else {
        int lastSmallIndex = partition(items, left, right);
        introspectiveSort(items, left, lastSmallIndex, level + 1);
        introspectiveSort(items, lastSmallIndex + 1, right, level + 1);
      }
    }
  }

  /**
   * Partition the indicated region of the array. The pivot item will be placed in
   * its final sorted position, with all smaller elements moved to the left and
   * all larger elements moved to the right.
   *
   * @return The final index of the pivot item.
   */
  protected static <T extends Comparable<T>> int partition(T[] items, int left, int right) {
    int pivotIndex = left + (right - left) / 2;
    T pivot = items[pivotIndex];

    // Advance from both ends until window collapses.
    boolean isDone = false;
    while (!isDone) {
      // Skip rightward past < pivots.
      while (items[left].compareTo(pivot) < 0) {
        left++;
      }

      // Skip leftward past > pivots.
      while (items[right].compareTo(pivot) > 0) {
        right--;
      }

      if (left < right) {
        BasicSorts.swap(items, left, right);
        left++;
        right--;
      } else {
        isDone = true;
      }
    }

    return right;
  }
}
