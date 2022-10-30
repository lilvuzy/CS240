package intset;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedIntSetTest {

  @Test
  public void testIteratorEmptySet() {
    LinkedIntSet i = new LinkedIntSet();

    assertThrows(NoSuchElementException.class,
            () -> {
              Iterator j = i.iterator();
            });
  }

  @Test
  public void testIteratorNextAfterDepleted() {
    LinkedIntSet i = new LinkedIntSet();
    i.add(1);
    i.add(2);
    i.add(3);
    Iterator j = i.iterator();
    while (j.hasNext()) {
      j.next();
    }
    assertThrows(NoSuchElementException.class,
            () -> {
              j.next();
            });
  }

  public static void main(String[] args) {
    LinkedIntSet i = new LinkedIntSet();

    Iterator j = i.iterator();

    j.next();

  }
}
