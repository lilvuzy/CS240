package lab12.graph;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * A directed graph whose vertices are labeled with integer indices starting at 0.
 */
public class Graph {
  private boolean[][] edges;

  /**
   * Creates a graph with the given number of vertices. The number cannot be changed later.
   * @param vertexCount number of vertices in graph
   */
  public Graph(int vertexCount) {
    edges = new boolean[vertexCount][vertexCount];
  }

  /**
   * Adds an edge from one vertex to another.
   * @param from the starting vertex
   * @param to the ending vertex
   */
  public void addEdge(int from, int to) {
    edges[from][to] = true;
  }

  /**
   * Removes an edge from one vertex to another.
   * @param from the starting vertex
   * @param to the ending vertex
   */
  public void removeEdge(int from, int to) {
    edges[from][to] = false;
  }

  /**
   * Checks if there's an edge from one vertex to another.
   * @param from the starting vertex
   * @param to the ending vertex
   * @return true if and only if the edge exists
   */
  public boolean hasEdge(int from, int to) {
    return edges[from][to];
  }

  /**
   * Retrieves a set of a vertex's neighbors.
   * @param from the vertex whose neighbors are retrieved
   */
  public Set<Integer> neighbors(int from) {
    Set<Integer> neighbors = new HashSet<>();
    for (int to = 0; to < edges[from].length; ++to) {
      if (hasEdge(from, to)) {
        neighbors.add(to);
      }
    }
    return Collections.unmodifiableSet(neighbors);
  }

  /**
   * Retrieves a set of all vertex labels.
   * @return the set of all vertex labels
   */
  public Set<Integer> vertices() {
    Set<Integer> vertices = new HashSet<>();
    for (int i = 0; i < edges.length; ++i) {
      vertices.add(i);
    }
    return Collections.unmodifiableSet(vertices);
  }

  /**
   * Gets a string representation of the graph in the Dot language of Graphviz.
   * @return the Dot representation
   */
  public String toDot() {
    // Visualize graph at http://viz-js.com.
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bos);

    out.println("digraph {");
    for (Integer vertex : vertices()) {
      out.printf("  %d;%n", vertex);
    }
    for (Integer from : vertices()) {
      for (Integer to : neighbors(from)) {
        out.printf("  %d -> %d;%n", from, to);
      }
    }
    out.println("}");

    out.close();
    return bos.toString();
  }

  public static void main(String[] args) {
    Graph graph = new Graph(5);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);

    System.out.println(graph.toDot());
    List<Integer> list = PathAlgorithms.shortestPath(graph, 0, 2);


  }
}