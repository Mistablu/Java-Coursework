import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This class represents a swing window. Once called, this class will create a
 * menu from which the user may choose to tackle any of three different levels.
 */
public class LevelSelect implements ActionListener {

    private JFrame frame; //The frame in which the gui is placed.
    private JPanel panel; //The panel on which the buttons are placed.
    private JPanel grid; //The layout of the level selection buttons.

    //Buttons which create the levels for users to play.
    private JButton lvl1Button = new JButton("Level 1");
    private JButton lvl2Button = new JButton("Level 2");
    private JButton lvl3Button = new JButton("Level 3");

    private JLabel welcomeDisplay = new JLabel("Please select a level."); //Displays text to the user informing them to select a level using the buttons provided.

    /**
     * Constructor, creates a new instance of the LevelSelect class with no initial parameters.
     */
    public LevelSelect() {
        //Creating the JFrame and giving it a fixed name and size
        frame = new JFrame();
        frame.setTitle("Case Noisettes");
        frame.setSize(300,150);
        //Adding action listeners to the buttons to register if a user has clicked them
        lvl1Button.addActionListener(this);
        lvl2Button.addActionListener(this);
        lvl3Button.addActionListener(this);
        //Creating the grid and placing the buttons in the specified format
        grid = new JPanel();
        grid.setLayout(new GridLayout(1,3));   
        grid.add(lvl1Button);
        grid.add(lvl2Button);
        grid.add(lvl3Button);
        //Creating a new panel and using it to place the welcomeDisplay above the grid
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add("Center",grid);
        panel.add("North",welcomeDisplay);
        //Making the panel visible and specifying what to do when the exit button is clicked
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * After lvl1Button is clicked, this function is called which creates the grid
     * and places the objects required to create the first level of Case Noisettes.
     */
    private void buildLevel1() {
        //creating objects
        Background bg = new Background();
        Squirrel redsq = new Squirrel(5,270,bg,"Red");
        Squirrel greysq = new Squirrel(10,0,bg,"Grey");
        //adding objects to the background grid
        bg.createFlower(9);
        bg.addRedSquirrel(redsq);
        bg.addGreySquirrel(greysq); 
        //hiding and destroying the level selector frame
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * After lvl2Button is clicked, this function is called which creates the grid
     * and places the objects required to create the second level of Case Noisettes.
     */
    private void buildLevel2() {
        //creating objects
        Background bg = new Background();
        Squirrel blacksq = new Squirrel(11,180,bg,"Black");
        Squirrel brownsq = new Squirrel(8,0,bg,"Brown");
        //adding objects to the background grid
        bg.createFlower(15);
        bg.addBlackSquirrel(blacksq);
        bg.addBrownSquirrel(brownsq);
        //hiding and destroying the level selector frame
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * After lvl3Button is clicked, this function is called which creates the grid
     * and places the objects required to create the final level of Case Noisettes.
     */
    private void buildLevel3() {
        //creating objects
        Background bg = new Background();
        Squirrel blacksq = new Squirrel(6,180,bg,"Black");
        Squirrel brownsq = new Squirrel(14,180,bg,"Brown");
        Squirrel redsq = new Squirrel(8,270,bg,"Red");
        Squirrel greysq = new Squirrel(11,180,bg,"Grey");
        //adding objects to the background grid
        bg.addBlackSquirrel(blacksq);
        bg.addBrownSquirrel(brownsq);
        bg.addRedSquirrel(redsq);
        bg.addGreySquirrel(greysq); 
        //hiding and destroying the level selector frame
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Determines the response given when each of the buttons is pressed, each of
     * the three buttons will create a respective level in the game.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==lvl1Button)
            buildLevel1();

        if (e.getSource()==lvl2Button)
            buildLevel2();

        if (e.getSource()==lvl3Button)
            buildLevel3();
        
    }
}