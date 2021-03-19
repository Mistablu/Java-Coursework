import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This class represents the board in the game, It's main purpose is to build a
 * gui which can be used to move the squirrels that have been placed on the board.
 * It is also used to store any variables which might need to be shared between the squirrels.
 */
public class Background implements ActionListener{

    private JFrame frame; //The frame in which the gui is placed.
    private JPanel panel; //The panel on which the entire board is placed.
    private JPanel grid; //The grid layout of the buttons on the board.
    private JButton[] gridButton; //An array of buttons to be placed onto the grid, representing playing space on the board.
    private Squirrel squirrel; //Holds the object of the currently selected squirrel class.
    private String direction; //The direction in which the squirrel is to be moved.
    private int flowerLocation=16; //Location of the hole-blocking flower. It is initialised to a number outside of bounds
                                   //so that it does not block a square when a flower is not created.
    private Squirrel redSquirrel,blackSquirrel,greySquirrel,brownSquirrel; //stores the object which represents each coloured squirrel on the board.
    private Integer[] obstacles; //Holds the indexes of all the currently inaccessible locations on the game board.
    private Integer[] filledHoles; //Holds the indexes of all the holes which have been filled with nuts.
    private Squirrel[] activeSquirrels; //Stores every squirrel object which has been added to the board in order to check 
                                        //for game completion with a dynamic number of squirrels.
    //Buttons which represent pictures of arrocks that allow the user to move the selected squirrel in a certain direction.
    private JButton upArrow = new JButton(new Picture("icons/BigArrow.png",0));
    private JButton rightArrow = new JButton(new Picture("icons/Arrow.png",90));
    private JButton downArrow = new JButton(new Picture("icons/BigArrow.png",180));
    private JButton leftArrow = new JButton(new Picture("icons/Arrow.png",270));

