package bettersort;

import static bettersort.BasicSorts.insertionSubsort;

/**
 * Collection of improved mergeSort operations.
 */
public class MergeSortImproved {

  private static final int MERGE_SORT_THRESHOLD = 40;

  /**
   * Merge sort the provided array using an improved merge operation.
   *
   * @param items original array.
   * @param <T>   generic parameter.
   */
  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    T[] tmps = (T[]) new Comparable[(items.length / 2) + 1];
    mergeSortHalfSpace(items, tmps, 0, items.length - 1);
  }

  /**
   * Recursive helper method for the merge sort algorithm.
   *
   * @param items The array to sort
   * @param tmps  Temporary array for merge operation
   * @param left  Index of the left end of the region to sort
   * @param right Index of the right end of the region to sort.
   * @param <T>   generic parameter.
   */
  private static <T extends Comparable<T>> void mergeSortHalfSpace(
          T[] items, T[] tmps, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;

      mergeSortHalfSpace(items, tmps, left, mid);
      mergeSortHalfSpace(items, tmps, mid + 1, right);
      merge(items, tmps, left, mid, right);
    }
  }

  /**
   * Merge two sorted sub-arrays using half the space of a traditional merge function.
   *
   * @param items original array.
   * @param left  left index.
   * @param mid   mid index.
   * @param right right index.
   * @param tmps  temp array.
   * @param <T>   generic parameter.
   */
  private static <T extends Comparable<T>> void merge(
          T[] items, T[] tmps, int left, int mid, int right) {

    for (int tempIndex = 0, itemIndex = left; itemIndex <= mid; itemIndex++, tempIndex++) {
      tmps[tempIndex] = items[itemIndex];
    }

    int mergeIndex = left;
    int rightIndex = mid + 1;
    int leftIndex = 0;
    while (rightIndex <= right && leftIndex <= mid - left) {
      if (tmps[leftIndex].compareTo(items[rightIndex]) <= 0) {
        items[mergeIndex] = tmps[leftIndex];
        leftIndex++;
      } else {
        items[mergeIndex] = items[rightIndex];
        rightIndex++;
      }
      mergeIndex++;
    }

    while (rightIndex <= right) {
      items[mergeIndex] = items[rightIndex];
      rightIndex++;
      mergeIndex++;
    }

    while (leftIndex <= mid - left) {
      items[mergeIndex] = tmps[leftIndex];
      leftIndex++;
      mergeIndex++;
    }
  }

  /**
   * Merge sort the provided array by using an improved merge operation and
   * switching to insertion sort for small sub-arrays.
   *
   * @param items original array.
   * @param <T>   generic parameter.
   */
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) {
    T[] tmps = (T[]) new Comparable[(items.length / 2) + 1];
    mergeSortAdaptive(items, tmps, 0, items.length - 1);
  }

  /**
   * Recursive helper method for adaptive merge sort algorithm.
   *
   * @param items the array to sort
   * @param tmps  temporary array for merge operation
   * @param left  index of left end of region to sort
   * @param right index of right end of region to sort
   * @param <T>   generic parameter.
   */
  private static <T extends Comparable<T>> void mergeSortAdaptive(
          T[] items, T[] tmps, int left, int right) {
    if (right - left < MERGE_SORT_THRESHOLD) {
      insertionSubsort(items, left, right);
    } else {
      int mid = (left + right) / 2;
      mergeSortAdaptive(items, tmps, left, mid);
      mergeSortAdaptive(items, tmps, mid + 1, right);
      merge(items, tmps, left, mid, right);
    }
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the
   * fallback method used by introspective sort.
   *
   * @param items original array.
   * @param start start index.
   * @param end   end index.
   * @param <T>   generic parameter.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    T[] tmps = (T[]) new Comparable[(items.length / 2) + 1];
    mergeSortAdaptive(items, tmps, start, end);
  }
}
