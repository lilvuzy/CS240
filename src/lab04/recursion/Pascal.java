package lab04.recursion;

/**
 * Pascal class for lab 04.
 *
 * @author Yavuz Yavuzer
 */
public class Pascal {

  /**
   *  Pascal triangle class for lab 04.
   *
   * @param row row
   * @param col column
   * @return expansion of initial location
   */
  public static int pascal(int col, int row) {
    if (col > row) {
      return 0;
    }
    if (col == row || col == 0) {
      return 1;
    }
    return pascal(col - 1, row - 1) + pascal(col, row - 1);
  }
}
