import java.util.LinkedList;
import java.util.HashMap;
import javafoundations.LinkedQueue;
import javafoundations.Queue;
import java.util.Scanner;
import java.io.*;

public class Building {
  private String name;
  private HashMap<String, LinkedList<Floor>> elevators;
  private LinkedList<Floor> floors;
  
  public Building (String name) {
    this.name = name;
    this.elevators = new HashMap<String, LinkedList<Floor>>();
    this.floors = new LinkedList<Floor>();
  }

  public Building(String name, String fileName) {
    this(name);
    readFromTextFile(fileName);
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

  private LinkedList<Integer> getFloorsForElevator(Elevator e) {
    LinkedList<Integer> elevatorFloors = new LinkedList<Integer>();
    LinkedList<Floor> floorsForElevator = elevators.get(e.getName());

    for(Floor f : floorsForElevator) {
      elevatorFloors.add(f.getFloor());
    }

    return elevatorFloors;
  }

  private int minDifference(LinkedList<Integer> elevatorFloors, int floor2) {
    int minDistance = Integer.MAX_VALUE;
    int minNum = -1;

    for(Integer i : elevatorFloors) {
      if(Math.abs(i - floor2) < minDistance) {
        minDistance = Math.abs(i - floor2);
        minNum = i;
      }
    }

    return minNum;
  }
  
  public boolean sameFloor(Room r1, Room r2){
    return r1.getFloor() == r2.getFloor();
  }
  
  public Queue<Room> traverseBuilding(Room r1, Room r2){
    Queue<Room> traversal = new LinkedQueue<Room>();

    int floor1 = r1.getFloor();
    int floor2 = r2.getFloor();

    if(r1 instanceof Elevator && !(r2 instanceof Elevator)) {
      LinkedList<Integer> elevatorFloors = getFloorsForElevator((Elevator) r1);

      if(elevatorFloors.contains(floor2)) {
        floor1 = floor2;
      } else {
        floor1 = minDifference(elevatorFloors, floor2);
      }

    } else if (!(r1 instanceof Elevator) && (r2 instanceof Elevator)) {
      LinkedList<Integer> elevatorFloors = getFloorsForElevator((Elevator) r2);

      if(elevatorFloors.contains(floor1)) {
        floor2 = floor1;
      } else {
        floor2 = minDifference(elevatorFloors, floor1);
      }
    } else {
       // *** What Happens if They're Both Elevators ***
    }

    if (floor1 == floor2){
      // call traverseFloor and return
      Floor roomFloor = getFloorByNumber(r1.getFloor());

      if(roomFloor != null) {
        return roomFloor.traverseFloor(r1, r2).getPath();
      }
    }
    else {
      // find all traversals on both floors to each elevator, then check
      // to find traversal with shortest total distance.
      // once found, empty second queue into first queue then return 1st queue.

      Floor fl1 = getFloorByNumber(floor1);
      Floor fl2 = getFloorByNumber(floor2);

      LinkedList<Elevator> connections = getElevatorsBetweenFloors(fl1, fl2);

      WeightedPath minPath = null, fl1Path, fl2Path;
      int minDistance = Integer.MAX_VALUE, combinedWeight;
      Queue<Room> combinedPath;

      for(Elevator e : connections) {
        fl1Path = fl1.traverseFloor(r1, fl1.findRoomByName(e.getName()));
        fl2Path = fl2.traverseFloor(fl2.findRoomByName(e.getName()), r2);

        if(fl1Path != null && fl2Path != null) {
          combinedWeight = fl1Path.getWeight() + fl2Path.getWeight();
          combinedPath = combineQueues(fl1Path.getPath(), fl2Path.getPath());

          if(combinedWeight < minDistance) {
            minPath = new WeightedPath(combinedPath, combinedWeight);
            minDistance = combinedWeight;
          }
        }
      }

      if(minPath != null) {
        return minPath.getPath();
      }
    }
    
    
    return traversal;
  }

  private Queue<Room> combineQueues(Queue<Room> queue1, Queue<Room> queue2) {
    int stop = queue2.size();
    queue2.dequeue();
    for(int i = 1; i < stop; i++) {
      queue1.enqueue(queue2.dequeue());
    }

    return queue1;
  }

  private Floor getFloorByNumber(int floorNumber) {
    for(Floor f : floors) {
      if(f.getFloor() == floorNumber) {
        return f;
      }
    }

    return null;
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

  public boolean isConnected(int f1, int f2) {
    return getElevatorsBetweenFloors(getFloorByNumber(f1), getFloorByNumber(f2)).size() > 0;
  }

  public Room findRoomByName(String name) {
    Room room = null;

    for(Floor f : floors) {
      room = f.findRoomByName(name);

      if(room != null) {
        return room;
      }
    }

    return room;
  }

  private void readFromTextFile(String fileName) {
    try {
      LinkedList<Room> rooms = new LinkedList<Room>();
      LinkedList<Floor> floors = new LinkedList<Floor>();

      boolean collectingRooms = true;

      File inFile = new File(fileName);
      Scanner input = new Scanner(inFile); 

      String[] tokens;

      while(input.hasNextLine()) {
        tokens = input.nextLine().split("\\s+");
        if(collectingRooms) {
          if(tokens.length > 1) {
            if(Boolean.valueOf(tokens[2])) {
              rooms.add(new Elevator(tokens[0], Integer.parseInt(tokens[1])));
            } else {
              rooms.add(new Room(tokens[0], Integer.parseInt(tokens[1])));
            }
          } else {
            collectingRooms = false;
            createFloors(rooms);
            continue;
          }
        } else {
          createConnections(tokens);
        }
      }

    } catch(IOException e) {
      System.out.println("Error occurred");
      return;
    }
    
    System.out.println("Successfully created building");
  }

  private void createFloors(LinkedList<Room> rooms) {
    LinkedList<Floor> floorsToAdd = new LinkedList<Floor>();
    Floor fa;

    for(Room r : rooms) {
      fa = findByNumber(floorsToAdd, r.getFloor());
      if(fa != null) {
        fa.addRoom(r);
      } else {
        floorsToAdd.add(new Floor(r.getFloor()));
        fa = findByNumber(floorsToAdd, r.getFloor());
        fa.addRoom(r);
      }
    }

    for(Floor f : floorsToAdd) {
      addFloor(f);
    }
  }

  private Floor findByNumber(LinkedList<Floor> floorsToAdd, int number) {
    Floor f = null;

    for(Floor fs : floorsToAdd) {
      if(fs.getFloor() == number) {
        return fs;
      }
    }

    return f;
  }

  private void createConnections(String[] tokens) {
    Floor f = f = getFloorByNumber(Integer.parseInt(tokens[1]));
    Room r = findRoomByName(tokens[0]), connect;

    if(!tokens[2].equals("none")) {
      connect = f.findRoomByName(tokens[2]);
      r.setNorth(connect);
      
      f.connectRooms(r, connect, Integer.parseInt(tokens[3]));
    }

    if(!tokens[4].equals("none")) {
      connect = f.findRoomByName(tokens[4]);
      r.setEast(connect);

      f.connectRooms(r, connect, Integer.parseInt(tokens[5]));
    }

    if(!tokens[6].equals("none")) {
      connect = f.findRoomByName(tokens[6]);
      r.setSouth(connect);

      f.connectRooms(r, connect, Integer.parseInt(tokens[7]));
    }

    if(!tokens[8].equals("none")) {
      connect = f.findRoomByName(tokens[8]);
      r.setWest(connect);

      f.connectRooms(r, connect, Integer.parseInt(tokens[9]));
    }
  }
  
  //setters 
  public void setName(String name) {
    this.name = name;
  }

  public static void main(String[] args) {
    Building building = new Building("Academic Building");
    Floor floor1 = new Floor(1);
    Floor floor2 = new Floor(2);
    Floor floor3 = new Floor(3);

    Room room1 = new Room("Science Room", 1);
    Room room2 = new Room("Math Room", 1);

    Room room3 = new Room("English Room", 2);
    Room room4 = new Room("Music Room", 2);

    Elevator elevator1 = new Elevator("Elevator 1", -1);
    Elevator elevator2 = new Elevator("Elevator 2", -1);

    floor1.addRoom(room1);
    floor1.addRoom(room2);
    floor1.addRoom(elevator1);
    floor1.connectRooms(room1, room2, 1);
    floor1.connectRooms(room2, elevator1, 1);
    floor1.connectRooms(room1, elevator1, 5);

    floor2.addRoom(room3);
    floor2.addRoom(room4);
    floor2.addRoom(elevator1);
    floor2.addRoom(elevator2);
    floor2.connectRooms(elevator1, room3, 1);
    floor2.connectRooms(room3, room4, 2);

    floor3.addRoom(elevator2);

    building.addFloor(floor1);
    building.addFloor(floor2);
    building.addFloor(floor3);

    Room searchOne = building.findRoomByName("Science Room");
    Room searchTwo = building.findRoomByName("Music Room");

    System.out.println("Floors 1 & 2 are connected: " + building.isConnected(floor1, floor2));
    System.out.println("Floors 1 & 3 are directly connected: " + building.isConnected(floor1, floor3));
    System.out.println("Path between Room1 and Elevator1 on Floor1: " + building.traverseBuilding(room1, elevator1));
    System.out.println("Path between Room1 and Room 4: " + building.traverseBuilding(room1, room4));
    System.out.println("Searching by name: " + building.traverseBuilding(searchOne, searchTwo));

    Building academicBuilding = new Building("Science Center", "create.mappr");

    Room s1 = academicBuilding.findRoomByName("RM100");
    Room s2 = academicBuilding.findRoomByName("RM202");

    System.out.println("Floors 1 & 2 are connected: " + academicBuilding.isConnected(1, 2));
    System.out.println("Path from Room 100 to Room 202: " + academicBuilding.traverseBuilding(s1, s2));
  }
}