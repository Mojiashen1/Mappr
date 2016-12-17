import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;


public class MapprGUI extends JFrame{
  private WelcomeScreen welcomeScreen;
  private InputScreen inputScreen;
  private InstructionScreen instructionScreen;
  
  public MapprGUI() {
    setSize(320, 568);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    welcomeScreen = new WelcomeScreen(this);
    welcomeScreen.setVisible(true);
    inputScreen = new InputScreen(this);
    inputScreen.setVisible(true);
    instructionScreen = new InstructionScreen(this);
    instructionScreen.setVisible(true);
    
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
  
  public static void main(String [] args) {
    MapprGUI gui = new MapprGUI();
  } 
}


