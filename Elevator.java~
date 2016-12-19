/*Name: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Room.java
 * 
 * */

public class Elevator extends Room {
  private Elevator up, down;
  
  //constructor that takes a name and the floor the room is on, and sets room to four directions to null 
  public Elevator (String name, int floor) {
    super(name, floor);
  }
  
  //constructor that takes name, floor, and the room on the four directions of it
  public Elevator (String name, int floor, Room north, Room south, Room east, Room west, Elevator up, Elevator down) {
    super(name, floor, north, south, east, west);
    this.up = up;
    this.down = down;
  }
  
  //getters
  public Elevator getUp(){
    return up;
  }
  
  public Elevator getDown(){
    return down;
  }
  
  //setters
  public void setUp(Elevator up){
    this.up = up;
  }
  
  public void setDown(Elevator down){
    this.down = down;
  }
  
}