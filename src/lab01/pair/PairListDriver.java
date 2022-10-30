package lab01.pair;

import java.util.Iterator;

/**
 * Code illustrating the use of the Pairs class.
 *
 * @author Michael S. Kirkpatrick and Nathan Sprague
 * @version V2, 8/2021
 */

public class PairListDriver {
  /**
   * Test Driver for the Pairs class.
   *
   * @param args Ignored.
   */
  public static void main(String[] args) {
    PairList<String, Integer> pairs = new PairList<String, Integer>();
    for (int i = 0; i < 15; i++) {  // Only 0-9 will actually be added!
      pairs.addPair("A" + i, i);  
    }

    System.out.println("Printing all pairs:");
    /* Iterate through all and print */
    for (Pair<String, Integer> pair : pairs) {
      System.out.println(pair.toString());
    }
    System.out.println();

    System.out.println("Using the iterator to remove all even pairs...");
    Iterator<Pair<String, Integer>> iterator = pairs.iterator();
    while (iterator.hasNext()) {
      Pair<String, Integer> pair = iterator.next();
      /* Remove any with an even second value */
      if ((pair.getSecond() % 2) == 0) {
        iterator.remove();
      }
    }
    
    System.out.println("Printing the remaining pairs:");    
    /* Iterate again and print the remaining */
    for (Pair<String, Integer> pair : pairs) {
      System.out.println(pair.toString());
    }
    System.out.println();

    System.out.println("Adding one more item (B, 10) and printing all:");    
    /* Now add something back in and confirm it is added */
    pairs.addPair("B", 10);

    for (Pair<String, Integer> pair : pairs) {
      System.out.println(pair.toString());
    }
    System.out.println();

  }
}