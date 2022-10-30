package bettersort;

import java.util.Random;


public class MergeSortImproved {
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
    // TODO
  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the
   * fallback method used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    // TODO
  }
}
