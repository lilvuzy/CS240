package lab05.rowlist;

public interface List<T> {
  int size();
  boolean contains(T item);
  void add(T item);
  void remove(int index);
  T get(int index);
}
