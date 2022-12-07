package madzip;

import java.io.*;
import java.util.HashMap;

/**
 * MadUnzip class for madzip project. Uses huffman compression to decompress an input file.
 */
public class MadUnzip {

  /**
   * Create empty file with given output name.
   *
   * @param fileOut name for output file
   */
  public static void writeEmptyFile(String fileOut) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
      fileOutputStream.close();
    } catch (IOException e) {
      System.out.println("Error writing to file.");
    }
  }

  /**
   * Main method.
   *
   * @param args string arguments
   */
  public static void main(String[] args) {


    try {
      String fileInput = args[0];
      String fileOutput = args[1];


      FileInputStream fileInputStream = new FileInputStream(fileInput);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Object objectFromSave = objectInputStream.readObject();
      objectInputStream.close();
      fileInputStream.close();
      HuffmanSave huffmanSave = (HuffmanSave) objectFromSave;

      // If bitsequence is empty, write empty file and exit
      if (huffmanSave.getEncoding().length() == 0) {
        writeEmptyFile(fileOutput);
        return;
      }

      // Build minHeap to store bytes and their frequencies
      MinHeap minHeap = HuffmanHelpers.buildMinHeap(huffmanSave.getFrequencies());

      // Build huffman tree
      HuffmanNode headNode = HuffmanHelpers.buildTree(minHeap);

      // Decompress and write to file
      HuffmanHelpers.huffmanDecompress(headNode, huffmanSave.getEncoding(), fileOutput);

    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Error reading file");
      e.printStackTrace();
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Please enter a file to unzip and an output file.");
      e.printStackTrace();
    }




  }
}
