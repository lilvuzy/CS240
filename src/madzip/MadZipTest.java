package madzip;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the CS240 Huffman Coding Project.
 *
 * @author Nathan Sprague
 * @version 3/2019
 *
 */
class MadZipTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private PrintStream originalOut;
  private PrintStream originalErr;

  // Keep a list of all the files that are created so that they can be deleted.
  private String[] createdFiles = {"empty.dat", "empty.mz", "one_byte.dat", "one_byte.mz",
          "mary.txt", "mary.mz", "fibonacci.dat", "fibonacci.mz", "bytes.dat", "bytes.mz",
          "bytes_restored.dat", "flubber.mz"};


  /**
   * Create several files that can be used to test encoding and decoding.
   *
   * Reset System.out and System.err so they can be checked in tests.
   *
   * @throws IOException
   */
  @BeforeEach
  public void setUp() throws IOException {
    originalOut = System.out;
    originalErr = System.err;
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));

    // Create an empty file.
    PrintWriter pw = new PrintWriter(new File("empty.dat"));
    pw.close();

    // Create a file containing a single byte.
    FileOutputStream fo = new FileOutputStream(new File("one_byte.dat"));
    fo.write(42);
    fo.close();

    // Create a short text file.
    pw = new PrintWriter(new File("mary.txt"));
    pw.print("Mary had a little lamb.  It's fleece was white as snow.\n");
    pw.close();

    // Create a short binary file containing truncated elements from the
    // Fibonacci sequence.
    fo = new FileOutputStream(new File("fibonacci.dat"));

    int prev1 = 1;
    int prev2 = 1;
    for (int i = 0; i < 1000; i++) {
      int next = prev1 + prev2;
      fo.write(next);
      prev1 = prev2;
      prev2 = next;
    }
    fo.close();

    // Create a file containing known counts of many different bytes.
    fo = new FileOutputStream(new File("bytes.dat"));

    for (int i = 0; i < 100; i++) { // Write the bytes 0-99
      for (int j = 0; j < i + 10; j++) { // Write each byte b+10 times.
        fo.write(i);
      }
    }
    fo.close();
  }

  /**
   * Restore System.out and System.err, also delete all files that were created
   * in setUp.
   */
  @AfterEach
  public void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);

    for (String fileName : createdFiles) {
      File file = new File(fileName);
      file.delete();
    }
  }

  // -----------------------------------------------
  // TESTS FOR MadZip COMMAND LINE ARGUMENT ERROR HANDLING
  //
  // NOTE: These tests only confirm that *some* error output is produced. It is
  // your responsibility to make sure that the output is appropriate.
  // -----------------------------------------------

  /**
   * Helper method to confirm that SOME output is generated.
   */
  private void assertProducesErrorMessage() {
    String resultOut = outContent.toString();
    String resultErr = errContent.toString();
    assertTrue(resultOut.length() != 0 || resultErr.length() != 0);
  }

  @Test
  public void testZipHandlesZeroArguments() {
    MadZip.main(new String[] {});
    assertProducesErrorMessage();
  }

  @Test
  public void testZipHandlesOneMissingArgument() {
    MadZip.main(new String[] {"file.txt"});
    assertProducesErrorMessage();
  }

  @Test
  public void testZipHandlesUnreadableFile() {
    MadZip.main(new String[] {"BLAfjdlSFl.txt", "bytes.mz"});
    assertProducesErrorMessage();
  }

  @Test
  public void testZipHandlesUnwriteableFile() {
    MadZip.main(new String[] {"mary.txt", "/bytes.mz"});
    assertProducesErrorMessage();
  }

  // -----------------------------------------------
  // TESTS FOR MadUnzip COMMAND LINE ARGUMENT ERROR HANDLING
  // -----------------------------------------------

  @Test
  public void testUnzipHandlesZeroArguments() {
    MadUnzip.main(new String[] {});
    assertProducesErrorMessage();
  }

  @Test
  public void testUnzipHandlesOneMissingArgument() {
    MadUnzip.main(new String[] {"file.txt"});
    assertProducesErrorMessage();
  }

  @Test
  public void testUnzipHandlesUnreadableFile() {
    MadUnzip.main(new String[] {"BLAfjdlSFl.txt", "out.mz"});
    assertProducesErrorMessage();
  }

  @Test
  public void testUnzipHandlesWrongFileFormat() {
    MadUnzip.main(new String[] {"mary.txt", "blah.txt"});
    assertProducesErrorMessage();
  }

  // -----------------------------------------------
  // TESTS FOR FILE CREATION by MadZip
  // -----------------------------------------------

  @Test
  public void testMadZipCreatesFile() throws IOException {
    MadZip.main(new String[] {"mary.txt", "flubber.mz"});

    File file = new File("flubber.mz");
    assertTrue(file.exists());
  }

  // -----------------------------------------------
  // TESTS FOR CORRECT FREQUENCIES in files created by MadZip
  // -----------------------------------------------

  private HuffmanSave loadSaved(String name) throws IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream(new File(name));
    ObjectInputStream in;
    in = new ObjectInputStream(fileIn);
    HuffmanSave result = (HuffmanSave) in.readObject();
    in.close();
    fileIn.close();
    return result;
  }

  @Test
  public void testOneByteFileCorrectFrequencies() throws ClassNotFoundException, IOException {
    MadZip.main(new String[] {"one_byte.dat", "one_byte.mz"});

    HuffmanSave result = loadSaved("one_byte.mz");
    assertEquals(1, result.getFrequencies().size());
    assertEquals(1, (int) result.getFrequencies().get((byte) 42));
  }

  @Test
  public void testMultiByteFileCorrectFrequencies() throws ClassNotFoundException, IOException {
    MadZip.main(new String[] {"bytes.dat", "bytes.mz"});

    HuffmanSave result = loadSaved("bytes.mz");
    assertEquals(100, result.getFrequencies().size());
    for (int i = 0; i < 100; i++) {
      assertEquals(i + 10, (int) result.getFrequencies().get((byte) i));
    }
  }

  // -----------------------------------------------
  // TESTS FOR CORRECT ENCODING LENGTH
  // -----------------------------------------------

  @Test
  public void testEmptyFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    MadZip.main(new String[] {"empty.dat", "empty.mz"});

    HuffmanSave result = loadSaved("empty.mz");
    assertEquals(0, result.getEncoding().length());
  }

  @Test
  public void testOneByteFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    MadZip.main(new String[] {"one_byte.dat", "one_byte.mz"});

    HuffmanSave result = loadSaved("one_byte.mz");
    assertEquals(1, result.getEncoding().length());
  }

  @Test
  public void testTextFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    MadZip.main(new String[] {"mary.txt", "mary.mz"});

    HuffmanSave result = loadSaved("mary.mz");
    assertEquals(227, result.getEncoding().length());
  }

  @Test
  public void testFibonacciFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    MadZip.main(new String[] {"fibonacci.dat", "fibonacci.mz"});

    HuffmanSave result = loadSaved("fibonacci.mz");
    assertEquals(7147, result.getEncoding().length());
  }

  @Test
  public void testByteFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    MadZip.main(new String[] {"bytes.dat", "bytes.mz"});

    HuffmanSave result = loadSaved("bytes.mz");
    assertEquals(38557, result.getEncoding().length());
  }

  // -----------------------------------------------
  // TESTS FOR CORRECT RECONSTRUCTION
  // -----------------------------------------------

  private void checkReconstruction(String name) throws IOException {
    MadZip.main(new String[] {name, "bytes.mz"});
    MadUnzip.main(new String[] {"bytes.mz", "bytes_restored.dat"});
    Path path1 = FileSystems.getDefault().getPath(name);
    Path path2 = FileSystems.getDefault().getPath("bytes_restored.dat");
    byte[] bytes1 = Files.readAllBytes(path1);
    byte[] bytes2 = Files.readAllBytes(path2);
    assertArrayEquals(bytes1, bytes2);
  }

  @Test
  public void testEmptyFileCorrectlyRestored() throws IOException {
    checkReconstruction("empty.dat");
  }

  @Test
  public void testOneByteFileCorrectlyRestored() throws IOException {
    checkReconstruction("one_byte.dat");
  }

  @Test
  public void testTextFileCorrectlyRestored() throws IOException {
    checkReconstruction("mary.txt");
  }

  @Test
  public void testFibonacciFileCorrectlyRestored() throws IOException {
    checkReconstruction("fibonacci.dat");
  }

  @Test
  public void testByteFileCorrectlyRestored() throws IOException {
    checkReconstruction("bytes.dat");
  }



}