package intset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedIntSet class for PA2.
 */
public class LinkedIntSet implements IntSet {

  private Node head;
  private Node tail;


  /**
   * Constructor for LinkedIntSet. Sets dummy head and tail node with null data values.
   */
  public LinkedIntSet() {
    head = new Node(null);
    tail = new Node(null);
    head.previousNode = null;
    head.nextNode = tail;
    tail.previousNode = head;
    tail.nextNode = null;
  }

  /**
   * toString method for linkedintset class.
   *
   * @return string format of object
   */
  public String toString() {
    String toReturn = "{";
    Node current = head.nextNode;
    while (current != tail) {
      toReturn += current.data.toString();
      if (current.nextNode != tail) {
        toReturn += ",";
      }
      current = current.nextNode;
    }
    toReturn += "}";
    return toReturn;
  }

  /**
   * Compares an outside object to test if it matches current linkedintset.
   *
   * @param obj object to compare
   * @return true if object is linkedintset and matches intervals
   */
  public boolean equals(Object obj) {
    if (obj instanceof LinkedIntSet) {
      if (((LinkedIntSet) obj).getIntervalCount() == getIntervalCount()) {
        Node current = head.nextNode;
        Node objCurrent = ((LinkedIntSet) obj).head.nextNode;
        while (current != tail) {
          if (current.data.getUpperBound() != objCurrent.data.getUpperBound()
                  || current.data.getLowerBound() != objCurrent.data.getLowerBound()) {
            return false;
          }
          current = current.nextNode;
          objCurrent = objCurrent.nextNode;
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isEmpty() {
    return head.nextNode == tail;
  }

  @Override
  public int size() {
    Node current = head.nextNode;
    int size = 0;
    while (current != tail) {
      size += current.data.size();
      current = current.nextNode;
    }
    return size;
  }

  @Override
  public int getIntervalCount() {
    Node current = head.nextNode;
    int intervalCount = 0;
    while (current != tail) {
      intervalCount++;
      current = current.nextNode;
    }
    return intervalCount;
  }

  @Override
  public Interval getInterval(int index) {
    if (index >= size()) {
      throw new IndexOutOfBoundsException();
    }
    Node current = head.nextNode;
    for (int i = 0; i < index; i++) {
      current = current.nextNode;
    }
    return current.data;
  }

  @Override
  public boolean contains(int num) {
    Node current = head.nextNode;
    while (current != tail) {
      if (num <= current.data.getUpperBound() && num >= current.data.getLowerBound()) {
        return true;
      }
      current = current.nextNode;
    }
    return false;
  }

  @Override
  public boolean add(int toAdd) {

    // Do nothing if num is already in an interval
    if (contains(toAdd)) {
      return false;
    } else if (isEmpty()) {
      // Create first node
      Interval newInterval = new Interval(toAdd, toAdd);
      Node newNode = new Node(newInterval);
      head.nextNode = newNode;
      tail.previousNode = newNode;
      newNode.nextNode = tail;
      newNode.previousNode = head;
      return true;
    }
    Node current = head.nextNode;
    while (current != tail) {
      // Check if num is top adjacent to current node
      if (toAdd == current.data.getUpperBound() + 1) {
        // Check if num is top adjacent to current node and bottom adjacent to
        // next node, combine nodes.
        if (current.nextNode != tail && toAdd == current.nextNode.data.getLowerBound() - 1) {
          current.data.enclose(current.nextNode.data.getUpperBound());
          // set node 2x next previous pointer to new node
          current.nextNode.nextNode.previousNode = current;
          // set new nodes pointers
          current.nextNode = current.nextNode.nextNode;
        } else {
          // Num is only top adjacent to current node, expand current node upper bound.
          current.data.enclose(toAdd);
        }
        return true;
      } else if (toAdd == current.data.getLowerBound() - 1) {
        // Check if num is bottom adjacent to current node, expand current node lower bound.
        current.data.enclose(toAdd);
        return true;
      }
      current = current.nextNode;
    }
    current = head.nextNode;

    if (toAdd < head.nextNode.data.getLowerBound()) {
      Node newNode = new Node(new Interval(toAdd, toAdd));
      newNode.nextNode = current;
      newNode.previousNode = head;
      current.previousNode = newNode;
      head.nextNode = newNode;
      return true;
    } else if (toAdd > tail.previousNode.data.getUpperBound()) {
      Node newNode = new Node(new Interval(toAdd, toAdd));
      newNode.nextNode = tail;
      newNode.previousNode = tail.previousNode;
      tail.previousNode.nextNode = newNode;
      tail.previousNode = newNode;
      return true;
    } else {
      current = head.nextNode;
      while (current != tail) {
        if (toAdd < current.nextNode.data.getLowerBound()) {
          Node newNode = new Node(new Interval(toAdd, toAdd));
          newNode.nextNode = current.nextNode;
          newNode.previousNode = current;
          current.nextNode.previousNode = newNode;
          current.nextNode = newNode;
          return true;
        }
        current = current.nextNode;
      }
    }
    return false;
  }

  @Override
  public void remove(int toRemove) {

    if (!contains(toRemove)) {
      throw new NoSuchElementException();
    }

    Node current = head.nextNode;
    while (current != tail) {
      // integer is an interval with the same upper and lower bound
      if (current.data.getLowerBound() == toRemove && current.data.getUpperBound() == toRemove) {
        // remove the entire interval
        current.nextNode.previousNode = current.previousNode;
        current.previousNode.nextNode = current.nextNode;
        break;
      } else if (current.data.getLowerBound() == toRemove) {
        current.data.setLowerBound(toRemove + 1);
      } else if (current.data.getUpperBound() == toRemove) {
        current.data.setUpperBound(toRemove - 1);
      } else if (toRemove > current.data.getLowerBound() && toRemove
              < current.data.getUpperBound()) {

        // Create new node representing upper split
        Node newNode = new Node(new Interval(toRemove + 1,
                current.data.getUpperBound()));
        // Adjust the upper bound of current interval to 1 below the int to remove
        current.data.setUpperBound(toRemove - 1);
        // Add new node above current interval with current's previous upper
        // bound and lower bound of toRemove + 1
        current.nextNode.previousNode = newNode;
        newNode.nextNode = current.nextNode;
        newNode.previousNode = current;
        current.nextNode = newNode;
      }
      current = current.nextNode;
    }

  }

  private class Node {
    public Node previousNode;
    public Node nextNode;
    public Interval data;

    Node(Interval data) {
      this.data = data;
    }
  }

  @Override
  public Iterator<Integer> iterator() {
    return new LinkedIntSetIterator();
  }

  private class LinkedIntSetIterator implements Iterator {
    Node current = head.nextNode;
    int intervalMax;
    int intervalIndex;

    public LinkedIntSetIterator() {

      if (current != tail) {
        intervalMax = current.data.getUpperBound();
        intervalIndex = current.data.getLowerBound() - 1;
      }
    }

    @Override
    public boolean hasNext() {
      if (isEmpty()) {
        return false;
      }
      return (intervalIndex != intervalMax || current.nextNode != tail)
              && getIntervalCount() != 0;
    }

    @Override
    public Object next() {
      if (!isEmpty()) {
        if (intervalIndex < intervalMax) {
          intervalIndex++;
          return intervalIndex;
        } else if (intervalIndex == intervalMax && current.nextNode != tail) {
          current = current.nextNode;
          intervalIndex = current.data.getLowerBound();
          intervalMax = current.data.getUpperBound();
          return intervalIndex;
        }
      }
      throw new NoSuchElementException();
    }
  }

}
