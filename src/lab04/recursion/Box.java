package lab04.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Box class for lab 04.
 *
 * @author Yavuz Yavuzer
 */
public class Box {

  List boxList;
  String boxColor;
  /**
   * Constructor for box class.
   *
   * @param color name of color
   * @param boxes list of nested boxes
   */
  public Box(String color, List boxes) {
    boxList = new ArrayList<List<Box>>();
    boxList.add(boxes);
    boxColor = color;
  }

  /**
   * Trace method for box class.
   *
   * @param color color to search
   * @param boxes path of colors
   * @return true if color is found in boxes
   */
  public boolean trace(String color, List boxes) {

    if(boxColor.equals(color)) {
      boxes.add(boxColor);
      return true;
    }

    return false;
  }
}
