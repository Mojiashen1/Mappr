/*
 * Author: Mojia Shen, Clarissa Verish
 * Date: Dec 11th 2016
 * Elevator.java extends from the Room class and create an elevator 
 * */

public class Elevator extends Room {
  private Elevator up, down;
  
  //constructor that takes a name and the floor the elevator is on, and sets room to four directions to null 
  public Elevator (String name, int floor) {
    super(name, floor);
  }
  
  //constructor that takes name, floor, and the elevator on the four directions of it
  public Elevator (String name, int floor, Room north, Room south, Room east, Room west, Elevator up, Elevator down) {
    super(name, floor, north, south, east, west);
    this.up = up;
    this.down = down;
  }
  
  //getters
  //return the object above the elevator 
  public Elevator getUp(){
    return up;
  }
  
  //return the object below the elevator 
  public Elevator getDown(){
    return down;
  }
  
  //setters
  //set the object above the elevator 
  public void setUp(Elevator up){
    this.up = up;
  }
  
  //set the object below the elevator 
  public void setDown(Elevator down){
    this.down = down;
  }  
}