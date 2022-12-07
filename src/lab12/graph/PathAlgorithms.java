package lab12.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class PathAlgorithms {

  /**
   * Finds the shortest path from one vertex to another.
   *
   * @param graph graph to check
   * @param from starting vertex
   * @param to end vertex
   * @return the shortest path from the starting vertex to the ending vertex, or null if no path exists
   */
  public static List<Integer> shortestPath(Graph graph, int from, int to) {
    return null;
  }

  /**
   * Checks if the graph is cyclic.
   *
   * @param graph the graph to check
   * @return true if and only if the graph is cyclic
   */
  public static boolean hasCycle(Graph graph) {
    return false;
  }

  /**
   * Checks if there is a path that leads back to
   *
   * @param graph the graph to check
   * @param current the current vertex
   * @param visited vertices that have already been visited on path
   * @param visitedOnPath
   * @return true if and only if there is a back edge
   */
  private static boolean hasBackEdge(Graph graph, int current, int[] visited, int[] visitedOnPath) {
    return false;
  }
}
