import java.util.LinkedList;


public interface WeightedGraph<T> {
  /** Returns true if this graph is empty, false otherwise. */
  public boolean isEmpty();
  
  /** Returns the number of vertices in this graph. */
  public int n();
  
  /** Returns the number of edges in this graph. */
  public int m();
  
  /** Returns true iff an edge exists between two given vertices
    * which means that two corresponding arcs exist in the graph */
  public boolean isEdge (T vertex1, T vertex2);
  
  /** Returns the weight of the edge between two vertices, or 
    * -1 if not found. */
  public int edgeWeight(T vertex1, T vertex2);
  /** Adds a vertex to this graph, associating object with vertex.
    * If the vertex already exists, nothing is inserted. */
  public void addVertex (T vertex);
  
  /** Removes a single vertex with the given value from this graph.
    * If the vertex does not exist, it does not change the graph. */
  public void removeVertex (T vertex);
  
  /** Inserts an edge between two vertices of this graph,
    * if the vertices exist. Else does not change the graph. */
  public void addEdge (T vertex1, T vertex2, int weight);
  
  /** Removes an edge between two vertices of this graph,
    if the vertices exist, else does not change the graph. */
  public void removeEdge (T vertex1, T vertex2);
  
  /** Retrieve from a graph the vertices x following vertex v (v->x)
    and returns them onto a linked list */
  public LinkedList<T> getNeighbors(T vertex);
  
  /** Returns a string representation of the adjacency matrix. */
  public String toString();
  
  
}
