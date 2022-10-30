package lab05.rowlist;

/**
 * Rowlist class for lab05.
 *
 * @param <T> generic type T
 * @author Yavuz Yavuzer
 */
public class RowList<T> implements List<T> {

  T[] array;
  int size;
  int capacity;

  /**
   * Constructor.
   */
  public RowList() {
    array = (T[]) new Object[10];
    size = 0;
    capacity = 10;
  }

  /**
   * Number of used spaces in the array.
   *
   * @return size
   */
  public int size() {
    return size;
  }

  /**
   * Add item to the array and increase capacity if necessary.
   *
   * @param item item to add
   */
  public void add(T item) {
    if (size == capacity) {
      T[] tempArray = (T[]) new Object[capacity * 2];
      capacity = capacity * 2;
      for (int i = 0; i < size; i++) {
        tempArray[i] = array[i];
      }
      array = tempArray;
    }
    array[size] = item;
    size++;

  }

  /**
   * Remove item from array and shift elements down if necessary.
   *
   * @param index index to remove
   */
  public void remove(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    for (int i = index; i < this.size() - 1; i++) {
      array[i] = array[i + 1];
    }
    size--;
  }

  /**
   * Get item at specified index.
   *
   * @param index index to fetch item
   * @return item at index
   */
  public T get(int index) {
    if (index >= this.size()) {
      throw new IndexOutOfBoundsException();
    }
    return array[index];
  }

  /**
   * Find whether array contains element.
   *
   * @param element item to check
   * @return true if found, false if not found
   */
  public boolean contains(T element) {
    for (int i = 0; i < this.size(); i++) {
      if (array[i].equals(element)) {
        return true;
      }
    }
    return false;
  }
}
