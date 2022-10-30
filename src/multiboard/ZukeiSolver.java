package multiboard;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * ZukeiSolver class for PA1.
 *
 * @author Yavuz Yavuzer
 */
public class ZukeiSolver {

  /**
   * Gets board configuration from given ID.
   *
   * @param id id for board to get
   * @return sparseboard created from ID
   */
  public static SparseBoard pullBoard(int id) throws IOException {
    URL url = new URL("https://ayuda.twodee.org:8443/zukei-square/" + id);

    Scanner scan = new Scanner(url.openStream());

    int width = scan.nextInt();
    int height = scan.nextInt();
    int x;
    int y;

    SparseBoard sparseBoard = new SparseBoard<>(width, height);

    while (scan.hasNext()) {
      x = scan.nextInt();
      y = scan.nextInt();
      sparseBoard.set(x, y, new OnOffCell());
    }
    scan.close();
    return sparseBoard;
  }

  /**
   * Solve board.
   *
   * @param board board to solve
   * @return number of squares found
   */
  public static int solveBoard(SparseBoard board) {

    return -1;
  }

  /**
   * Main method for zukeisolver.
   *
   * @param args string args for zukeisolver
   */
  public static void main(String[] args) {

  }
}
