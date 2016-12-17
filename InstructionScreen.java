import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class InstructionScreen extends JPanel{
  private MapprGUI gui;
  private JButton startButton;
  private JLabel label;
  
  public InstructionScreen(MapprGUI gui) {
    this.gui = gui;
    initialize();
  }
  
  private void initialize () {
    label = new JLabel();
    label.setText("Mappr");
    label.setFont(new Font("Garamond", Font.PLAIN, 24));
    add(label);
    
    startButton = new JButton("Instruction");
    startButton.addActionListener(new ButtonListener());
    add(startButton);
  }

  public ScreenType getType() {
    return ScreenType.INSTRUCTION;
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == startButton) {
        gui.switchScreen(getType());       
      }
    }
  }
  
}