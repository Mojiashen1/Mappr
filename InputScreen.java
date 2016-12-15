import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class InputScreen extends JPanel implements Screen {
  private MapprGUI gui;
  private JButton startButton;
  private JLabel label;
  
  public InputScreen(MapprGUI gui) {
    this.gui = gui;
    initialize();
  }
  
  private void initialize () {
    label = new JLabel();
    label.setText("Maps");
    label.setFont(new Font("Garamond", Font.PLAIN, 24));
    add(label);
    
    startButton = new JButton("Let's Go!");
    startButton.addActionListener(new ButtonListener());
    add(startButton);
  }

  public ScreenType getType() {
    return ScreenType.INPUT;
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == startButton) {
        gui.switchScreen(getType());       
      }
    }
  }
  
}