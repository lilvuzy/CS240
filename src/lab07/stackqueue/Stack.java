package lab07.stackqueue;

import java.util.ArrayList;

public class Stack<T> {
  private ArrayList<T> items;

  public Stack() {
    items = new ArrayList<>();
  }

  public int size() {
    return items.size();
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public T peek() {
    return items.get(size() - 1);
  }

  public T pop() {
    return items.remove(size() - 1);
  }

  public void push(T item) {
    items.add(item);
  }
}
