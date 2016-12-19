/*
 * Author: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Stairs.java extends from the Room class and create a stair 
 * */

public class Stairs extends Room {
  private Stairs up, down;
  
  //constructor that takes a name and the floor the stair is on, and sets room to four directions to null 
  public Stairs (String name, int floor) {
    super(name, floor);
  }
  
  //constructor that takes name, floor, and the stair on the four directions of it
  public Stairs (String name, int floor, Room north, Room south, Room east, Room west, Stairs up, Stairs down) {
    super(name, floor, north, south, east, west);
    this.up = up;
    this.down = down;
  }
  
  //getters
  //return the object above the stair
  public Stairs getUp(){
    return up;
  }
  
  //return the object below the stair
  public Stairs getDown(){
    return down;
  }
  
  //setters
  //set the object above the stair
  public void setUp(Stairs up){
    this.up = up;
  }
  
  //set the object below the stair 
  public void setDown(Stairs down){
    this.down = down;
  }
  
}