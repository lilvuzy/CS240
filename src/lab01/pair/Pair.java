package lab01.pair;

/**
 * Mutable 2-tuple type.
 *
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version V4, 8/2021
 */
public class Pair <T, U>{
  private T first;
  private U second;

  /**
   * Create a Pair with the provided objects.
   *
   * @param first The first object.
   * @param second The second object.
   */
  public Pair(T first, U second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Access the first component.
   *
   * @return The first component.
   */
  public T getFirst() {
    return first;
  }

  /**
   * Set the first component.
   *
   * @param first The new component.
   */
  public void setFirst(T first) {
    this.first = first;
  }

  /**
   * Access the second component.
   *
   * @return The second component.
   */
  public U getSecond() {
    return second;
  }

  /**
   * Set the second component.
   *
   * @param second The new component.
   */
  public void setSecond(U second) {
    this.second = second;
  }

  @Override
  public String toString() {
    return "<" + first.toString() + ", " + second.toString() + ">";
  }
}