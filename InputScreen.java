import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

/*
 * Author: Mojia Shen
 * Purpose: InputScreen is the second screen the users see. 
 * It asks the user to input where they are, where they want to go, and add their preferences for elevator or stair. 
 * Date: Dec 17th 2016
 * */

public class InputScreen extends JPanel implements Screen {
  private MapprGUI gui;
  private JButton searchButton;
  private JTextField from, to;
  private JRadioButton noPreference, stairOnly, elevatorOnly;
  private JLabel fromLabel, toLabel, prefLabel, errorLabel;
  
  public InputScreen(MapprGUI gui) {
    this.gui = gui;
    //create a layout the 4 rows by 2 columns
    setLayout(new GridLayout(4, 2));
    initialize();
  }
  
  /*
   * initialize creates label FROM (where the user is), TO (where the user is going), PREFERENCE (the user's preference)
   * it also creates input fields asking for the user input 
   * it also creates a button to take the user to the next page that display instructions
   * it is a private class so the layout cannot be changed. 
   * */
  private void initialize () {
    //**create a from label 
    fromLabel = new JLabel();
    fromLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0)); //set a margin on the left
    fromLabel.setText("FROM");
    fromLabel.setFont(new Font("Brandon Grotesque", Font.PLAIN, 16));
    fromLabel.setForeground(new Color(242, 124, 51));
    add(fromLabel);
    
    //create textPanel1 that take an empty label, the textField and another empty label, so the textField will sppear in the middle 
    JPanel textPanel1 = new JPanel(new GridLayout(3, 1));
    textPanel1.add(new JLabel());
    //**create a text input field FROM
    from = new JTextField();
    from.setFont(new Font("Brandon Grotesque", Font.PLAIN, 12));
    from.setForeground(new Color(94, 93, 92));
    textPanel1.add(from);
    textPanel1.add(new JLabel());
    textPanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25)); //set the margin on the right 
    add(textPanel1);
    
    //**create a to label 
    toLabel = new JLabel();
    toLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
    toLabel.setText("TO");
    toLabel.setForeground(new Color(242, 124, 51));
    toLabel.setFont(new Font("Brandon Grotesque", Font.PLAIN, 16));
    add(toLabel);
    
    //create textPanel2 that take an empty label, the textField and another empty label, so the textField will sppear in the middle 
    JPanel textPanel2 = new JPanel(new GridLayout(3, 1));
    textPanel2.add(new JLabel());
    //**create a new text input field TO
    to = new JTextField();
    to.setFont(new Font("Brandon Grotesque", Font.PLAIN, 12));
    to.setForeground(new Color(94, 93, 92));
    textPanel2.add(to);
    textPanel2.add(new JLabel());
    textPanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
    add(textPanel2);
    
    //**create a preference label 
    prefLabel = new JLabel();
    prefLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
    prefLabel.setFont(new Font("Brandon Grotesque", Font.PLAIN, 16));
    prefLabel.setForeground(new Color(242, 124, 51));
    prefLabel.setText("PREFERENCE");
    add(prefLabel);

    //**create a buttonGroup that takes three bottons so their selection will be mutually exclusive 
    ButtonGroup preferences = new ButtonGroup();
    noPreference = new JRadioButton();
    noPreference.setFont(new Font("Brandon Grotesque", Font.PLAIN, 12));
    noPreference.setText("No Preferences");
    noPreference.setForeground(new Color(94, 93, 92));
    noPreference.setSelected(true);

    stairOnly = new JRadioButton();
    stairOnly.setFont(new Font("Brandon Grotesque", Font.PLAIN, 12));
    stairOnly.setForeground(new Color(94, 93, 92));
    stairOnly.setText("Stairs Only");

    elevatorOnly = new JRadioButton();
    elevatorOnly.setFont(new Font("Brandon Grotesque", Font.PLAIN, 12));
    elevatorOnly.setForeground(new Color(94, 93, 92));
    elevatorOnly.setText("Elevator Only");

    preferences.add(noPreference);
    preferences.add(stairOnly);
    preferences.add(elevatorOnly);

    //**create a bottonPanel that allows the three buttons appear horizontally 
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(5, 1));
    buttonPanel.add(new JLabel());
    buttonPanel.add(noPreference);
    buttonPanel.add(stairOnly);
    buttonPanel.add(elevatorOnly);
    buttonPanel.add(new JLabel());

    add(buttonPanel);
    
    add(new JLabel());
    
    //**create a search Panel so the search button can appear in the middle of the grid 
    JPanel searchPanel = new JPanel(new GridLayout(4, 1));
    searchPanel.add(new JLabel());
    //**create a search button
    searchButton = new JButton("SEARCH");
    searchButton.setFont(new Font("Brandon Grotesque", Font.PLAIN, 16));
    searchButton.setBackground(new Color(87, 186, 137));
    searchButton.setForeground(Color.white);
    searchButton.setBorderPainted(false);
    searchButton.setOpaque(true);
    searchButton.addActionListener(new ButtonListener());
    searchPanel.add(searchButton);
    
    //**create a label that display input errors 
    errorLabel = new JLabel("Did you forget something?");
    errorLabel.setFont(new Font("Brandon Grotesque", Font.PLAIN, 10));
    errorLabel.setForeground(new Color(242, 145, 85));
    errorLabel.setVisible(false);
    searchPanel.add(errorLabel);
    searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
    add(searchPanel);
  }
  
  /*
   * validate takes a text input and checks if the input is empty
   * if the input is empty, then reture false (not valid), else return true 
   * */
  private boolean validate(JTextField text) {
    if (text.getText().equals("")) {
      return false;
    }
    return true;
  }
  
  /*
   * getType return the screen type of the current screen
   * */
  public ScreenType getType() {
    return ScreenType.INPUT;
  }
  
  /*
   * ButtonListener is a private class that implements ActionListener. It tells the button what action to perform when clicked
   * */ 
  private class ButtonListener implements ActionListener {
    /*actionPerformed is a method required by ActionListener interface
     * It takes an event and if the source of the event is searchButton, it will switch screen to the next
     * */
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == searchButton) {
        //if the FROM or TO field is empty, show the error message, otherwise, switch screen
        if (!validate(from) || !validate(to)) {
          errorLabel.setVisible(true);
        } else {
          gui.switchScreen(getType());    
        }
      }
    }
  }
  
}