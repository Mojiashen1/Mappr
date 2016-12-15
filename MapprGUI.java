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
    
    //pack();
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

    invalidate();
    validate();
  }
  
  public static void main(String [] args) {
//    JFrame frame = new JFrame("Mappr");
//    frame.setSize(600000, 240000); //???
//    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    MapprGUI gui = new MapprGUI();
//    frame.getContentPane().add(gui);
//    
////    JLabel fromLabel = new JLabel();
////    label.setText("Where are you?");
////    frame.add(fromLabel);
////    JTextField field = new JTextField("classroom 257");
////    frame.add(field);
//    
////    JRadioButton radioButton = new JRadioButton("choose this");
////    frame.add(radioButton);
//
//    frame.pack();
//    frame.setVisible(true);
    MapprGUI gui = new MapprGUI();
  }
  
}


