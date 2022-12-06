package madzip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * Helper methods for madzip, including shared methods for both madzip and madunzip.
 */
public class HuffmanHelpers {

  /**
   * Build huffman tree from a hashmap of bytes and their frequencies.
   *
   * @return the head node.
   */
  public static HuffmanNode buildTree(MinHeap minHeap) {
    while (minHeap.size() > 1) {
      HuffmanNode left = (HuffmanNode) minHeap.remove();
      HuffmanNode right = (HuffmanNode) minHeap.remove();
      HuffmanNode parent = new HuffmanNode(left.getFrequency() + right.getFrequency(), left, right);
      minHeap.add(parent);
    }
    return (HuffmanNode) minHeap.remove();
  }

  /**
   * Build code map from huffman tree.
   * @param head head node
   * @return hashmap code map
   */
  public static HashMap<Byte, String> buildHuffmanCodes(HuffmanNode head, HashMap<Byte, String> huffmanCodeMap, String code) {
    if (head.isLeaf()) {
      huffmanCodeMap.put(head.getData(), code);
    } else {
      buildHuffmanCodes(head.getLeft(), huffmanCodeMap, code + "0");
      buildHuffmanCodes(head.getRight(), huffmanCodeMap, code + "1");
    }
    return huffmanCodeMap;
  }

  /**
   * Build min heap from a hashmap of bytes and their frequencies.
   *
   * @param byteFrequency bytes and their corresponding frequencies
   * @return min heap
   */
  public static MinHeap buildMinHeap(HashMap<Byte, Integer> byteFrequency) {
    MinHeap minHeap = new MinHeap(byteFrequency.size());
    for (byte b : byteFrequency.keySet()) {
      minHeap.add(new HuffmanNode(byteFrequency.get(b), b));
    }
    return minHeap;
}


  public static void huffmanDecompress(HuffmanNode head, BitSequence bitSequence, String fileOut) {

    // Create and write to new file
    try {
      // Instantiate output file and output stream to file
      FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);


      HuffmanNode current = head;
      for (int i = 0; i < bitSequence.length(); i++) {
        if (bitSequence.getBit(i) == 0) {
          current = current.getLeft();
        } else {
          current = current.getRight();
        }
        if (current.isLeaf()) {
          bufferedOutputStream.write(current.getData());
          current = head;
        }
      }

      bufferedOutputStream.close();
      fileOutputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }






  }
}
