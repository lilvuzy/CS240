package lab04.recursion;

/**
 * Collatz class for lab04.
 *
 * @author Yavuz Yavuzer
 */
public class Collatz {
  /**
   * Find number of transformations in order to make input reach 1.
   *
   * @param num number to transform
   * @return number of transformations needed
   */
  public static int collatzCount(int num) {
    if (num == 1) {
      return 0;
    }
    if (num % 2 == 0) {
      return 1 + collatzCount(num / 2);
    } else {
      return 1 + collatzCount(num*3 + 1);
    }
  }
}
