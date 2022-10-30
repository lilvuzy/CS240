package lab02.search;

/**
 * Main test class for lab 02.
 */
public class Main {

  /**
   * Main method.
   *
   * @param args .
   */
  public static void main(String[] args) {

    Integer[] searchArray = new Integer[]{1, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    SearchProfiler search = new SearchProfiler<>(searchArray);

    SearchProfiler.printSummary(search.profileAllBinary());

  }
}
