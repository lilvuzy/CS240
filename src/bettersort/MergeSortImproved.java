package bettersort;

import java.util.Random;

import static bettersort.BasicSorts.insertionSubsort;


public class MergeSortImproved {

  private static final int MERGE_SORT_THRESHOLD = 100;
  /**
   * Merge sort the provided array using an improved merge operation.
   */
  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    // TODO
  }

  /**
   * Merge sort the provided array by using an improved merge operation and
   * switching to insertion sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) {
    T[] tmps = (T[]) new Comparable[items.length];
    mergeSortAdaptive(items, tmps, 0, items.length - 1);
  }

  /**
   * Recursive helper method for adaptive merge sort algorithm.
   *
   * @param items the array to sort
   * @param tmps temporary array for merge operation
   * @param left index of left end of region to sort
   * @param right index of right end of region to sort
   */
  private static <T extends Comparable<T>> void mergeSortAdaptive(T[] items, T[] tmps, int left, int right) {
    if (items.length < MERGE_SORT_THRESHOLD) {
      insertionSubsort(tmps, left, right);
    } else {
      int mid = (left + right) / 2;
      mergeSortAdaptive(items, tmps, left, mid);
      mergeSortAdaptive(items, tmps, mid + 1, right);
      merge(items, tmps, left, mid, right);
    }
  }

  /**
   * Merge two sorted sub-arrays.
   */
  private static <T extends Comparable<T>> void merge(T[] items, T[] tmps, int left, int mid, int right) {
    // Copy items to temporary array since we're about to
    // chaotically reorganize the original.
    for (int i = left; i <= right; ++i) {
      tmps[i] = items[i];
    }

    int i = left;
    int leftIndex = left;
    int rightIndex = mid + 1;

    // Compare heads until one subarray is empty.
    for (; leftIndex <= mid && rightIndex <= right; ++i) {
      if (tmps[leftIndex].compareTo(tmps[rightIndex]) <= 0) {
        items[i] = tmps[leftIndex];
        ++leftIndex;
      } else {
        items[i] = tmps[rightIndex];
        ++rightIndex;
      }
    }

    // Copy over remainder of left subarray.
    for (; leftIndex <= mid; ++leftIndex, ++i) {
      items[i] = tmps[leftIndex];
    }

    // Copy over remainder of right subarray.
    for (; rightIndex <= right; ++rightIndex, ++i) {
      items[i] = tmps[rightIndex];
    }
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the
   * fallback method used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    // TODO
  }
}
