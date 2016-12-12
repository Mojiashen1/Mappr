/**
 * Clarissa Verish
 * WeightedAdjMatGraph.java
 * 
 * Purpose: to be able to build an adjacency matrix that stores the data for 
 * weighted edges. The graph will be undirected when constructed.
 */


public class WeightedAdjMatGraph<T> implements WeightedGraph<T>, Iterable<T>{
  public static final int NOT_FOUND = -1;
  private static final int DEFAULT_CAPACITY = 100; // fairly large to account for building
  
  private int[][] edges; // storage for the integer weights of the edges
  private int n; // num vertices
  private T[] vertices; // vertices in graph
  
  // constructor
  public WeightedAdjMatGraph(){
    this.edges = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    this.n = 0;
    this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
  }
  
  /***********************************
    ******* IMPLEMENTING GRAPH *******
    **********************************/

  public boolean isEmpty(){
    return n = 0;
  }
  
  /**
   * Returns number of vertices in graph.
   */
  public int n(){
    return n;
  };
  
  /**
   * Returns number of edges in the graph.
   * Counts number of arcs in adjacency matrix, then divides
   * by two since graph is undirected.
   */
  public int m(){
    int m = 0;
    // looping through adj matrix to check if edge.
    // increments counter if so.
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        if (vertexIndex(edges[i][j]) > 0){
          m++;
        }
      }
    }
    return m / 2;
  }
  
  public boolean isEdge (T vertex1, T vertex2);
  
  public int edgeWeight(T vertex1, T vertex2);
  
  public void addVertex (T vertex);
  
  public void removeVertex (T vertex);
  
  public void addEdge (T vertex1, T vertex2);
  
  public void removeEdge (T vertex1, T vertex2);
  
  public LinkedList<T> getNeighbors(T vertex);
  
  /** Returns a string representation of the adjacency matrix. */
  public String toString();
  
  
  /***********************************
    ******** HELPER FUNCTIONS ********
    **********************************/
  
  /**
   * finds the index of the vertex. If it does not exist,
   * it returns -1 (NOT_FOUND). If it does, it returns the 
   * index of the vertex
   */
  private int vertexIndex(T obj){
    for (int i = 0; i < n; i++){
      if (obj.compareTo(vertices[i]) == 0){
        return i;
      }
    }
    return NOT_FOUND;
  }
    
  /**
   * expands the capacity of the graph.
   */
  private void expandCapacity() {
    T[] tempVertices = (T[])(new Object[vertices.length * 2]);
    int[][] tempEdges = new int[vertices.length * 2][vertices.length * 2];
    
    for (int i = 0; i < n; i++) {
      tempVertices[i] = vertices[i];
      for (int j = 0; j < n; j++) {
        tempEdges[i][j] = edges[i][j];
      }
    }
    
    vertices = tempVertices;
    edges = tempEdges;
  }  
}