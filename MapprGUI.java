/*
 * Author: Andrew Deaver, Mojia Shen
 * MapprGUI extends JFrame. It has three private variables that store three screens 
 * It takes the user's input and display the result 
 * It has method switchScreen that switches screen when a user click on a button
 * Date: Dec 17th 2016
 * */

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class MapprGUI extends JFrame{
  private WelcomeScreen welcomeScreen;
  private InputScreen inputScreen;
  private InstructionScreen instructionScreen;
  
  //constructor that initialize three screen and display the welcome screen
  public MapprGUI() {
    //set the GUI to iPhone 6 screen size
    setSize(320, 568);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    //initiate all three screens and pass into the GUI as an input 
    welcomeScreen = new WelcomeScreen(this);
    welcomeScreen.setVisible(true);
    inputScreen = new InputScreen(this);
    inputScreen.setVisible(true);
    instructionScreen = new InstructionScreen(this);
    instructionScreen.setVisible(true);
    
    //add welcomeScreen to the frame initially to display first 
    getContentPane().add(welcomeScreen);

    setVisible(true);
  }
  
  //switchScreen method takes a screenType 
  //It removes all screen from the frame and according to the input, display the next screen. 
  //if the input type is not found, it will display welcome screen
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


