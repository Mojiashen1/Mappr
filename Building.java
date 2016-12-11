import java.util.LinkedList;
import javafoundations.LinkedQueue;

public class Building {
  private String name;
  WeightedAdjMatGraph<Room> building;
  LinkedList<Floor> floors;
  
  public Building (String name) {
    this.name = name;
    this.building = new WeightedAdjMatGraph<Room>();
    this.floors = new LinkedList<Floor>();
  }
  
  public void addFloor(Floor f){
    floors.add(f);
  }
  
  public void addStairs() {
  }
  
  public void addElevator() {
  }
  
  public boolean sameFloor(Room r1, Room r2){
    return r1.getFloor() == r2.getFloor();
  }
  
  public LinkedQueue<Room> traverseBuilding(Room r1, Room r2){
    LinkedQueue<Room> traversal = new LinkedQueue<Room>();
    if (sameFloor(r1, r2)){
      // call traverseFloor and return
    }
    else {
      // find all traversals on both floors to each elevator, then check
      // to find traversal with shortest total distance.
      // once found, empty second queue into first queue then return 1st queue.
    }
    
    
    return traversal;
  }
  
  //getters
  public String getName() {
    return name;
  }
  
  public LinkedList<Floor> getFloors() {
    return floors;
  }
  
  public boolean isConnected (Floor f1, Floor f2) {
    return building.getEdge(f1, f2) > 0;
  }
  
  //setters 
  public void setName(String name) {
    this.name = name;
  }
}