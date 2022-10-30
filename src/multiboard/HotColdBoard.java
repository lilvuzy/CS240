package multiboard;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

/**
 * HotColdBoard class for multiboard PA.
 *
 * @param <T> generic of cell type
 * @author Yavuz Yavuzer
 */
public class HotColdBoard<T extends Cell> extends ArrayBoard<T> {

  private Random random;

  /**
   * Constructor for HotColdBoard.
   *
   * @param width board width
   * @param height board height
   * @param seed random number generator seed
   */
  public HotColdBoard(int width, int height, long seed) {
    super(width, height);
    random = new Random(seed);
    for (int i = 0; i < width(); i++) {
      for (int j = 0; j < height(); j++) {
        board[i][j] = new HotColdCell(0);
      }
    }
    reset();
  }

  /**
   * Reset game board.
   */
  public void reset() {

    int tcol = random.nextInt(board.length);
    int trow = random.nextInt(board[0].length);
    double temp;

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        temp = Point2D.distance(i, j, tcol, trow) / distanceToFarCorner(tcol, trow);
        set(i, j, new HotColdCell(temp));
      }
    }
  }

  /**
   * Calculate distance to far corner.
   *
   * @param col column
   * @param row row
   * @return distance as double.
   */
  public double distanceToFarCorner(int col, int row) {
    double topLeft = Point2D.distance(col, row, 0, 0);
    double topRight = Point2D.distance(col, row, board.length - 1, 0);
    double botLeft = Point2D.distance(col, row, 0, board[0].length - 1);
    double botRight = Point2D.distance(col, row, board.length - 1, board[0].length - 1);

    double max = topLeft;

    if (topRight > max) {
      max = topRight;
    }
    if (botLeft > max) {
      max = botLeft;
    }
    if (botRight > max) {
      max = botRight;
    }

    return max;
  }

  /**
   * Main method for multiboard.
   *
   * @param args string args for main method.
   */
  public static void main(String[] args) throws IOException {

    // BoardFrame game = new BoardFrame(new LightsOutBoard(78235));


    URL url = new URL("https://ayuda.twodee.org:8443/zukei-square/1245");

    Scanner scan = new Scanner(url.openStream());


    int width = scan.nextInt();
    int height = scan.nextInt();
    int x;
    int y;

    SparseBoard sparseBoard = new SparseBoard<>(width, height);

    System.out.println("Width: " + width + " Height: " + height);

    while (scan.hasNext()) {
      x = scan.nextInt();
      y = scan.nextInt();

      sparseBoard.set(x, y, new OnOffCell());
      System.out.println(x + " " + y);
    }
    scan.close();


  }
}
