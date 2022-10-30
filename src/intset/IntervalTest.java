package intset;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test classes for LinkedIntSet and Interval.
 */
public class IntervalTest {

  @Test
  public void testConstructorThrowsException() {
    assertThrows(IllegalArgumentException.class,
            () -> {
              Interval i = new Interval(5, 0);
            });
  }

  @Test
  public void testGetLowerBound() {
    Interval i = new Interval(0, 5);
    assertEquals(0, i.getLowerBound());
  }

  @Test
  public void testGetUpperBound() {
    Interval i = new Interval(0, 5);
    assertEquals(5, i.getUpperBound());
  }

  @Test
  public void testGetSizeSameUpperLower() {
    Interval i = new Interval(0, 0);
    assertEquals(1, i.size());
  }

  @Test
  public void testSetLowerBoundValid() {
    Interval i = new Interval(0, 5);
    i.setLowerBound(1);
    assertEquals(1, i.getLowerBound());
  }

  @Test
  public void testSetLowerBoundOutOfBounds() {
    Interval i = new Interval(0, 5);
    assertThrows(IllegalArgumentException.class,
            () -> {
              i.setLowerBound(6);
            });
  }

  @Test
  public void testSetUpperBoundValid() {
    Interval i = new Interval(0, 5);
    i.setUpperBound(6);
    assertEquals(6, i.getUpperBound());
  }

  @Test
  public void testSetUpperBoundOutOfBounds() {
    Interval i = new Interval(0, 5);
    assertThrows(IllegalArgumentException.class,
            () -> {
              i.setUpperBound(-1);
            });
  }

  @Test
  public void testEncloseUpper() {
    Interval i = new Interval(0, 5);
    i.enclose(7);
    assertEquals(7, i.getUpperBound());
    i.enclose(8);
    assertEquals(8, i.getUpperBound());
  }

  @Test
  public void testEncloseLower() {
    Interval i = new Interval(0, 5);
    i.enclose(-1);
    assertEquals(-1, i.getLowerBound());
  }

  @Test
  public void testEncloseWithinInterval() {
    Interval i = new Interval(0, 5);
    i.enclose(3);
    assertEquals(0, i.getLowerBound());
    assertEquals(5, i.getUpperBound());

  }

  @Test
  public void testContainsNotInInterval() {
    Interval i = new Interval(0, 5);
    assertFalse(i.contains(10));
    assertFalse(i.contains(-1));
  }

  @Test
  public void testContainsInInterval() {
    Interval i = new Interval(0, 5);
    assertTrue(i.contains(3));
    assertTrue(i.contains(0));
    assertTrue(i.contains(5));
  }

  @Test
  public void testEqualsMatchingInterval() {
    Interval i = new Interval(0, 5);
    Interval j = new Interval(0, 5);
    assertTrue(i.equals(j));
  }

  @Test
  public void testEqualsNonMatchingUpperBound() {
    Interval i = new Interval(0, 5);
    Interval j = new Interval(0, 6);
    assertFalse(i.equals(j));
  }

  @Test
  public void testEqualsNonMatchingLowerBound() {
    Interval i = new Interval(0, 5);
    Interval j = new Interval(1, 5);
    assertFalse(i.equals(j));
  }

  @Test
  public void testEqualsDifferentObjType() {
    Interval i = new Interval(0, 5);
    String j = "bob";
    assertFalse(i.equals(j));
  }

  @Test
  public void testToStringSameUpperLower() {
    Interval i = new Interval(0, 0);
    assertEquals("0", i.toString());

  }

  @Test
  public void testToStringDifferentUpperLower() {
    Interval i = new Interval(0, 1);
    assertEquals("0-1", i.toString());
  }

}
