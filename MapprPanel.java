import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapprPanel extends JPanel {
  
//  private MapprDriver game;
  private JButton nextButton;
  private JLabel welcomeLabel;
  
  

  public MapperPanel(TicTacToe g) {
    this.game = g;
    
    buttons = new JButton[3][3];
    
    for (int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        buttons[i][j] = new JButton();
        buttons[i][j].addActionListener(new ButtonListener());
        add(buttons[i][j]);
      }  
    }
    
    
    quitButton = new JButton("QUIT");
    quitButton.addActionListener(new ButtonListener());
    add(quitButton);
    
    playerTurn = new JLabel("Player X goes first");
    add(playerTurn);
    
    resetButton = new JButton("Reset");
    resetButton.addActionListener(new ButtonListener());
    add(resetButton);
    /*
     xImg = createImageIcon("images/X.jpg",
     "an X image");
     oImg = createImageIcon("images/O.jpg",
     "a Y image");
     tieImg = createImageIcon("images/Tie.jpg",
     "a tie image");
     */
    
    /*setLayout(new BorderLayout(10, 10)); // hgap, vgap
     setBackground(Color.blue); // to match the background color of center grid panel
     */
    
  }
  
  /** 
   * Creates and returns an ImageIcon out of an image file.
   * @param path The path to the imagefile relevant to the current file.
   * @param description A short description to the image.
   * @return ImageIcon An ImageIcon, or null if the path was invalid. 
   */
  private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = TicTacToe.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
  /**
   * Separate class for the ButtonListener, which
   * will implement the abstract Action Listener interface
   */ 
  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == quitButton){
        System.exit(0);
      }
      else if(event.getSource() == resetButton){
        game = new TicTacToe();
        
        for (int i=0; i< 3; i++){
          for (int j=0; j< 3; j++){
           buttons[i][j].setText("");
           playerTurn.setText("Player X goes first");
          }
        }
      }
      else{
        for (int i = 0; i < 3; i++){
          for(int j = 0; j < 3; j++){
            if (event.getSource() == buttons[i][j]){
              
              game.takeAStep(i, j);
              
              if (!game.getIsXTurn()){
                buttons[i][j].setText("X");
                playerTurn.setText("Player O goes next");
              }
              else{
                buttons[i][j].setText("O");
                playerTurn.setText("Player X goes next");
              }
              int over = game.isGameOver();
              if (over == 0){
                playerTurn.setText("Tie game");
              }
              else if (over == 1){
                playerTurn.setText("Player X wins!");
              }
              else if (over == 2){
               playerTurn.setText("Player O wins!"); 
              }
            }
          }  
        }
      }
    }
  }
  
} //end TicTacToePanel public class

