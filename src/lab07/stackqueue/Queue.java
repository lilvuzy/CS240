package lab07.stackqueue;

import java.util.LinkedList;

public class Queue<T> {
  private LinkedList<T> items;

  public Queue() {
    items = new LinkedList<>();
  }

  public int size() {
    return items.size();
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public T peek() {
    return items.getFirst();
  }

  public T dequeue() {
    return items.removeFirst();
  }

  public void enqueue(T item) {
    items.addLast(item);
  }
}