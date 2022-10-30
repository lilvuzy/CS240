package intset;

/**
 * Interval class for PA2 intset.
 *
 * @author Yavuz Yavuzer
 */
public class Interval {
  private int lowerBound;
  private int upperBound;

  /**
   * Constructor for Interval class.
   *
   * @param lowerBound interval lower bound
   * @param upperBound interval upper bound
   */
  public Interval(int lowerBound, int upperBound) {
    if (lowerBound > upperBound) {
      throw new IllegalArgumentException();
    }
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  /**
   * Getter for interval lower bound.
   *
   * @return lowerBound
   */
  public int getLowerBound() {
    return lowerBound;
  }

  /**
   * Getter for interval upper bound.
   *
   * @return upperBound
   */
  public int getUpperBound() {
    return upperBound;
  }

  /**
   * Number of integers in span of bounds.
   *
   * @return interval size
   */
  public int size() {
    return Math.abs(upperBound - lowerBound) + 1;
  }

  /**
   * Adjust bounds to include integer.
   *
   * @param toInclude int to include
   */
  public void enclose(int toInclude) {
    if (toInclude <= lowerBound) {
      lowerBound = toInclude;
    } else if (toInclude >= upperBound) {
      upperBound = toInclude;
    }
  }

  /**
   * Adjust lower bound of interval.
   *
   * @param newLowerBound new value to adjust lower bound to
   */
  public void setLowerBound(int newLowerBound) {
    if (newLowerBound > upperBound) {
      throw new IllegalArgumentException();
    }
    lowerBound = newLowerBound;
  }

  /**
   * Adjust upper bound of interval.
   *
   * @param newUpperBound new value to adjust upper bound to
   */
  public void setUpperBound(int newUpperBound) {
    if (newUpperBound < lowerBound) {
      throw new IllegalArgumentException();
    }
    upperBound = newUpperBound;
  }

  /**
   * Check if interval contains value.
   *
   * @param num value to check
   * @return true only if value is within interval bounds
   */
  public boolean contains(int num) {
    return num >= lowerBound && num <= upperBound;
  }

  /**
   * Checks if interval object is the same as current interval.
   *
   * @param obj Interval to compare
   * @return true only if interval matches upper and lower bounds
   */
  public boolean equals(Object obj) {
    if (obj instanceof Interval) {
      return ((Interval) obj).lowerBound == lowerBound
              && ((Interval) obj).upperBound == upperBound;
    }
    return false;
  }

  /**
   * Returns textual representation of interval.
   *
   * @return string in format "[lowerBound]-[upperbound]" ie. "45-57"
   */
  public String toString() {
    if (upperBound == lowerBound) {
      return String.valueOf(upperBound);
    }
    return lowerBound + "-" + upperBound;
  }
}
