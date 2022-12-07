package madzip;

/**
 *  HuffmanNode class for madzip project. Used to create a Huffman tree.
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

  private final int frequency;
  private  byte data;
  private HuffmanNode left;
  private HuffmanNode right;

  /**
   * Constructor for HuffmanNode with left and right children.
   *
   * @param frequency frequency of byte in file.
   * @param data byte to store.
   */
  public HuffmanNode(int frequency, byte data, HuffmanNode left, HuffmanNode right) {
    this.frequency = frequency;
    this.data = data;
    this.right = right;
    this.left = left;
  }

  /**
   * Constructor for an inner huffman node with no data.
   *
   * @param frequency frequency of byte in file.
   */
  public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
    this.frequency = frequency;
    this.left = left;
    this.right = right;
  }

  /**
   * Constructor for an inner huffman node with no data.
   *
   * @param frequency frequency of byte in file.
   */
  public HuffmanNode(int frequency, byte data) {
    this.frequency = frequency;
    this.data = data;
    this.left = null;
    this.right = null;
  }

  /**
   * Set left child.
   *
   * @param left node to set as left child
   **/
  public void setLeft(HuffmanNode left) {
    this.left = left;
  }

  /**
   * Set right child.
   *
   * @param right node to set as right child
   */
  public void setRight(HuffmanNode right) {
    this.right = right;
  }

  /**
   * Get left child.
   *
   * @return left child
   */
  public HuffmanNode getLeft() {
    return left;
  }

  /**
   * Get right child.
   *
   * @return right child
   */
  public HuffmanNode getRight() {
    return right;
  }

  /**
   * Check if node is a leaf.
   *
   * @return true if no left or right children.
   */
  public boolean isLeaf() {
    return left == null && right == null;
  }

  /**
   * Get the frequency of the byte.
   *
   * @return frequency of byte.
   */
  public int getFrequency() {
    return frequency;
  }

  /**
   * Get the byte.
   *
   * @return byte.
   */
  public byte getData() {
    return data;
  }

  @Override
  public int compareTo(HuffmanNode o) {

    // If frequencies are equal, compare bytes, if not compare frequencies
    if (this.frequency == o.frequency) {
      return o.data - this.data;
    }
    return this.frequency - o.frequency;
  }

}
