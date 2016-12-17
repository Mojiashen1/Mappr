import java.util.LinkedList;
import java.util.HashMap;
import javafoundations.LinkedQueue;

public class Building {
  private String name;
  private HashMap<String, LinkedList<Floor>> elevators;
  private LinkedList<Floor> floors;
  
  public Building (String name) {
    this.name = name;
    this.elevators = new HashMap<String, LinkedList<Floor>>();
    this.floors = new LinkedList<Floor>();
  }
  
  public void addFloor(Floor f){
    if(!floors.contains(f)) {
      floors.add(f);
      connectFloor(f);
    }
  }
  
  private void connectFloor(Floor f) {
      LinkedList<Elevator> elevatorsOnFloor = getElevatorsOnFloor(f);
      LinkedList<Floor> connection;

      for(Elevator e : elevatorsOnFloor) {
        connection = elevators.get(e.getName());

        if(connection != null) {
          connection.add(f);
          elevators.put(e.getName(), connection);
        } else {
          connection = new LinkedList<Floor>();
          connection.add(f);
          elevators.put(e.getName(), connection);
        }
      }
  }

  private LinkedList<Elevator> getElevatorsBetweenFloors(Floor f1, Floor f2) {
    LinkedList<Elevator> connections = new LinkedList<Elevator>();

    if(floors.contains(f1) && floors.contains(f2)) {
      LinkedList<Elevator> elevatorsOnF1 = getElevatorsOnFloor(f1);
      LinkedList<Floor> floorsForElevator;

      for(Elevator e : elevatorsOnF1) {
        floorsForElevator = elevators.get(e.getName());

        if(floorsForElevator != null) {
          if(floorsForElevator.contains(f2)) {
            connections.add(e);
          }
        }
      }
    }

    return connections;
  }

  private LinkedList<Elevator> getElevatorsOnFloor(Floor f) {
    LinkedList<Elevator> elevatorsOnFloor = new LinkedList<Elevator>();

    for(Room r : f.getRooms()) {
      if(r instanceof Elevator) {
        elevatorsOnFloor.add((Elevator) r);
      }
    }

    return elevatorsOnFloor;
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
    return getElevatorsBetweenFloors(f1, f2).size() > 0;
  }
  
  //setters 
  public void setName(String name) {
    this.name = name;
  }
}