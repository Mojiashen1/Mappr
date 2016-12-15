import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class WelcomeScreen extends JPanel{
  private MapprGUI gui;
  private JButton startButton;
  private JLabel label;
  
  public WelcomeScreen(MapprGUI gui) {
    this.gui = gui;
    initialize();
  }
  
  private void initialize () {
    label = new JLabel();
    label.setText("Mappr");
    label.setFont(new Font("Garamond", Font.PLAIN, 24));
    add(label);
    
    startButton = new JButton("Let's go");
    startButton.addActionListener(new ButtonListener());
    add(startButton);
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == startButton) {
        gui.switchScreen();       
      }
    }
  }
  
}