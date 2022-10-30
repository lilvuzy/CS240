package lab02.search;

import java.util.ArrayList;

/**
 * SearchProfiler class for lab 2 CS240.
 *
 * @param <T> the array to sort and compare.
 * @author Yavuz Yavuzer
 */

public class SearchProfiler<T extends Comparable<T>> {
  T[] arrayToCompare;

  /**
   * Constructor for SearchProfiler class.
   *
   * @param arrayToCompare array to assign.
   */
  public SearchProfiler(T[] arrayToCompare) {
    this.arrayToCompare = arrayToCompare;
  }

  /**
   * Use linear search to find item.
   *
   * @param itemToFind item to find in array.
   * @return true if found, false if not.
   */
  public SearchResult linearSearch(T itemToFind) {
    boolean isFound = false;
    int comparisonCount = 0;
    for (int i = 0; i < arrayToCompare.length; i++) {
      comparisonCount++;
      if (itemToFind.equals(arrayToCompare[i])) {
        isFound = true;
        break;
      }
    }
    return new SearchResult(itemToFind, isFound, comparisonCount);
  }

  /**
   * Use binary search to find item.
   *
   * @param itemToFind item to find in array.
   * @return true if found, false if not.
   */
  public SearchResult binarySearch(T itemToFind) {
    boolean isFound = false;
    int comparisonCount = 0;
    int upper = arrayToCompare.length - 1;
    int lower = 0;

    while (upper >= lower) {
      int mid = (lower + upper) / 2;


      comparisonCount++;
      if (itemToFind.compareTo(arrayToCompare[mid]) < 0) {
        upper = mid - 1;

      } else if (itemToFind.compareTo(arrayToCompare[mid]) == 0) {
        isFound = true;
        break;


      } else {
        lower = mid + 1;
      }

    }

    return new SearchResult(itemToFind, isFound, comparisonCount);
  }

  /**
   * Uses linear search to look for every element in array.
   *
   * @return list of search results.
   */
  public ArrayList profileAllLinear() {
    ArrayList<SearchResult> searchArray = new ArrayList<SearchResult>();

    for (T item : arrayToCompare) {
      searchArray.add(linearSearch(item));
    }
    return searchArray;
  }

  /**
   * Uses binary search to look for every element in array.
   *
   * @return list of search results.
   */
  public ArrayList profileAllBinary() {

    ArrayList<SearchResult> searchArray = new ArrayList<SearchResult>();

    for (T i : arrayToCompare) {
      searchArray.add(binarySearch(i));
    }
    return searchArray;
  }

  /**
   * Prints out the best, worst, and average cases.
   *
   * @param searchResults the array of results to print.
   */
  public static void printSummary(ArrayList<SearchResult> searchResults) {
    int worst = searchResults.get(0).getComparisonCount();
    int best = searchResults.get(0).getComparisonCount();
    double total = 0;

    for (SearchResult i : searchResults) {
      total += i.getComparisonCount();

      if (i.getComparisonCount() > worst) {
        worst = i.getComparisonCount();
      }
      if (i.getComparisonCount() < best) {
        best = i.getComparisonCount();
      }
    }

    double average = total / searchResults.size();

    System.out.println("Worst: " + worst);
    System.out.println("Best: " + best);
    System.out.printf("Average: " + "%.1f", average);
    System.out.println();

  }

}
