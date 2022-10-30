package lab02.search;

/**
 * SearchResult class for lab 2 CS240.
 *
 * @param <T> generic variable name.
 * @author Yavuz Yavuzer
 */
public class SearchResult<T extends Comparable<T>> {

  private T item;
  private boolean isFound;
  private int comparisonCount;

  /**
   * Constructor for SearchResult class.
   *
   * @param item            item to look for.
   * @param isFound         true if item is found.
   * @param comparisonCount how many comparisons were done.
   */
  public SearchResult(T item, boolean isFound, int comparisonCount) {
    this.item = item;
    this.isFound = isFound;
    this.comparisonCount = comparisonCount;
  }

  /**
   * Getter for item.
   *
   * @return item.
   */
  public T getItem() {
    return item;
  }

  /**
   * Getter for isFound.
   *
   * @return isFound.
   */
  public boolean isFound() {
    return isFound;
  }

  /**
   * Getter for comparisonCount.
   *
   * @return comparisonCount.
   */
  public int getComparisonCount() {
    return comparisonCount;
  }

  /**
   * Custom toString for SearchResult.
   *
   * @return formatted string of object values.
   */
  public String toString() {
    return (item + " " + isFound + " " + comparisonCount);
  }
}
