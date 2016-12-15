import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class MapprGUI extends JFrame{
  private WelcomeScreen welcomeScreen;
  private InputScreen inputScreen;
  private InstructionScreen instructionScreen;
  
  public MapprGUI() {
    setSize(600000, 240000);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    welcomeScreen = new WelcomeScreen(this);
    welcomeScreen.setVisible(true);
    inputScreen = new InputScreen(this);
    inputScreen.setVisible(true);
    instructionScreen = new InstructionScreen(this);
    instructionScreen.setVisible(true);
    
    getContentPane().add(welcomeScreen);
    
    pack();
    setVisible(true);
  }
  
  public void switchScreen() {
    System.out.println(getComponent(0));
    if (getComponent(0) instanceof WelcomeScreen) {
      getContentPane().removeAll();
      getContentPane().add(inputScreen);
      invalidate();
      validate();
    } else if (getComponents()[0] instanceof InputScreen) {
      getContentPane().removeAll();
      getContentPane().add(instructionScreen);
      invalidate();
      validate();
    }
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


