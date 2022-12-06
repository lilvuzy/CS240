package madzip;

import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * MadZip class for madzip project. Uses huffman compression to compress an input file.
 */
public class MadZip {

  /**
   * Constructor for MadZip.
   *
   **/
  public MadZip() {
  }

  /**
   * Generate dictionary of byte -> huffman code.
   * @param minHeap
   * @return
   */
  public static HashMap<Byte, Integer> getDictionary(MinHeap minHeap) {
    HashMap<Byte, Integer> dictionary = new HashMap<>();
    while (!minHeap.isEmpty()) {
      HuffmanNode node = (HuffmanNode) minHeap.remove();
      dictionary.put(node.getData(), node.getFrequency());
    }
    return dictionary;
  }


  /**
   * Main method.
   *
   * @param args string arguments
   */
  public static void main(String[] args) {

    String fileInput = args[0];
    String fileOutput = args[1];

    try {

      // Create a file input stream to read the file byte by byte
      FileInputStream fileInputStream = new FileInputStream(fileInput);
      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

      byte[] buffer = new byte[bufferedInputStream.available()];
      bufferedInputStream.read(buffer);


      // Read the file and print bytes (for testing)
      System.out.println("Byte count: " + buffer.length);
      for (byte b : buffer) {
        System.out.println(b);
      }

      // Store byte frequency in a hashmap
      HashMap<Byte, Integer> byteFrequency = new HashMap<>();
      for (byte b : buffer) {
        if (byteFrequency.containsKey(b)) {
          byteFrequency.put(b, byteFrequency.get(b) + 1);
        }
        else {
          byteFrequency.put(b, 1);
        }
        System.out.println("Byte: " + b + " Frequency: " + byteFrequency.get(b));
      }

      // Start of shared code for both madzip and madunzip

      // Create a min heap to store the bytes
      MinHeap minHeap = HuffmanHelpers.buildMinHeap(byteFrequency);

      // Create a huffman tree from the min heap
      HuffmanNode headNode = HuffmanHelpers.buildTree(minHeap);

      // Build huffman code map
      HashMap<Byte, String> huffmanCodes = HuffmanHelpers.buildHuffmanCodes(headNode, new HashMap<Byte, String>(), "");

      // Write corresponding huffman codes to BitSequence object
      BitSequence bitSequence = new BitSequence();
      for (byte b : buffer) {
        System.out.println("Huffman code: " + huffmanCodes.get(b));
        bitSequence.appendBits(huffmanCodes.get(b));
      }

      // Create HuffmanSave with BitSequence and Frequency Map
      HuffmanSave huffmanSave = new HuffmanSave(bitSequence, byteFrequency);

      // Write BitSequence to file
      try {
        FileOutputStream fileOutputStream = new FileOutputStream(fileOutput);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(huffmanSave);
        objectOutputStream.close();
        fileOutputStream.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }






  }
}
