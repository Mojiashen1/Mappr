import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import javafoundations.LinkedQueue;
import javafoundations.Queue;

public class MapprGUI extends JFrame{
  private WelcomeScreen welcomeScreen;
  private InputScreen inputScreen;
  private InstructionScreen instructionScreen;
  private Building building;
  private Queue<Room> directions;
  
  public MapprGUI() {
    building = new Building("Science Center", "create.mappr");
    setSize(320, 568);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    welcomeScreen = new WelcomeScreen(this);
    welcomeScreen.setVisible(true);
    inputScreen = new InputScreen(this);
    inputScreen.setVisible(true);
    instructionScreen = new InstructionScreen(this);
    instructionScreen.setVisible(true);

    directions = new LinkedQueue<Room>();
    
    getContentPane().add(welcomeScreen);

    setVisible(true);
  }
  
  public void switchScreen(ScreenType type) {
    getContentPane().removeAll();

    switch(type) {
      case WELCOME:
        getContentPane().add(inputScreen);
        break;
      case INPUT:
        getContentPane().add(instructionScreen);
        break;
      case INSTRUCTION:
        getContentPane().add(inputScreen);
        break;
      default:
        getContentPane().add(welcomeScreen);
    }
    //repaint the screen
    repaint();
    //refresh the screen
    invalidate();
    validate();
  }

  public Queue<Room> getDirections() {
    return directions;
  }

  public boolean setRouteData(RouteDataTransfer rDto) {
    Room r1 = building.findRoomByName(rDto.getFrom());
    Room r2 = building.findRoomByName(rDto.getTo());

    if(r1 != null && r2 != null) {
      directions = building.traverseBuilding(r1, r2);
      return true;
    }

    return false;
  }
  
  public static void main(String [] args) {
    MapprGUI gui = new MapprGUI();
  } 
}


