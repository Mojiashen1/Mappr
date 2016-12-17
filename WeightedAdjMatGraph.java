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
import javafoundations.ArrayQueue;

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
   * Returns true if a vertex exists
   */
  public boolean isVertex(T vertex) {
    return vertexIndex(vertex) != -1;
  }
  
  /** 
   * Returns weight of edge if it exists. If it does not,
   * it returns NOT_FOUND (-1)
   */
  public int getEdgeWeight(T vertex1, T vertex2){
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
      if (edges[index][i] > 0){
        neighbors.add(vertices[i]);
      }
    }
    return neighbors;
  }
  
  /** Returns the shortest path traveled between two vertices on 
    * a graph as a LinkedQueue */
  public ArrayQueue<T> getShortestPath(T vertex1, T vertex2){
    DijkstraShortestPath p = new DijkstraShortestPath(vertex1, vertex2);
    return p.getMinPath();
  }
  
  /** Returns the shortest distance between two vertices on a graph */
  public int getShortestDistance(T vertex1, T vertex2){
    DijkstraShortestPath p = new DijkstraShortestPath(vertex1, vertex2);
    return p.getMinDistance();
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
    ***** DIJKSTRA SHORTEST PATH *****
    **********************************/
  
  /**
   * computes the shortest path to traverse graph using Dijkstra's algorithm.
   * - all nodes start in unvisited linkedlist, and the distances from all nodes
   *   to the start node is stored in an array and start at infinity. 
   * - the start node is added to the visited, removed from unvisited, node's
   *   distance is set to zero, and node is set as current node
   * - find all neighbors of current node - if neighbor hasn't been visited, find
   *   (distance from current node to start) + (edge weight from current to neighbor)
   * - if distance for neighbor is less than already-stored distance, change to this
   *   distance
   * - after looking at all neighbors, set unvisited neighbor with shortest distance to 
   *   start equal to the current node, and repeat
   */
  
  private class DijkstraShortestPath {
    private T start, end; // starting and ending vertices
    private int totalDistance;
    private int[] distances; // distance from start 
    private T[] closestPredecessor; // closest predecessor for each node (null if start node)
    private LinkedList<T> visited, unvisited; // visited and unvisited vertices
    private ArrayQueue<T> path; // shortest path

    
    
    public DijkstraShortestPath(T start, T end){
      this.start = start;
      this.end = end;
      visited = new LinkedList<T>();
      unvisited = new LinkedList<T>();
      distances = new int[n];
      path = new ArrayQueue<T>();
      closestPredecessor = (T[])(new Object[n]);
      
      for (int i = 0; i < n; i++){ 
        distances[i] = Integer.MAX_VALUE; // setting distances to infinity
        unvisited.add(vertices[i]); // adding all vertices to unvisited
      }
      // setting start index as visited
      visited.add(start);
      unvisited.remove(start);
      distances[vertexIndex(start)] = 0;
      computeShortestPath();
      
    }
    
    /**
     * returns a queue of the minimum path produced
     */
    public ArrayQueue<T> getMinPath(){
      return path;
    }
    
    /**
     * returns the minimum distance from start to end
     */
    public int getMinDistance(){
      return totalDistance;
    }
    
    
    
    // computes the shortest path using Dijkstra's algorithm.
    // does not return anything.
    private void computeShortestPath(){
      T current = start;
      boolean atEnd = false;
      while (!unvisited.isEmpty()&& !atEnd){
        int currentIndex = vertexIndex(current);
        LinkedList<T> neighbors = getUnvisitedNeighbors(current); // getting unvisited neighbors
        
        if (neighbors.isEmpty()){
          
        }
        
        // looping through to find distances from start to neighbors
        for (T neighbor : neighbors){ 
          int neighborIndex = vertexIndex(neighbor); 
          int d = distances[currentIndex] + getEdgeWeight(current, neighbor);
          
          if (d < distances[neighborIndex]){ // if this distance is less than previous distance
            distances[neighborIndex] = d;
            closestPredecessor[neighborIndex] = current; // now closest predecessor is current
          }
        }
        
        if (current.equals(end)){
          atEnd = true;
        }
        else{
        // finding unvisited node that is closest to start node
          T min = getMinUnvisited();
          if (min != null){
            unvisited.remove(min); // remove minimum neighbor from unvisited
            visited.add(min); // add minimum neighbor to visited
            current = min;
          }
        }
      }
      computePath();
      totalDistance = distances[vertexIndex(end)];
    }
     
    // returns a queue of the minimum path by following closestPredecessor
    private void computePath(){
      LinkedStack<T> reversePath = new LinkedStack<T>();
      // adding end vertex to reversePath
      T current = end; // first node is the end one
      while (!current.equals(start)){
        reversePath.push(current); // adding current to path
        current = closestPredecessor[vertexIndex(current)]; // changing to closest predecessor to current
      }
      reversePath.push(current); // adding the start vertex
      
      while (!reversePath.isEmpty()){
        path.enqueue(reversePath.pop());
      }
    }
    
    // returns a list of unvisited neighbors of the input vertex
    private LinkedList<T> getUnvisitedNeighbors(T vertex){
      LinkedList<T> neighbors = getNeighbors(vertex);
      LinkedList<T> unvisitedNeighbors = new LinkedList<T>();
      
      for (T neighbor : neighbors){
        if (!visited.contains(neighbor)){
          unvisitedNeighbors.add(neighbor);
        }
      }
      return unvisitedNeighbors;
    }
    
    // finds the unvisited neighbor with the smallest distance from the
    // start vertex
    private T getMinUnvisited(){
      if (unvisited.isEmpty()) return null;
      T min = unvisited.get(0);
      for (int i = 1; i < unvisited.size(); i++){
        T temp = unvisited.get(i);
        if (distances[vertexIndex(temp)] < distances[vertexIndex(min)]){
          min = temp;
        }
      }
      return min;
    }

  }

 
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
  
  
  
  public static void main(String[] args){
    WeightedAdjMatGraph<String> w = new WeightedAdjMatGraph<String>();
    w.addVertex("A");
    w.addVertex("B");
    w.addVertex("C");
    w.addVertex("D");
    w.addVertex("E");
    w.addVertex("F");
    
    w.addEdge("A", "B", 2);
    w.addEdge("A", "F", 4);
    w.addEdge("B", "C", 1);
    w.addEdge("B", "F", 6);
    w.addEdge("C", "F", 5);
    w.addEdge("C", "D", 2);
    w.addEdge("C", "E", 5);
    w.addEdge("D", "E", 1);

    
    System.out.println(w);
    System.out.println("num vertices: " + w.n() + "; num edges: " + w.m());
    System.out.println("edge b/w A and C? " + w.isEdge("A", "C"));
    System.out.println("edge b/w E and C? " + w.isEdge("E", "C"));
    System.out.println("edge weight b/w E and C? " + w.getEdgeWeight("E", "C"));
    System.out.println("neighbors of A: " + w.getNeighbors("A"));
    System.out.println("shortest distance from A to D: " + w.getShortestDistance("A", "D"));
    System.out.println("shortest path:\n" + w.getShortestPath("A", "D"));
    System.out.println("shortest distance from E to F: " + w.getShortestDistance("E", "F"));
    System.out.println("shortest path:\n" + w.getShortestPath("E", "F"));
    
    System.out.println("removing B....");
    
    w.removeVertex("B");
    
    System.out.println(w);
    
    System.out.println("shortest distance from A to D: " + w.getShortestDistance("A", "D"));
    System.out.println("shortest path:\n" + w.getShortestPath("A", "D"));
    System.out.println("shortest distance from E to F: " + w.getShortestDistance("E", "F"));
    System.out.println("shortest path:\n" + w.getShortestPath("E", "F"));
  }
  
  
  
  
}