package madzip;

import java.io.*;
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
   * Write codes to bitsequence object.
   *
   * @param huffmanCodes huffman code map
   * @param buffer array of bytes
   * @return bitsequence object
   */
  public static BitSequence toBitSequence(HashMap<Byte, String> huffmanCodes, byte[] buffer) {

    BitSequence bitSequence = new BitSequence();

    for (byte b : buffer) {
      System.out.println("Huffman code: " + huffmanCodes.get(b));
      bitSequence.appendBits(huffmanCodes.get(b));
    }
    return bitSequence;
  }

  /**
   * Generate code map from min heap.
   *
   * @param minHeap heap to write from
   * @return code map
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
   * Write data to output file.
   *
   * @param fileOut name of output file
   * @param huffmanSave huffmanSave object to write to file
   */
  public static void writeFile(String fileOut, HuffmanSave huffmanSave) {
    // Write huffmansave to file
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(huffmanSave);
      objectOutputStream.close();
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Build frequency map from array of bytes.
   *
   * @param buffer array of bytes
   * @return frequency map
   */
  public static HashMap<Byte, Integer> toFrequencyMap(byte[] buffer) {
    HashMap<Byte, Integer> frequencyMap = new HashMap<>();

    for (byte b : buffer) {
      if (frequencyMap.containsKey(b)) {
        frequencyMap.put(b, frequencyMap.get(b) + 1);
      } else {
        frequencyMap.put(b, 1);
      }
    }

    return frequencyMap;
  }

  /**
   * Main method.
   *
   * @param args string arguments
   */
  public static void main(String[] args) {



    try {
      // Create a file input stream to read the file byte by byte
      final String fileInput = args[0];
      final String fileOutput = args[1];
      FileInputStream fileInputStream = new FileInputStream(fileInput);
      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
      byte[] buffer = new byte[bufferedInputStream.available()];
      bufferedInputStream.read(buffer);

      // If empty, write empty file and exit
      if (buffer.length == 0) {
        writeFile(fileOutput, new HuffmanSave(new BitSequence(), new HashMap<>()));
        return;
      }

      // Store byte frequency in a hashmap
      HashMap<Byte, Integer> byteFrequency = toFrequencyMap(buffer);

      // Create a min heap to store the bytes
      MinHeap minHeap = HuffmanHelpers.buildMinHeap(byteFrequency);

      // Create a huffman tree from the min heap
      HuffmanNode headNode = HuffmanHelpers.buildTree(minHeap);

      // Build huffman code map
      HashMap<Byte, String> huffmanCodes = HuffmanHelpers.buildHuffmanCodes(headNode,
              new HashMap<Byte, String>(), "");

      // Write corresponding huffman codes to BitSequence object
      BitSequence bitSequence = toBitSequence(huffmanCodes, buffer);

      // Create HuffmanSave with BitSequence and Frequency Map
      HuffmanSave huffmanSave = new HuffmanSave(bitSequence, byteFrequency);

      // Write HuffmanSave to file
      writeFile(fileOutput, huffmanSave);



    } catch (IOException e) {
      e.printStackTrace();
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Please enter a file to unzip and an output file.");
      e.printStackTrace();
    }


  }
}
