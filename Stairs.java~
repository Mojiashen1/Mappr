/*Name: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Room.java
 * 
 * */

public class Stairs extends Room {
  private Stairs up, down;
  
  //constructor that takes a name and the floor the room is on, and sets room to four directions to null 
  public Stairs (String name, int floor) {
    super(name, floor);
  }
  
  //constructor that takes name, floor, and the room on the four directions of it
  public Stairs (String name, int floor, Room north, Room south, Room east, Room west, Stairs up, Stairs down) {
    super(name, floor, north, south, east, west);
    this.up = up;
    this.down = down;
  }
  
  //getters
  public Stairs getUp(){
    return up;
  }
  
  public Stairs getDown(){
    return down;
  }
  
  //setters
  public void setUp(Stairs up){
    this.up = up;
  }
  
  public void setDown(Stairs down){
    this.down = down;
  }
  
}