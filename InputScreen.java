import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.GridLayout;

public class InputScreen extends JPanel implements Screen {
  private MapprGUI gui;
  private JButton searchButton;
  private JTextField from, to;
  private JRadioButton noPreference, stairOnly, elevatorOnly;
  private ButtonGroup preferences;
  private JPanel buttonPanel;
  private JLabel fromLabel, toLabel, prefLabel;
  
  public InputScreen(MapprGUI gui) {
    this.gui = gui;
    setLayout(new GridLayout(4, 2));
    initialize();
  }
  
  private void initialize () {
    fromLabel = new JLabel();
    fromLabel.setText("From:");
    fromLabel.setFont(new Font("Garamond", Font.PLAIN, 16));
    add(fromLabel);

    from = new JTextField();
    from.setFont(new Font("Garamond", Font.PLAIN, 16));
    add(from);

    toLabel = new JLabel();
    toLabel.setText("To:");
    toLabel.setFont(new Font("Garamond", Font.PLAIN, 16));
    add(toLabel);

    to = new JTextField();
    to.setFont(new Font("Garamond", Font.PLAIN, 16));
    add(to);

    prefLabel = new JLabel();
    prefLabel.setFont(new Font("Garamond", Font.PLAIN, 16));
    prefLabel.setText("Preferences");
    add(prefLabel);

    preferences = new ButtonGroup();

    noPreference = new JRadioButton();
    noPreference.setFont(new Font("Garamond", Font.PLAIN, 16));
    noPreference.setText("No Preferences");
    noPreference.setSelected(true);

    stairOnly = new JRadioButton();
    stairOnly.setFont(new Font("Garamond", Font.PLAIN, 16));
    stairOnly.setText("Stairs Only");

    elevatorOnly = new JRadioButton();
    elevatorOnly.setFont(new Font("Garamond", Font.PLAIN, 16));
    elevatorOnly.setText("Elevator Only");

    preferences.add(noPreference);
    preferences.add(stairOnly);
    preferences.add(elevatorOnly);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(3, 1));
    buttonPanel.add(noPreference);
    buttonPanel.add(stairOnly);
    buttonPanel.add(elevatorOnly);

    add(buttonPanel);

    searchButton = new JButton("Search for a Route");
    searchButton.addActionListener(new ButtonListener());
    add(searchButton);

    // startButton = new JButton("Let's Go!");
    // startButton.addActionListener(new ButtonListener());
    // add(startButton);
  }

  public ScreenType getType() {
    return ScreenType.INPUT;
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == searchButton) {
        gui.switchScreen(getType());       
      }
    }
  }
  
}