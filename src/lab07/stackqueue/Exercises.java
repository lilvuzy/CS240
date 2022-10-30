package lab07.stackqueue;

import java.awt.*;
import java.util.Scanner;

import static java.lang.Character.isLetter;

public class Exercises {

  public static void oddsFirst(Queue<Integer> queue) {
    Queue<Integer> odds = new Queue<>();
    Queue<Integer> evens = new Queue<>();

    // Split queue to odds and evens
    for (int i = queue.size(); i > 0; i--) {
      if (queue.peek() % 2 == 0) {
        evens.enqueue(queue.dequeue());
      } else {
        odds.enqueue(queue.dequeue());
      }
    }
    // Add all odds to queue
    int oddsSize = odds.size();
    for (int i = odds.size(); i > 0; i--) {
      queue.enqueue(odds.dequeue());
    }
    // Add all evens to queue
    for (int i = evens.size(); i > 0; i--) {
      queue.enqueue(evens.dequeue());
    }
  }

  public static Point wander(String commands) {
    Scanner scanner = new Scanner(commands);
    Point currentLocation = new Point(0, 0);
    Stack<Point> savedLocations = new Stack<>();

    while (scanner.hasNext()) {
      switch (scanner.next()) {
        case "up":
          currentLocation.y += scanner.nextInt();
          break;
        case "down":
          currentLocation.y -= scanner.nextInt();
          break;
        case "left":
          currentLocation.x -= scanner.nextInt();
          break;
        case "right":
          currentLocation.x += scanner.nextInt();
          break;
        case "save":
          savedLocations.push(new Point(currentLocation.x, currentLocation.y));
          break;
        case "restore":
          currentLocation = new Point(savedLocations.pop());
          break;
      }
    }
    return currentLocation;
  }

  public static void printPaths(String path) {
    for (int j = 0; j < path.length(); j++) {

      Queue<Character> letterQueue = new Queue<>();

      for (int i = 0; i < j; i++) {

        if (path.charAt(i) == '[') {
          letterQueue.enqueue(path.charAt(i-1));
        } else if (path.charAt(i) == ']') {
          letterQueue.dequeue();
        }

      }

      if (isLetter(path.charAt(j))) {

        while (!letterQueue.isEmpty()) {
          System.out.print(letterQueue.dequeue());
        }

        System.out.println(path.charAt(j));
      }
    }
  }
}
