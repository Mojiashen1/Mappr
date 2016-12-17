/*
 * Author: Mojia Shen
 * Purpose: WelcomeScreen is the first screen the users see. 
 * It has a title, subtitle and a button that takes the user to the InputScreen where they input where they want to go
 * Date: Dec 17th 2016
 * */

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

public class WelcomeScreen extends JPanel implements Screen {
  private MapprGUI gui;
  private JButton startButton;
  private JLabel label, subTitle;
  
  public WelcomeScreen(MapprGUI gui) {
    //takes GUI to easily go back to the MapprGUI
    this.gui = gui;
    //use GridLayout to make the UI look clean
    setLayout(new GridLayout(5, 1)); 
    initialize();
  }
  
  /*
   * initialize creates a the title, subtitle and a button, and add them all to the WelcomeScreen Panel
   * */
  private void initialize () {
    //add empty space for the layout have a top margin
    add(new JLabel());
    
    //**create a label to group the title and subtitle together. We want them to be close to each other
    JPanel labelP = new JPanel(new GridLayout(2, 1));
    label = new JLabel();
    label.setText("M A P P R");
    label.setForeground(new Color(242, 124, 51));
    label.setHorizontalAlignment(JLabel.CENTER); //center the label
    label.setFont(new Font("Brandon Grotesque", Font.PLAIN, 34));
    labelP.add(label);

    subTitle = new JLabel();
    subTitle.setText("NAVIGATE YOUR WAY");
    subTitle.setForeground(new Color(232, 192, 160));
    subTitle.setHorizontalAlignment(JLabel.CENTER);
    subTitle.setFont(new Font("Brandon Grotesque", Font.PLAIN, 15));
    
    labelP.add(subTitle);
    add(labelP);
    
    //another empty space
    add(new JLabel());

    //**create button
    startButton = new JButton("LET'S GO");
    startButton.setForeground(Color.white);
    startButton.setBackground(new Color(87, 186, 137));
    startButton.setBorderPainted(false); //this makes the background color show up
    startButton.setOpaque(true); 
    startButton.setFont(new Font("Brandon Grotesque", Font.PLAIN, 12));
    startButton.addActionListener(new ButtonListener());
    
    //create a layout group so the button can appear in the middle horizontally
    GridLayout layout = new GridLayout(1, 3);
    JPanel buttons = new JPanel(layout);
    buttons.add(new JLabel());
    
    //create an inner layout so the button can appear in the middle vertically 
    GridLayout innerLayout = new GridLayout(3, 1);
    JPanel innerButtons = new JPanel(innerLayout);
    innerButtons.add(new JLabel());
    innerButtons.add(startButton);
    innerButtons.add(new JLabel());
    buttons.add(innerButtons);
    buttons.add(new JLabel());
    add(buttons);
    setBackground(new Color(237, 235, 230));
  }

  /*
   * getType return the screen type of the current screen
   * */
  public ScreenType getType() {
    return ScreenType.WELCOME;
  }
  
  /*
   * ButtonListener is a private class that implements ActionListener. It tells the button what action to perform when clicked
   * */
  private class ButtonListener implements ActionListener {
    /*actionPerformed is a method required by ActionListener interface
     * It takes an event and if the source of the event is startButton, it will switch screen to the next
     * */
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == startButton) {
        gui.switchScreen(getType());       
      }
    }
  }
  
}