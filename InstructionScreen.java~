/*
 * Author: Mojia Shen
 * Purpose: InstructionScreen is the third screen. It has a button that takes the user back to the input screen
 * It also has a panel that display a list of instructions
 * It also has a button that ends the program.
 * Date: Dec 17th 2016
 * */

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

public class InstructionScreen extends JPanel{
  private MapprGUI gui;
  private JButton backButton, finishButton;
  private JLabel instructionLabel, instructions;
  
  public InstructionScreen(MapprGUI gui) {
    this.gui = gui;
    setLayout(new GridLayout(5, 1));
    initialize();
  }
  
  private void initialize () {
    //****create a layout group so the button can appear in the left horizontally
    JPanel outerLayout = new JPanel(new GridLayout(1, 3));
    
    //create an inner layout so the button can appear in the top vertically 
    JPanel innerLayout = new JPanel(new GridLayout(3, 1));
    
    //**create a back button
    backButton = new JButton("BACK");
    backButton.setFont(new Font("Brandon Grotesque", Font.PLAIN, 16));
    backButton.setBackground(new Color(87, 186, 137));
    backButton.setForeground(Color.white);
    backButton.setBorderPainted(false);
    backButton.setOpaque(true);
    backButton.addActionListener(new ButtonListener());

    innerLayout.add(backButton);
    innerLayout.add(new JLabel());
    innerLayout.add(new JLabel());
    
    outerLayout.add(innerLayout);
    //add two empty labels
    outerLayout.add(new JLabel());
    outerLayout.add(new JLabel());
    outerLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
    add(outerLayout);
    //****finish layout group for the back button
    
    //**create an instruction label
    instructionLabel = new JLabel("HERE IS WHAT YOU DO");
    instructionLabel.setHorizontalAlignment(JLabel.CENTER);
    instructionLabel.setForeground(new Color(242, 124, 51));
    instructionLabel.setFont(new Font("Brandon Grotesque", Font.PLAIN, 16));
    add(instructionLabel);
    
    instructions = new JLabel("Blah blah BLAH");
    instructions.setHorizontalAlignment(JLabel.CENTER);
    instructions.setForeground(new Color(94, 93, 92));
    instructions.setFont(new Font("Brandon Grotesque", Font.PLAIN, 14));
    add(instructions);
    
    //****create a layout group so the button can appear in the middle horizontally
    JPanel outerLayout2 = new JPanel(new GridLayout(1, 3));
    outerLayout2.add(new JLabel());
    
    //create an inner layout so the button can appear in the middle vertically 
    JPanel innerLayout2 = new JPanel(new GridLayout(3, 1));
    innerLayout2.add(new JLabel());
    
    //**create a finish button
    finishButton = new JButton("FINISH");
    finishButton.setFont(new Font("Brandon Grotesque", Font.PLAIN, 16));
    finishButton.setBackground(new Color(87, 186, 137));
    finishButton.setForeground(Color.white);
    finishButton.setBorderPainted(false);
    finishButton.setOpaque(true);
    finishButton.addActionListener(new ButtonListener());
    
    innerLayout2.add(finishButton);
    innerLayout2.add(new JLabel());
    
    outerLayout2.add(innerLayout2);
    //add an empty labels
    outerLayout2.add(new JLabel());
    add(outerLayout2);
    //****finish layout group for the finish button
  }
  
  /*
   * getType return the screen type of the current screen
   * */
  public ScreenType getType() {
    return ScreenType.INSTRUCTION;
  }
  
  /*
   * ButtonListener is a private class that implements ActionListener. It tells the button what action to perform when clicked
   * */
  private class ButtonListener implements ActionListener {
    /*actionPerformed is a method required by ActionListener interface
     * It takes an event and if the source of the event is backButton, it will switch screen to the input screen
     * If the source of the event is finishButton, the program will terminate
     * */
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == backButton) {
        gui.switchScreen(getType());       
      } else if (event.getSource() == finishButton) {
        gui.dispose();
      }
    }
  }
  
}