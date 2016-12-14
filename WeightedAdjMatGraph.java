/**
 * Clarissa Verish
 * 12/11/2016
 * WeightedAdjMatGraph.java
 * 
 * Purpose: to be able to build an adjacency matrix that stores the data for 
 * weighted edges. The graph will be undirected when constructed.
 */

import java.util.LinkedList;
import javafoundations.ArrayIterator;
import java.lang.Iterable;
import java.util.Iterator;
import javafoundations.LinkedStack;

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
    return n == 0;
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
        if (edges[i][j] > 0){
          m++;
        }
      }
    }
    return m / 2;
  }
  
  /**
   * Returns true if an edge exists between two vertices
   */
  public boolean isEdge (T vertex1, T vertex2){
    int i1 = vertexIndex(vertex1);
    int i2 = vertexIndex(vertex2);
    if (i1 != NOT_FOUND && i2 != NOT_FOUND && edges[i1][i2] > 0){
      return true;
    }
    return false;
  }
  
  /** 
   * Returns weight of edge if it exists. If it does not,
   * it returns NOT_FOUND (-1)
   */
  public int edgeWeight(T vertex1, T vertex2){
    if (isEdge(vertex1, vertex2)){
      return edges[vertexIndex(vertex1)][vertexIndex(vertex2)];
    }
    return NOT_FOUND;
  }
  
  /**
   * Adds vertex if it does not already exist, and autopopulates
   * all newly created edges between old vertices and new vertex with -1.
   */
  public void addVertex (T vertex){
    if (vertexIndex(vertex) > 0) return; // vertex already in graph, return.
    if (n == vertices.length){ // need to expand capacity of arrays
      expandCapacity();
    }
    
    vertices[n] = vertex;
    for (int i = 0; i < n; i++){ // populating new edges with -1
      edges[n][i] = -1;
      edges[i][n] = -1;
    }
    n++;
  }
  
  /**
   * Removes the given vertex if possible.
   */
  public void removeVertex (T vertex){
    int index = vertexIndex(vertex);
    
    if (index < 0) return; // vertex does not exist
    
    n--;
    // IF THIS DOESN'T WORK make separate loop for column moving
    for (int i = index; i < n; i++){
      vertices[i] = vertices[i + 1];
      for (int j = 0; j < n; j++){
        edges[j][i] = edges[j][i + 1]; // moving row up
        edges[i] = edges[i + 1]; // moving column over
      }
    }
  }
  
  public void addEdge (T vertex1, T vertex2, int weight){
    int i1 = vertexIndex(vertex1);
    int i2 = vertexIndex(vertex2);
    
    if (i1 == NOT_FOUND || i2 == NOT_FOUND) return;
    
    edges[i1][i2] = weight;
    edges[i2][i1] = weight;
  }
  
  public void removeEdge (T vertex1, T vertex2){
    int i1 = vertexIndex(vertex1);
    int i2 = vertexIndex(vertex2);
    
    if (i1 == NOT_FOUND || i2 == NOT_FOUND) return;
    
    edges[i1][i2] = -1;
    edges[i2][i1] = -1;
  }
  
  public LinkedList<T> getNeighbors(T vertex){
    LinkedList<T> neighbors = new LinkedList<T>();
    int index = vertexIndex(vertex);
    
    for (int i = 0; i < n; i++){
      if (edges[n][i] > 0){
        neighbors.add(vertices[i]);
      }
    }
    return neighbors;
  }
  
  /** Returns a string representation of the adjacency matrix. */
  public String toString(){
    String s = "";
    s += "\t";
    
    for (int i = 0; i < n; i++){
      // adding vertices for columns
      s += vertices[i] + "\t";
    }
    
    s += "\n";
    
    for (int i = 0; i < n; i++){
      s += vertices[i] + "\t"; // vertex for row
      for (int j = 0; j < n; j++){
        s += edges[j][i] + "\t"; // adding edges across row
      }
      s += "\n";
    }
    
    return s;
  }
  
  /***********************************
    ***** IMPLEMENTING ITERABLE ******
    **********************************/
  
  public Iterator<T> iterator(){
    ArrayIterator<T> iter = new ArrayIterator<T>();
    for (int i = 0; i < n; i++){
      iter.add(vertices[i]);
    }
    return (Iterator<T>)(iter);
  }
  

    
  /***********************************
    ********** WEIGHTED PATH *********
    **********************************/
  
  private class WeightedPath {
    private int pathWeight;
    private LinkedStack<T> path;
    
    public WeightedPath(T start){
      path = new LinkedStack<T>();
      path.push(start);
      pathWeight = 0;
    }
    
    /**
     * adds a room to the path
     */
    public void expandPath(T obj){
      
    }
    
    /**
     * gets the current path weight
     */
    public int getPathWeight(){
      return pathWeight;
    }
    
    /** 
     * sets new path weight
     */
    public void setPathWeight(int n){
      pathWeight = n;
    }
    
    /**
     * returns the last-visited room for this path
     */
    public T getLastVisited(){
      return path.peek();
    }
  }
  
  /***********************************
    ********* PATH TRAVERSAL *********
    **********************************/
  
  
  
  
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
      if (obj.equals(vertices[i])){
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