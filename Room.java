/*
 * Authors: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Room.java creates a room object that takes a name, the rooms on its four directions as well we the floor that it is on
 * */

public class Room{
  private String name;
  private Room north, south, east, west;
  private int floor;
  
  //constructor 1 that takes a name and the floor the room is on, and sets room to four directions to null 
  public Room (String name, int floor) {
    this.name = name;
    this.floor = floor;
    this.north = null;
    this.south = null;
    this.east = null;
    this.west = null;
  }
  
  //constructor 2 that takes name, floor, and the room on the four directions of it
  public Room (String name, int floor, Room north, Room south, Room east, Room west) {
    this.name = name;
    this.floor = floor;
    this.north = north;
    this.south = south;
    this.east = east;
    this.west = west;
  }
  
  //getters
  //return the name of the room
  public String getName(){
    return name;
  }
  
  //return the floor of the room
  public int getFloor(){
    return floor;
  }
  
  //return the room on the north
  public Room getNorth(){
    return north;
  }
  
  //return the room on the south
  public Room getSouth(){
    return south;
  }
  
  //return the room on the east
  public Room getEast(){
    return east;
  }
  
  //return the room on the west
  public Room getWest(){
    return west;
  }
  
  //setters
  //set the name of the room
  public void setName(String name){
    this.name = name;
  }
  
  //set the floor of the room
  public void setFloor(int floor){
    this.floor = floor;
  }
  
  //set the room on the north
  public void setNorth(Room north){
    this.north = north;
  }
  
  //set the room on the south
  public void setSouth(Room south){
    this.south = south;
  }
  
  //set the room on the east
  public void setEast(Room east){
    this.east = east;
  }
  
  //set the room on the west
  public void setWest(Room west){
    this.west = west;
  }
  
  //toString return the name of the room
  public String toString() {
    return name;
  }
}