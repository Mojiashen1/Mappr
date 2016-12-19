/*
 * Authors: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Floor.java 
 * The Floor class create a floor inside the building
 * it stores the floor number and a matrix of all the rooms
 * */

import javafoundations.LinkedQueue;
import javafoundations.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class Floor {
  private int n; //the number of the floor
  private WeightedAdjMatGraph<Room> matrix; //matrix that stores the location of the rooms
  
  /*contructor that takes a number of a floor and create a empty matrix of the rooms 
   * @param: number of a floor 
   * */
  public Floor(int n){
    this.n = n;
    this.matrix = new WeightedAdjMatGraph<Room>();
  }
  
  /*
   * addRoom takes a Room object and add it to the matrix
   * @param: Room object 
   * */
  public void addRoom(Room r){
    matrix.addVertex(r);
  }
  
  /*
   * connectRooms creates an edge between two rooms in the matrix and store the distance between them
   * @param: Room1, Room2, distance (between the rooms)
   * */
  public void connectRooms(Room r1, Room r2, int distance){
    matrix.addEdge(r1, r2, distance);
  }
  
  /*
   * traverseFloor takes the start and end rooms on the same floor and find the shortest path between them
   * @param: Room start, Room end
   * @return: a WeightedPath that contains the shortest path between the room 
   * */
  public WeightedPath<Room> traverseFloor(Room start, Room end){
    LinkedList<Room> rooms = getRooms();
    
    //double check if the rooms are contained in the list of rooms. They should be because user input is validated in the Building Class 
    if(rooms.contains(start) && rooms.contains(end)) { 
      //get the shortest path between rooms
      Queue<Room> shortestPath = matrix.getShortestPath(start, end);
      
      //get the shortest distance between rooms 
      int shortestDistance = matrix.getShortestDistance(start, end);
      
      //create a weightedPath of the shortest path 
      WeightedPath<Room> roomPath = new WeightedPath<Room>(shortestPath, shortestDistance);
      System.out.println("Hi");
      return roomPath;
    }
    
    return null;
  }
  
  /*
   * exist takes a room and check if it exists in the matrix 
   * */
  public boolean exist(Room room) {
    //if the room is not found, getVertex returns -1
    return matrix.isVertex(room);
  }
  
  //getters
  //get the floor number 
  public int getFloor() {
    return n;
  }
  
  //get the distance between two rooms
  public int getDistance(Room r1, Room r2){
    return matrix.getEdgeWeight(r1, r2);
  }
  
  //check if two rooms are adjacent to each other 
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
  //set the floor number 
  public void setFloor(int n) {
    this.n = n;
  }
  
  /*create an edge in the matrix that sets the distance between two rooms 
   * @param: Room1, Room2, the distance between them
   * */
  public void setDistance(Room r1, Room r2, int distance) {
    matrix.addEdge(r1, r2, distance);
  }
  
  //findRoomByName takes a name and find the corresponding room in the rooms
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
  
  public Queue<String> getInstructions(Room start, Room end){
    Instructions i = new Instructions(traverseFloor(start, end));
    return i.getInstructions();
  }
  
  private class Instructions {
    private Queue<String> instructions;
    private Queue<Room> traversal;
    
    public Instructions(WeightedPath<Room> traversal){
      this.traversal = traversal.getPath();
      this.instructions = new LinkedQueue<String>();
    }
    
    public Queue<String> getInstructions() {
      Room current = traversal.dequeue();
      Room temp;
      String lastDirection = "";
      String newDirection = "";
      int currentDistance = 0;
      
      while (!traversal.isEmpty()){
        temp = traversal.dequeue();
        System.out.println(getDistance(current, temp));
        newDirection = getRelationship(current, temp);
        if (newDirection.equals(lastDirection)) {
          System.out.println("Same direction");
          currentDistance += getDistance(current, temp);
        }
        else if (!newDirection.equals(lastDirection)){
          if (currentDistance == 0) {
            currentDistance = getDistance(current, temp);
          }
          instructions.enqueue(convertToNatLanguage(lastDirection, newDirection, current, temp, currentDistance));
          currentDistance = 0;    
        }
        lastDirection = newDirection;
        current = temp;
      }
      
      return instructions;
    }
    
    private String convertToNatLanguage(String lastDirection, String newDirection, Room oldRoom, Room newRoom, int distance){
      String instruction = "";
      if (lastDirection.equals("")){
        instruction += "Walk towards room " + newRoom.getName() + " for " + getDistance(oldRoom, newRoom) + " steps.";
      }
      else {
        instruction += "Then take " + distance + " steps, then turn ";
        String[] rightTurn = new String[]{"north", "east", "south", "west"};
        int newDirIndex = 0;
        int lastDirIndex = 0;
        // finding index in rightturn array
        for (int i = 0; i < rightTurn.length; i++){
          if (rightTurn[i].equals(lastDirection)){
            lastDirIndex = i;
          }
          if (rightTurn[i].equals(newDirection)){
            newDirIndex = i;
          }
        }
        
        if (lastDirIndex < newDirIndex || (lastDirIndex == 3 && newDirIndex == 0)){  
          instruction += "right ";
        }
        else {
          instruction += "left ";
        }
        
        instruction += "at room " + oldRoom.getName() + ".";
      }
      if (newRoom == null) {
        instruction += "Walk " + distance + " more steps and you are at room " + oldRoom;
      }
      return instruction;
    }
    
    private String getRelationship(Room r1, Room r2){
      String n1 = r1.getName();
      String n2 = r2.getName();
      if (r1.getNorth() != null) {
        if (r1.getNorth().getName() == n2) {
          return "north";
        }
      }
      else if (r1.getEast() != null) {
        if (r1.getEast().getName() == n2) {
          return "east";
        } 
      } 
      else if (r1.getSouth() != null) {
        if (r1.getSouth().getName() == n2) {
          return "south";
        } 
      }
      else if (r1.getWest() != null) {
        if (r1.getWest().getName() == n2) {
          return "west";
        }
      }
      
      return "NOT FOUND";
      
    }
  }
  
  public static void main (String [] args) {
    Floor floor = new Floor(1);
    Room r1 = new Room("A", 1);
    Room r2 = new Room("B", 1);
    Room r3 = new Room("C", 1);
    Room r4 = new Room("D", 1);
    Room r5 = new Room("E", 1);
    
    r1.setNorth(r2);
    r2.setNorth(r3);
    r3.setEast(r4);
    r4.setSouth(r5);
    
    floor.addRoom(r1);
    floor.addRoom(r2);
    floor.addRoom(r3);
    floor.addRoom(r4);
    floor.addRoom(r5);
    
    floor.connectRooms(r1, r2, 3);
    floor.connectRooms(r2, r3, 5);
    floor.connectRooms(r3, r4, 6);
    floor.connectRooms(r4, r5, 9);
    
    
    Queue i = floor.getInstructions(r1, r5);
    System.out.println(i);
    
  }
  
}