import javax.swing.*;
/**
 * This class represents a Squirrel object that users may interact with in the game.
 * When a new instance of this object is created, it needs to be added to the game board
 * before it can be interacted with.
 */
public class Squirrel {
    private int headLocation,tailLocation; //Integer values storing the index of the button which is occupied by the head and tail of the squirrel.
    private int squirrelRotation; //Integer value which stores the orientation of the squirrel.
    private int operator;   //The arithmetic action to be applied to the squirrel indeces when it is moved in the specified direction.
    private JButton[] gridButton;   //The array of gridbuttons retrieved from the Background class.
    private boolean nutStatus=true; //Boolean which tracks whether the squirrel is currently holding a nut.
    private boolean valid;  //Represents the validity of the squirrel being moved in the specified direction.
    private Background background; // The Background object which the squirrel resides on.
    private String squirrelHead,squirrelNutHead,squirrelTail; //Strings which hold the file paths of the images of the squirrels' head or tail. 
    private String colour; //The colour of the squirrel as defined in the constructor.
    private Integer[] obstacles; //Array of indexes of gridbuttons which determine the areas on the board which cannot be accessed by a squirrel
    private Integer[] colourExceptions; //A modification of the obstacles array which allows each squirrel to prevent its own position from being treated as an obstacle.
    private Integer[] filledHoles;  //The indexes of the gridbuttons that specifies which holes have been filled by what coloured squirrels.
    
    /**
     * Constructor. Creates a new instance of the squirrel class based off the gives attributes.
     * @param headLocation Defines the initial location of the squirrels' head.
     * @param squirrelRotation Defines the permanent orientation of the squirrel in degrees.
     * @param background the parameter which defines the Background instance which the squirrel has been placed on.
     * @param colour the permanent colour of the Squirrel.
     */   
    public Squirrel(int headLocation, int squirrelRotation, Background background, String colour) {

        colourExceptions = new Integer[11];
        obstacles = new Integer[11];
        //store constructor variables as private instance variables
        this.headLocation=headLocation;
        this.squirrelRotation=squirrelRotation;
        this.background = background;
        this.colour=colour;
        //retrieve the array of gridbuttons from the current Background instance
        gridButton=background.getgridButton();
        //changes the colour of the squirrel to the one specified by the constructor
        changeColour();
        //changes the squirrels' initial position to the one specified by the constructor
        moveSquirrel(null);
    }
    /**
     * Function which is called immediately after the Squirrel object is constructed which sets the colour of the images that represent
     * the squirrel into the correct colour as constructed. 
     */
    private void changeColour() {
        //if the specified colour is red
        if (colour == "Red") {
            //change all icons to represent a red squirrel
            squirrelHead = "icons/RedSquirrel1.png";
            squirrelNutHead = "icons/RedSquirrel1Nut.png";
            squirrelTail = "icons/RedSquirrel2.png";
        }
        //if the specified colour is black
        if (colour == "Black") {
            //change all icons to represent a black squirrel
            squirrelHead = "icons/BlackSquirrel1.png";
            squirrelNutHead = "icons/BlackSquirrel1Nut.png";
            squirrelTail = "icons/BlackSquirrel2.png"; 
        }
        //if the specified colour is brown
        if (colour == "Brown") {
            //change all icons to represent a brown squirrel
            squirrelHead = "icons/BrownSquirrel1.png";
            squirrelNutHead = "icons/BrownSquirrel1Nut.png";
            squirrelTail = "icons/BrownSquirrel2.png";  
        }
        //if the specified colour is grey
        if (colour == "Grey") {
            //change all icons to represent a grey squirrel
            squirrelHead = "icons/GreySquirrel1.png";
            squirrelNutHead = "icons/GreySquirrel1Nut.png";
            squirrelTail = "icons/GreySquirrel2.png";
        }
    }

