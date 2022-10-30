package lab01.pair;

/**
 * Test driver for the Pair class.
 *
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version V3, 8/2021
 *
 */
public class PairDriver {

  /**
   * Create several stadium pairs then print the name of the stadium with the largest capacity.
   *
   * @param args Not used.
   */
  public static void main(String[] args) {

    @SuppressWarnings("unchecked")
    Pair<String, Integer>[] stadiums = new Pair[3];
    stadiums[0] = new Pair<>("Bridgeforth Stadium", 25000);
    stadiums[1] = new Pair<>("Michigan Stadium", 109901);
    stadiums[2] = new Pair<>("Lane Stadium", 66233);

    System.out.println(largestStadium(stadiums));
  }

  /**
   * Returns the name of the stadium with the largest capacity.
   *
   * @param stadiums An array of Pairs where each pair contains a stadium name
   *     and an integer capacity.
   * @return The name of the stadium with the largest capacity
   */
  public static String largestStadium(Pair[] stadiums) {
    int maxSize = 0;
    String largest = null;

    for (Pair stadium : stadiums) {
      Integer size = ((Integer) stadium.getSecond()).intValue();
      if (size > maxSize) {
        largest = (String) stadium.getFirst();
        maxSize = size;
      }
    }

    return largest;
  }
}