    /**
     * Constructor. Creates a new instance of the Background class which creates the empty
     * board used in the game.
     */
    public Background() {
        //Initialising arrays lengths
        activeSquirrels = new Squirrel[4];
        gridButton = new JButton[16];
        obstacles = new Integer[11];
        filledHoles = new Integer[4];
        //Creating the JFrame and giving it a fixed name and size
        frame = new JFrame();
        frame.setTitle("Case Noisettes");
        frame.setSize(600,600);
        //Creating the game board and placing the grid buttons, turning specified button icons into 'Holes' instead of 'Empty' icons
        grid = new JPanel();
        grid.setLayout(new GridLayout(4,4));   
        for (int i=0; i<16; i++) {
            if (i == 2 || i==4|| i==9 || i==15) //indexes of icons to be initialised as holes
                gridButton[i] = new JButton(new Picture("icons/Hole.png", 0));
            else
                gridButton[i] = new JButton(new Picture("icons/Empty.png", 0));

            grid.add(gridButton[i]);
            //Register when a user clicks on each of the added grid buttons
            gridButton[i].addActionListener(this);
        }
        //Creating a new panel and using it to place the directional buttons on each side of the game board
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add("Center",grid);
        panel.add("South",downArrow);
        panel.add("West",leftArrow);
        panel.add("East",rightArrow);
        panel.add("North",upArrow);
        //Allows each of the directional buttons to register when a user has clicked on them
        upArrow.addActionListener(this);
        downArrow.addActionListener(this);
        rightArrow.addActionListener(this);
        leftArrow.addActionListener(this);
        //Making the panel visible and specifying what to do when the exit button is clicked
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Used to create a hole-blocking flower at the specified location.
     * @param location The index of the gridbutton the flower will occupy.
     */
    public void createFlower(int location) {
        gridButton[location].setIcon(new Picture("icons/Flower.png",0));
        this.flowerLocation=location;
    }

    /**
     * Takes a squirrel object as a parameter and adds it to the active squirrels on the board
     * only one of each colour squirrel is allowed and the colour is determined by which method
     * is called.
     * @param newSquirrel The squirrel object being added to the background class
     */
    public void addRedSquirrel(Squirrel newSquirrel) {
        this.redSquirrel=newSquirrel;
        this.activeSquirrels[0]=redSquirrel;
    }

    public void addBlackSquirrel(Squirrel newSquirrel) {
        this.blackSquirrel=newSquirrel;
        this.activeSquirrels[1]=blackSquirrel;
    }

    public void addBrownSquirrel(Squirrel newSquirrel) {
        this.brownSquirrel=newSquirrel;
        this.activeSquirrels[2]=brownSquirrel;
    }

    public void addGreySquirrel(Squirrel newSquirrel) {
        this.greySquirrel=newSquirrel;
        this.activeSquirrels[3]=greySquirrel;
    }

    /**
     * Moves the currently selected squirrel in the location specified by which
     * of the directional buttons was clicked. Also checks if the clear condition has been
     * met after each movement.
     */
    private void moveSquirrel() {
            //move active squirrel in specified direction
            squirrel.moveSquirrel(direction);
            //check if game is cleared
            boolean cleared = this.checkCleared();
            if (cleared == true)
                this.levelCleared();

    }

    /**
     * Checks if every active squirrel has dropped it's nut in one of the holes on the board,
     * returning a boolean which determines whether the game has been cleared.
     */
    public boolean checkCleared() {
        boolean clearedLevel = true;
        //unless every active squirrel on the board has dropped its nut the game is not cleared
        for (int i=0; i<4;i++)
            if (activeSquirrels[i] != null)
                if (activeSquirrels[i].getnutStatus()==true) {
                    clearedLevel = false;
                    return clearedLevel;
                }   
        return clearedLevel;
    }

    /**
     * If the game has been cleared, this function will return the user to the level selection menu
     * while hiding and subsequently destroying the current game board.
     */
    private void levelCleared() {
        //creates new instance of LevelSelect object
        LevelSelect lvsq = new LevelSelect();
        //hides and destroys current Background frame
        frame.setVisible(false);
        frame.dispose();
        //calls pop-up box function
        clearedBox();

    }

    /**
     * Called after destroying the current game board, this function will create a pop-up
     * message that informs the user that they have cleared the current level.
     */
    private void clearedBox() {
        JFrame clearedFrame = new JFrame();
        JOptionPane.showMessageDialog(clearedFrame,"Congratulations!\nlevel cleared!");
    }

    /**
     * Determines the response given when each of the buttons on the game board is pressed, 
     * each of the directional buttons will move the active squirrel, while selecting a
     * gridbutton that shows the head of a squirrel will make it into the active squirrel.
     */
    public void actionPerformed(ActionEvent e) {
        //if a squirrel has been selected
        if (this.squirrel != null) {
            //if a directional button has been pressed, changes the movement direction to the one
            //represented by the button before moving the active squirrel in that direction.
            if (e.getSource()==upArrow) {
                this.direction = "Up";
                moveSquirrel();
            }

            if (e.getSource()==downArrow) {
                this.direction = "Down";
                moveSquirrel();
            }

            if (e.getSource()==rightArrow) {
                this.direction = "Right";
                moveSquirrel();
            }

            if (e.getSource()==leftArrow) {
                this.direction = "Left";
                moveSquirrel();
            }
        }
        //if said squirrel exists on the board
        if (redSquirrel != null) {
            //if said squirrel's head is clicked on
            if (e.getSource()==gridButton[redSquirrel.getheadLocation()]) {
                //set it to be the current active squirrel
                this.squirrel=redSquirrel;  
            }
        }

        if (greySquirrel != null) {
            if (e.getSource()==gridButton[greySquirrel.getheadLocation()])  {
                this.squirrel=greySquirrel;
            }
        }

        if (blackSquirrel != null) {
            if (e.getSource()==gridButton[blackSquirrel.getheadLocation()]) 
                this.squirrel=blackSquirrel;
        }

        if (brownSquirrel != null) {
            if (e.getSource()==gridButton[brownSquirrel.getheadLocation()]) 
                this.squirrel=brownSquirrel;
        }
        

    }

    /**
     * Creates a list of all the active obstacles on the board, obstacles are given
     * specific indexes so that they may be referenced individually at other points.
     */
    public Integer[] getObstacles() {
        //hole-blocking flower always occupies index 0
        obstacles[0]=flowerLocation;
        //if there is a red squirrel
        if (redSquirrel != null) {
            //it's head and tail will always occupy indexes 1 and 2
            obstacles[1]=redSquirrel.getheadLocation();
            obstacles[2]=redSquirrel.gettailLocation(redSquirrel.getheadLocation(), redSquirrel.getSquirrelRotation());
        }
        //if there is a black squirrel
        if (blackSquirrel != null) {
            //it's head, tail and flower which makes it into an L shape will occupy indexes 3, 4 and 5
            obstacles[3]=blackSquirrel.getheadLocation();
            obstacles[4]=blackSquirrel.gettailLocation(blackSquirrel.getheadLocation(), blackSquirrel.getSquirrelRotation());
            obstacles[5]=blackSquirrel.getLFlowerLocation(blackSquirrel.getheadLocation());
        }
        if (brownSquirrel != null) {
            obstacles[6]=brownSquirrel.getheadLocation();
            obstacles[7]=brownSquirrel.gettailLocation(brownSquirrel.getheadLocation(), brownSquirrel.getSquirrelRotation());
            obstacles[8]=brownSquirrel.getLFlowerLocation(brownSquirrel.getheadLocation());
        }
        if (greySquirrel != null) {
            obstacles[9]=greySquirrel.getheadLocation();
            obstacles[10]=greySquirrel.gettailLocation(greySquirrel.getheadLocation(), greySquirrel.getSquirrelRotation());
        }
        return obstacles;
    }

    /**
     * Provides the array of gridbuttons to another function.
     */
    public JButton[] getgridButton() {
        return gridButton;
    }

    /**
     * Provides this object to the function to be used in other classes.
     */
    public Background getBackground() {
        return this;
    }

    /**
     * Returns an array of the filled holes on this game board.
     */
    public Integer[] getFilledHoles() {
        return filledHoles;
    }

    /** 
     * Getter function which returns the location of the hole-blocking flower
     */
    public int getflowerLocation() {
        return flowerLocation;
    }

    /**
     * Sets the clicked squirrel as the 'active' squirrel to be moved by the directional buttons.
     */
    public void setSquirrel(Squirrel squirrel) {
        this.squirrel=squirrel;
    }

    /**
     * A function which takes as a parameter the index of a gridbutton that has just had a nut
     * been slotted into its hole, This function will then add the location of that filled hole
     * to an array which stores what holes are filled and which squirrel filled that hole.
     * This prevents multiple squirrels dropping nuts into the same hole.
     */
    public void setFilledHoles(Integer filledHole) {
        //assume the hole has not been filled before
        boolean unique = true;
        //for each coloured squirrel
        if (squirrel.getColour()=="Red")
            for (int i=0;i<4;i++)
                //if any other squirrel has moved a nut into a hole
                if (filledHoles[i] != null)
                    //if this squirrel is trying to fill a hole that has already been filled
                    if (filledHoles[i]==filledHole)
                        //the hole is cannot be filled
                        unique = false;
            //if the hole can be filled
            if (unique) 
                //add the index of the location of the hole to an array that specifies which squirrel filled that hole
                filledHoles[0]=filledHole;

        if (squirrel.getColour()=="Grey")
            for (int i=0;i<4;i++)
                if (filledHoles[i] != null)
                    if (filledHoles[i]==filledHole)
                        unique = false;
            if (unique) 
                filledHoles[1]=filledHole;

        if (squirrel.getColour()=="Black")
            for (int i=0;i<4;i++)
                if (filledHoles[i] != null)
                    if (filledHoles[i]==filledHole)
                        unique = false;
            if (unique) 
                filledHoles[2]=filledHole;
        if (squirrel.getColour()=="Brown")
            for (int i=0;i<4;i++)
                if (filledHoles[i] != null)
                    if (filledHoles[i]==filledHole)
                        unique = false;
            if (unique) 
                filledHoles[3]=filledHole;
    }
}