    /**
     * Changes the Icon of the gributton which is currently occupied by the parts of the squirrel.
     * this function is called immediately before incrementing the current location of the squirrel so that the background changes
     * behind it, giving the illusion that the squirrel itself is moving.
     */
    public void setBlank() {
        //if the head is currently on a hole
        if (headLocation == 2 || headLocation==4|| headLocation==9 || headLocation==15) {
            //set the current locations icon to a hole
            gridButton[headLocation].setIcon(new Picture("icons/Hole.png",0)); 
            for(int i=0;i<4;i++)
                //if that hole is stored in the array of filled holes
                if (filledHoles[i] != null)
                    if (headLocation==filledHoles[i])
                        //set the current locations icon to a filled hole
                        gridButton[headLocation].setIcon(new Picture("icons/HoleNut.png",0));
        }  
        //otherwise set the icon to an empty one
        else
            gridButton[headLocation].setIcon(new Picture("icons/Empty.png",0));
        //if tail is currently on a hole
        if (gettailLocation(headLocation,squirrelRotation)==2 || gettailLocation(headLocation,squirrelRotation)==4|| gettailLocation(headLocation,squirrelRotation)==9 || gettailLocation(headLocation,squirrelRotation)==15) {
            //set the tail's icon to a hole
            gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture("icons/Hole.png",0)); 
            for(int i=0;i<4;i++)
                //if that hole is stored in the array of filled holes
                if (filledHoles[i] != null) {
                    if (gettailLocation(headLocation, squirrelRotation)==filledHoles[i])
                        //set the current locations icon to a filled hole
                        gridButton[gettailLocation(headLocation, squirrelRotation)].setIcon(new Picture("icons/HoleNut.png",0));
                }
        }  
        else
            //otherwise set the icon to an empty one
            gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture("icons/Empty.png",0));
        //applies only to black or brown squirrels
        if (colour == "Black" || colour == "Brown")
            //if flower is currently on a hole
            if (getLFlowerLocation(headLocation) == 2  || getLFlowerLocation(headLocation)==4|| getLFlowerLocation(headLocation)==9 || getLFlowerLocation(headLocation)==15) {
                //set the flower's icon to a hole
                gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/Hole.png",0));
                for(int i=0;i<4;i++)
                    //if that hole is stored in the array of filled holes
                    if (filledHoles[i] != null)
                        if (getLFlowerLocation(headLocation)==filledHoles[i])
                        //set the current locations icon to a filled hole
                            gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/HoleNut.png",0));
            }
            else
                //otherwise set the icon to an empty one
                gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/Empty.png",0));
    }

    /**
     * Handles the movement of the squirrel in each direction as specified by user.
     * this function takes the direction to be moved as a parameter to call functions which allow the squirrel
     * to move in that direction.
     * @param direction Value provided by the ActionListener on the directional buttons in the Background class.
     */
    public void moveSquirrel(String direction) {
        //if you are initialising the position of a squirrel then no checks are required
        if (direction == null)
            move();

        if (direction == "Up") {
            //passes '-4' as a parameter which is the equivalent of going up by 1 square
            operator=-4;
            //ensures the movement is legal
            if (validateDirection(operator)) {
                //changes icon of current tile before moving the squirrel
                setBlank();
                headLocation-=4;
                if (nutStatus)
                    this.nutStatus=checkNutStatus();
                move();
            }
        }

        if (direction == "Down") {
            //passes '4' as a parameter which is the equivalent of going down by 1 square
            operator=4;
            //ensures the movement is legal
            if (validateDirection(operator)) {
                //changes icon of current tile before moving the squirrel
                setBlank();
                headLocation+=4;
                if (nutStatus)
                    this.nutStatus=checkNutStatus();
                move();
            }
        }

        if (direction == "Left") {
            //passes '-1' as a parameter which is the equivalent of going left by 1 square
            operator=-1;
            //ensures the movement is legal
            if (validateDirection(operator)) {
                //changes icon of current tile before moving the squirrel
                setBlank();
                headLocation-=1;
                if (nutStatus)
                    this.nutStatus=checkNutStatus();
                move();
            }
        }

        if (direction == "Right") {
            //passes '1' as a parameter which is the equivalent of going right by 1 square
            operator=1;
            //ensures the movement is legal
            if (validateDirection(operator)) {
                //changes icon of current tile before moving the squirrel
                setBlank();
                headLocation+=1;
                if (nutStatus)
                    this.nutStatus=checkNutStatus();
                move();
            }
        }
    }

    /**
     * 
     * Takes the obstacles array and removes the current location of this squirrel from the list of obstacles. This is to prevent the Squirrels'
     * own head/tail or flower from colliding with itself when attempting to move. the parts of the array to be excluded are defined by the colour
     * of the squirrel when it was constructed.
     */
    private Integer[] colourExceptions() {
        colourExceptions = background.getObstacles();
        if (colour=="Red") {
            //ignore indexes 1 & 2
            colourExceptions[1]=null;
            colourExceptions[2]=null;
        }
        if (colour=="Black") {
            //ignore indexes 3, 4 & 5
            colourExceptions[3]=null;
            colourExceptions[4]=null;
            colourExceptions[5]=null;
        }
        if (colour=="Brown") {
            //ignore indexes 6, 7 & 8
            colourExceptions[6]=null;
            colourExceptions[7]=null;
            colourExceptions[8]=null;
        }
        if (colour=="Grey") {
            //ignore indexes 9 & 10
            colourExceptions[9]=null;
            colourExceptions[10]=null;
        }
        //return the obstacles list after removing the ignored values
        return colourExceptions;

    }

    /**
     * Modifies the nutStatus of the squirrel whenever it goes over a hole. The function first removes the nut from the squirrel before
     * re-adding it, only if it discovers that a nut was already dropped into the same hole prevoisly. 
     */
    private boolean checkNutStatus() {
        boolean nutStatus=true;
        //grabs list of obstacles from Background object
        obstacles = background.getObstacles();
        //updates the list to exclude the current location of this squirrel as an obstacle
        obstacles = colourExceptions();
        //gets list of filled holes from Background class
        filledHoles = background.getFilledHoles();
        //if the head lands on a hole
        if (headLocation==2 || headLocation==4 || headLocation==9 || headLocation==15) { 
            //drop the nut 
            for (int i=0;i<4;i++) {
                if (filledHoles[i] != null) {
                    //if the hole was already full
                    if (headLocation==filledHoles[i]) {
                        //pick the nut back up
                        nutStatus = true;
                        return nutStatus;
                    }
                }
            }
            //stops you from dropping nuts into holes blocked by any obstacle
            for (int j=0; j<11;j++) {
                if (obstacles[j] != null) 
                    if (headLocation == obstacles[j] || gettailLocation(headLocation, squirrelRotation)==obstacles[j] || getLFlowerLocation(headLocation)==obstacles[j]) {
                        nutStatus=true; 
                        return nutStatus;
                    }
            }
        nutStatus=false;
                 
        }
        //if the hole has been filled then add its location to the array of filled holes stored in the Background object
        if (nutStatus==false) {
            background.setFilledHoles(headLocation);
            System.out.println("apprantly the hole at "+headLocation+" is filled lol");

        }
        return nutStatus;
    }

    /**
     * An exhuastive function that checks whether or not the squirrel is able to move in a specified direction based on its rotation,
     * its current location and whether or not there are any obstacles in the way. 
     * @param operator  the amount that must be added to the index of the gridbutton to move the squirrel in the specified direction
     */
    private boolean validateDirection(int operator) {
        //grabs list of obstacles from Background object
        obstacles = background.getObstacles();
        //updates the list to exclude the current location of this squirrel as an obstacle
        obstacles = colourExceptions();
        //if the squirrel is holding a nut
        if (nutStatus)
            //check to see if it should drop it or not
            checkNutStatus();
        //if the new headlocation is within the bounds of the grid
        if ((headLocation+operator) >-1 && (headLocation+operator) <16) {
            //for black and brown squirrels only
            if (colour=="Black" || colour == "Brown") {
                //if the L-shaped flower is outside the bounds of the grid
                if (getLFlowerLocation(headLocation+operator)<0 || getLFlowerLocation(headLocation+operator)>15) {
                    //reject movement
                    valid = false;
                    return valid;
                }
                //if the L-shaped flower collides with an obstacle 
                for (int i=0; i<11;i++) 
                    if (obstacles[i] != null)
                        if (getLFlowerLocation(headLocation+operator)==obstacles[i]) {
                            //reject movement
                            valid=false;
                            return valid;
                        }
                //prevents the L-shaped flower from moving so far left that it shows up on the other side of the grid
                if (operator==-1)
                    if (squirrelRotation==180)
                        if (getLFlowerLocation(headLocation+operator)==3 || getLFlowerLocation(headLocation+operator)==7 || getLFlowerLocation(headLocation+operator)==11 || getLFlowerLocation(headLocation+operator)==15) {
                            //reject movement
                            valid=false;
                            return valid;
                        }
                //prevents the L-shaped flower from moving so far right that it shows up on the other side of the grid
                if (operator == 1)
                    if (squirrelRotation==0)
                        if (getLFlowerLocation(headLocation+operator)==0 || getLFlowerLocation(headLocation+operator)==4 || getLFlowerLocation(headLocation+operator)==8 || getLFlowerLocation(headLocation+operator)==12) {
                            //reject movement
                            valid=false;
                            return valid;
                        }
            }
            //prevents head/tail from moving so far left or right that it shows up on the right hand side of the grid
            if (operator == 1 || operator == -1)
                if (squirrelRotation == 270) 
                    if (headLocation+operator == 15 || headLocation+operator == 11 || headLocation+operator == 7 || headLocation+operator == 3) {
                        //reject movement
                        valid=false;
                        return valid;
                    }
            //prevents tail from moving so far left or right that it shows up on the left hand side of the grid
            if (operator == 1 || operator == -1)
                if (squirrelRotation == 90) 
                    if (headLocation+operator == 12 || headLocation+operator == 8 || headLocation+operator == 4) {
                        //reject movement
                        valid=false;  
                        return valid;
                    }
            //prevents tail from going so low that it falls outside of the bounds of the grid
            if (operator == 4)
                if (squirrelRotation == 0 ) 
                    if (headLocation+operator == 15 || headLocation+operator == 14 || headLocation+operator == 13 || headLocation+operator == 12) {
                        //reject movement
                        valid=false;  
                        return valid;
                    }
            //prevents head/tail going so far right that it ends up on the left hand side of the grid
            if (operator == 1)
                if (squirrelRotation == 0 || squirrelRotation == 180 ) 
                    if (headLocation+operator == 12 || headLocation+operator == 8 || headLocation+operator == 4) {
                        //reject movement
                        valid=false;  
                        return valid;
                    }
            //prevents head/tail from moving so far left that it shows up on the right hand side of the grid
            if (operator == -1)
                if (squirrelRotation == 0 || squirrelRotation == 180 ) 
                    if (headLocation+operator == 11 || headLocation+operator == 7 || headLocation+operator == 3) {
                        //reject movement
                        valid=false;  
                        return valid;
                    }
            //prevents tail from going too high and moving out of the grid
            if (operator == -4)
                if (squirrelRotation == 180 ) 
                    if (headLocation+operator == 0 || headLocation+operator == 1 || headLocation+operator == 2 || headLocation+operator == 3) {
                        //reject movement
                        valid=false;  
                        return valid;
                    }   
 
            for (int i=0; i<11;i++) 
                if (obstacles[i] != null) 
                    //if the head or tail of the squirrel is colliding with an object
                    if (headLocation+operator == obstacles[i] || gettailLocation((headLocation+operator),squirrelRotation)==obstacles[i]) {
                        //reject movement
                        valid=false;
                        return valid;
                        }
        valid = true;
        return valid; 
        }

        else    
            valid=false;
            return valid;
    }

    /**
     * Updates the icons on the grid button array to the new values that the squirrel is being moved to in order to emulate
     * the movement of the squirrel.
     */
    private void move() {
        //if the squirrel is holding a nut
        if (nutStatus == true)
            //make it look like it's holding a nut
            gridButton[headLocation].setIcon(new Picture(squirrelNutHead,squirrelRotation));
        //if the squirrel is not holding a nut
        else
            //stop making it carry a nut
            gridButton[headLocation].setIcon(new Picture(squirrelHead,squirrelRotation));
        //makes the tail follow the head
        gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture(squirrelTail,squirrelRotation));
        //only applies to black or brown squirrels
        if (colour == "Black" || colour == "Brown")
            //makes the L-shaped flower follow the head
            gridButton[this.getLFlowerLocation(headLocation)].setIcon(new Picture("icons/SquirrelFlower.png",squirrelRotation));    
    }

    /**
     * Returns whether or not his squirrel is currently holding a nut.
     */
    public boolean getnutStatus() {
        return this.nutStatus;
    }

    /**
     * Returns the colour of this squirrel.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Returns the index in the array of grid buttons which points to the head of this squirrel.
     */
    public int getheadLocation() {
        return headLocation;
    }

    /**
     * Returns the rotation in degrees of this squirrel as it was constructed.
     */
    public int getSquirrelRotation() {
        return squirrelRotation;
    }

    /**
     * Calculates the index of the location of the tail of a squirrel, taking the position of the head of the squirrel 
     * and the rotation of the squirrel as parameters.
     * @param headLocation the index of the location of the head
     * @param squirrelRotation the rotation of the Squirrel
     * @return the index of the location of the tail based on the head location and rotation
     */
    public int gettailLocation(int headLocation, int squirrelRotation) {
        //if squirrel facing upright
        if (squirrelRotation == 0)
                //place tail below squirrel
                tailLocation = headLocation+4;
        //if squirrel facing right
        if (squirrelRotation == 90)
                //put the tail on it's left
                tailLocation = headLocation+-1;
        //if squirrel facing down
        if (squirrelRotation == 180)
                //put the tail above it
                tailLocation = headLocation-4;
        //if squirrel facing left
        if (squirrelRotation == 270)
                //put the tail on it's right
                tailLocation = headLocation+1;
    
        return tailLocation;
    }

    /**
     * Calculates, for the Black and Brown squirrels which are both L shaped, the index of the location of their added flowers
     * using the location of the head of said squirrel as a parameter.
     * @param headLocation the index of the location of the head
     * @return the index of the location of the additional flower based on the head location and rotation
     */
    public int getLFlowerLocation(int headLocation) {
        int lFlowerLocation=16;
        if (colour=="Black") {
        //constantly place the L-Shaped flower to the right of the squirrel's tail
            if (squirrelRotation == 0)
                lFlowerLocation = (gettailLocation(headLocation, squirrelRotation)+1);

            if (squirrelRotation == 90)
                lFlowerLocation = (gettailLocation(headLocation, squirrelRotation)+4);

            if (squirrelRotation == 180)
                lFlowerLocation = (gettailLocation(headLocation, squirrelRotation)-1);

            if (squirrelRotation == 270)
                lFlowerLocation = (gettailLocation(headLocation, squirrelRotation)-4);
        }
        if (colour=="Brown") {
        //constantly place the L-Shaped flower to the right of the squirrel's head
            if (squirrelRotation == 0)
                lFlowerLocation = headLocation+1;
            
            if (squirrelRotation == 90)
                lFlowerLocation = headLocation+4;
            
            if (squirrelRotation == 180)
                lFlowerLocation = headLocation-1;
            
            if (squirrelRotation == 270)
                lFlowerLocation = headLocation-4; 
        }
        return lFlowerLocation;
    }

}
