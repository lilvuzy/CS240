package lab08.hashtable;

/**
 * Simple HashTable class.
 *
 * @author CS240 Instructors and ...
 */
public class HashTable<K, V> {
  public static final int INITIAL_CAPACITY = 16; // must be a power of 2.
  public static final double MAX_LOAD = 0.5;

  public KeyValuePair<K, V>[] table; // (Leave this non-private for testing.)
  private int size;

  /**
   * HashTable constructor.
   */
  @SuppressWarnings("unchecked")
  public HashTable() {
    table = (KeyValuePair<K, V>[]) new KeyValuePair[INITIAL_CAPACITY];
    size = 0;
  }

  /**
   * Probe for the real index of a key or return -1 if it can't be found. If
   * removal is implemented, this will skip over tombstone positions during
   * the search.
   */
  private int probe(K key) {
    int startIndex = indexFor(key.hashCode(), table.length);
    for (int i = 0; i < table.length; i++) {
      if (table[startIndex % table.length] != null && table[startIndex % table.length].key.equals(key) && !table[startIndex % table.length].isDeleted()) {
        return startIndex % table.length;
      }
      startIndex++;
    }
    return -1;
  }

  /**
   * Store the provided key/value pair.
   */
  public void put(K key, V value) {
    int index = indexFor(key.hashCode(), table.length);
    if (table[index] == null || table[index].isDeleted()) {
      table[index] = new KeyValuePair<>(key, value);
      size++;
    } else if (table[index].key().equals(key)) {
      table[index].setValue(value);
    } else {
      table[getNextAvailableIndex(index, table)] = new KeyValuePair<>(key, value);
      size++;
    }
    if ((size * 1.0) / table.length > MAX_LOAD) {
      resize();
    }
  }

  /**
   * Return the value associated with the provided key, or null if no such
   * value exists.
   */
  public V get(K key) {
    if (probe(key) == -1) {
      return null;
    } else {
      return table[probe(key)].value();
    }
  }

  /**
   * Remove the provided key from the hash table and return the associated
   * value. Returns null if the key is not stored in the table.
   */
  public V remove(K key) {
    if (probe(key) == -1) {
      return null;
    } else {
      V tempValue = table[probe(key)].value();
      table[probe(key)].delete();
      size--;
      return tempValue;
    }
  }

  /**
   * Return the number of items stored in the table.
   */
  public int size() {
    return size;
  }

  // PRIVATE HELPER METHODS BELOW THIS POINT----------

  /**
   * Double the size of the hash table and rehash all existing items.
   */
  private void resize() {
    KeyValuePair<K, V>[] newTable;
    newTable =  (KeyValuePair<K, V>[]) new KeyValuePair[table.length * 2];
    int index;

    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) {
        index = indexFor(table[i].hashCode(), newTable.length);

        K currentKey = table[i].key();
        V currentValue = table[i].value();

        if (newTable[index] == null || newTable[index].isDeleted()) {
          newTable[index] = new KeyValuePair<>(currentKey, currentValue);
        } else if (newTable[index].key().equals(currentKey)) {
          newTable[index].setValue(currentValue);
        } else {
          newTable[getNextAvailableIndex(index, newTable)] = new KeyValuePair<>(currentKey, currentValue);
        }
      }
    }
    table = newTable;
  }

  /**
   * Get the next available index to insert key value pair into using linear probing.
   */
  private int getNextAvailableIndex(int startIndex, KeyValuePair<K, V>[] tableToSearch) {
    for (int i = 0; i < tableToSearch.length; i++) {
      if (tableToSearch[startIndex] == null || tableToSearch[startIndex].isDeleted()) {
        return startIndex;
      }
      startIndex++;
    }
    return -1;
  }

  /**
   * Returns index for hash code h.
   */
  private static int indexFor(int h, int length) {
    return hash(h) & (length - 1);
  }

  /**
   * Applies a supplemental hash function to a given hashCode, which
   * defends against poor quality hash functions. This is critical
   * because HashMap uses power-of-two length hash tables, that
   * otherwise encounter collisions for hashCodes that do not differ
   * in lower bits.
   * YOU SHOULD NOT CALL THIS METHOD DIRECTLY. IT IS A HELPER FOR THE
   * indexFor METHOD ABOVE.
   */
  private static int hash(int h) {
    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }

  /**
   * KeyValuePair class is a simple wrapper for key/value pairs.
   */
  public static class KeyValuePair<K, V> { // leave this non-private for testing.
    final private K key;
    private V value;
    private boolean tombstone;

    /**
     * Create an KeyValuePair object.
     */
    public KeyValuePair(K key, V value) {
      this.key = key;
      this.value = value;
      this.tombstone = false;
    }

    /* Getters and setters */
    public K key() {
      return key;
    }

    public V value() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }

    public boolean isDeleted() {
      return tombstone;
    }

    public void delete() {
      tombstone = true;
    }
  }
}

// The hash and indexFor methods are taken directly from the Java HashMap
// implementation with some modifications. That code is licensed as follows:
/*
 * Copyright 1997-2007 Sun Microsystems, Inc. All Rights Reserved. DO NOT ALTER OR REMOVE COPYRIGHT
 * NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under the terms of the GNU
 * General Public License version 2 only, as published by the Free Software Foundation. Sun
 * designates this particular file as subject to the "Classpath" exception as provided by Sun in the
 * LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version 2 along with this work;
 * if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara, CA 95054 USA or visit
 * www.sun.com if you need additional information or have any questions.
 */
