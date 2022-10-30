package lab01.pair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Pair class.
 */
public class PairTest {

  /**
   * Test constructing and accessing a Character-Integer pair.
   */
  @Test
  public void testPairCharacterInteger() {
    Pair pair = new Pair('B', 12);

    // TODO: With generics, these should not use casting or intValue() calls:
    assertEquals('B', (pair.getFirst()));
    assertEquals(12, (pair.getSecond()));
  }

  /**
   * Test constructing and accessing a Double-Double pair.
   */
  @Test
  public void testPairDoubleDouble() {
    Pair pair = new Pair(1.0, 2.0);

    // TODO: Create a test similar to above
    assertEquals(1.0, (pair.getFirst()));
    assertEquals(2.0, pair.getSecond());
  }

  /**
   * Test constructing and accessing a String-Integer pair.
   */
  @Test
  public void testPairStringInteger() {
    Pair pair = new Pair("test", 1);

    // TODO: Create a test similar to above
    assertEquals("test", pair.getFirst());
    assertEquals(1, pair.getSecond());
  }
  
  /**
   * Test toString serialization.
   */
  @Test
  public void testPairToString() {
    // Do NOT change this method beyond uncommenting the lines
    // below. Fix Pair to make the test pass.
    Pair<Integer, Integer> pair = new Pair<>(1, 2);
    assertEquals("<1, 2>", pair.toString());
  }

  /**
   * Test toString serialization of nested pair.
   */
  @Test
  public void testNestedPairToString() {
    // Do NOT change this method beyond uncommenting the lines
    // below. Fix Pair to make the test pass.
    // Pair<Integer, Integer> ab = new Pair<>(5, 6);
    // Pair<Pair<Integer, Integer>, Integer> pair = new Pair<>(ab, 10);
    // assertEquals("<<5, 6>, 10>", pair.toString());
  }
}