/*Name: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Room.java
 * 
 * */

public class Room{
  private String name;
  private Room north, south, east, west;
  private int floor;
  
  //constructor that takes a name and the floor the room is on, and sets room to four directions to null 
  public Room (String name, int floor) {
    this.name = name;
    this.floor = floor;
    this.north = null;
    this.south = null;
    this.east = null;
    this.west = null;
  }
  
  //constructor that takes name, floor, and the room on the four directions of it
  public Room (String name, int floor, Room north, Room south, Room east, Room west) {
    this.name = name;
    this.floor = floor;
    this.north = north;
    this.south = south;
    this.east = east;
    this.west = west;
  }
  
  //getters
  public String getName(){
    return name;
  }
  
  public int getFloor(){
    return floor;
  }
  
  public Room getNorth(){
    return north;
  }
  
  public Room getSouth(){
    return south;
  }
  
  public Room getEast(){
    return east;
  }
  
  public Room getWest(){
    return west;
  }
  
  //setters
  public void setName(String name){
    this.name = name;
  }
  
  public void setFloor(int floor){
    this.floor = floor;
  }
  
  public void setNorth(Room north){
    this.north = north;
  }
  
  public void setSouth(Room south){
    this.south = south;
  }
  
  public void setEast(Room east){
    this.east = east;
  }
  
  public void setWest(Room west){
    this.west = west;
  }
  

  
}