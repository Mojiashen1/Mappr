/*Name: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Floor.java
 * The Floor class 
 * */

import javafoundations.LinkedQueue;
import javafoundations.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class Floor {
  private int n; //the number of the floor
  private WeightedAdjMatGraph<Room> matrix;
  
  public Floor(int n){
    this.n = n;
    this.matrix = new WeightedAdjMatGraph<Room>();
  }
  
  public void addRoom(Room r){
    matrix.addVertex(r);
  }
  
  public void connectRooms(Room r1, Room r2, int distance){
    matrix.addEdge(r1, r2, distance);
  }
  
  public WeightedPath<Room> traverseFloor(Room start, Room end){
    LinkedList<Room> rooms = getRooms();

    if(rooms.contains(start) && rooms.contains(end)) {
      Queue<Room> shortestPath = matrix.getShortestPath(start, end);
      int shortestDistance = matrix.getShortestDistance(start, end);

      WeightedPath<Room> roomPath = new WeightedPath<Room>(shortestPath, shortestDistance);
      return roomPath;
    }

    return null;
  }
  
  public boolean exist(Room room) {
    //if the room is not found, getVertex returns -1
    return matrix.isVertex(room);
  }
  
  //getters
  public int getFloor() {
    return n;
  }
  
  public int getDistance(Room r1, Room r2){
    return matrix.getEdgeWeight(r1, r2);
  }
  
  public boolean isAdjacent(Room r1, Room r2) {
    return matrix.getEdgeWeight(r1, r2) >= 0;
  }
  
  // returns a linkedlist of all rooms
  public LinkedList<Room> getRooms() {
    LinkedList<Room> rooms = new LinkedList<Room>();
    Iterator<Room> it = matrix.iterator();
    
    while (it.hasNext()){
      rooms.add(it.next());
    }
    
    return rooms;
  }
  
  //setters
  public void setFloor(int n) {
    this.n = n;
  }
  
  public void setDistance(Room r1, Room r2, int distance) {
    matrix.addEdge(r1, r2, distance);
  }
  
  public Room findRoomByName(String name) {
    LinkedList<Room> rooms = getRooms();
    String searchName = name.toLowerCase().trim();
    String compareName;

    for(Room r : rooms) {
      compareName = r.getName().toLowerCase().trim();
      if(compareName.equals(searchName)) {
        return r;
      }
    }

    return null;
  }
}