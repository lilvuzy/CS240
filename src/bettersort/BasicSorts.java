package bettersort;

import java.util.Arrays;
import java.util.Random;

/**
 * Basic n^2 sorting algorithms.
 *
 */
public class BasicSorts {
  /**
   * Sort the given array using insertion sort.
   *
   * @param items The array to sort.
   * @param <T> generic parameter.
   */
  public static <T extends Comparable<T>> void insertionSort(T[] items) {
    for (int i = 1; i < items.length; i++) {
      T itemToShift = items[i];

      // Displace any smaller items one cell to the right.
      int j = i;
      while (j > 0 && itemToShift.compareTo(items[j - 1]) < 0) {
        items[j] = items[j - 1];
        j--;
      }

      items[j] = itemToShift;
    }
  }

  /**
   * Sort only the given window of the array.
   *
   * @param items The array to partially sort.
   * @param start The inclusive starting index of the window.
   * @param end The inclusive ending index of the window.
   * @param <T> generic parameter.
   */
  public static <T extends Comparable<T>> void insertionSubsort(T[] items, int start, int end) {
    for (int i = start + 1; i < end + 1; i++) {
      T itemToShift = items[i];

      // Displace any smaller items one cell to the right.
      int j = i;
      while (j > start && itemToShift.compareTo(items[j - 1]) < 0) {
        items[j] = items[j - 1];
        j--;
      }

      items[j] = itemToShift;
    }
  }

  /**
   * Selection sort the given array.
   *
   * @param items The array to sort.
   * @param <T> generic parameter
   */
  public static <T extends Comparable<T>> void selectionSort(T[] items) {
    for (int i = 0; i < items.length - 1; i++) {
      int smallIndex = i;

      for (int j = smallIndex + 1; j < items.length; j++) {
        if (items[j].compareTo(items[smallIndex]) < 0) {
          smallIndex = j;
        }
      }

      swap(items, smallIndex, i);
    }
  }

  /**
   * Swap items i and j in the provided array.
   *
   * @param i i index
   * @param j j index
   * @param items original array
   * @param <T> generic parameter
   */
  public static <T extends Comparable<T>> void swap(T[] items, int i, int j) {
    T tmp = items[i];
    items[i] = items[j];
    items[j] = tmp;
  }
}
