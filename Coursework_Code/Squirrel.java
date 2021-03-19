package Coursework_Code;
import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;

public class Squirrel {
    private int headLocation,tailLocation,squirrelRotation,filledHole;
    private JButton[] gridButton;
    private boolean nutStatus=true,valid;
    private Background background;
    private String squirrelHead,squirrelNutHead,squirrelTail,colour;
//    private int[] allHeadLocations;
    private Integer[] obstacles,colourExceptions;

    public Squirrel(int headLocation, int squirrelRotation, Background bg, String colour) {
        this.nutStatus=true;
        colourExceptions = new Integer[12];
//        allHeadLocations = new int[4];
        this.headLocation=headLocation;
        this.squirrelRotation=squirrelRotation;
        this.background = bg;
        this.gridButton=background.getgridButton();
        this.colour=colour;
        changeColour();
        moveSquirrel(true,null);
    }

    private void changeColour() {
        if (colour == "Red") {
            squirrelHead = "icons/RedSquirrel1.png";
            squirrelNutHead = "icons/RedSquirrel1Nut.png";
            squirrelTail = "icons/RedSquirrel2.png";
        }
        if (colour == "Black") {
            squirrelHead = "icons/BlackSquirrel1.png";
            squirrelNutHead = "icons/BlackSquirrel1Nut.png";
            squirrelTail = "icons/BlackSquirrel2.png"; 
        }
        if (colour == "Brown") {
            squirrelHead = "icons/BrownSquirrel1.png";
            squirrelNutHead = "icons/BrownSquirrel1Nut.png";
            squirrelTail = "icons/BrownSquirrel2.png";  
        }
        if (colour == "Grey") {
            squirrelHead = "icons/GreySquirrel1.png";
            squirrelNutHead = "icons/GreySquirrel1Nut.png";
            squirrelTail = "icons/GreySquirrel2.png";
        }
    }

    public String getColour() {
        return colour;
    }

    public int getheadLocation() {
        return headLocation;
    }

    public int getSquirrelRotation() {
        return squirrelRotation;
    }

    public int gettailLocation(int headLocation, int squirrelRotation) {
        if (squirrelRotation == 0)
                tailLocation = headLocation+4;

       if (squirrelRotation == 90)
                tailLocation = headLocation+-1;

       if (squirrelRotation == 180)
                tailLocation = headLocation-4;

       if (squirrelRotation == 270)
                tailLocation = headLocation+1;
    
        return tailLocation;
    }

    public int getLFlowerLocation(int headLocation) {
        int lFlowerLocation=16;
        if (colour=="Black") {
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

    public void setBlank() {
        if (headLocation == 2 || headLocation==4|| headLocation==9 || headLocation==15) {
            gridButton[headLocation].setIcon(new Picture("icons/Hole.png",0)); 
            if (headLocation==filledHole)
                gridButton[headLocation].setIcon(new Picture("icons/HoleNut.png",0));
        }  
        else
            gridButton[headLocation].setIcon(new Picture("icons/Empty.png",0));

        if (gettailLocation(headLocation,squirrelRotation)==2 || gettailLocation(headLocation,squirrelRotation)==4|| gettailLocation(headLocation,squirrelRotation)==9 || gettailLocation(headLocation,squirrelRotation)==15) {
            gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture("icons/Hole.png",0)); 
            if (gettailLocation(headLocation,squirrelRotation)==filledHole)
                gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture("icons/HoleNut.png",0));  
        }  
        else
            gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture("icons/Empty.png",0));
        if (colour == "Black" || colour == "Brown")
            if (getLFlowerLocation(headLocation) == 2  || getLFlowerLocation(headLocation)==4|| getLFlowerLocation(headLocation)==9 || getLFlowerLocation(headLocation)==15) {
                gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/Hole.png",0));
                if (getLFlowerLocation(headLocation)==filledHole)
                    gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/HoleNut.png",0));
            }
            else
                gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/Empty.png",0));
        



    }

    public void moveSquirrel(boolean nutStatus,String direction) {
        this.nutStatus=nutStatus;
        if (direction == null)
            move(nutStatus);

        if (direction == "Up") {
            validateUp();
            if (valid) {
                removeNut();
                setBlank();
                headLocation-=4;
                move(nutStatus);
            }
        }

        if (direction == "Down") {
            validateDown();
            if (valid) {
                removeNut();
                setBlank();
                headLocation+=4;
                move(nutStatus);
            }
        }

        if (direction == "Left") {
            validateLeft();
            if (valid) {
                removeNut();
                setBlank();
                headLocation-=1;
                move(nutStatus);
            }
        }

        if (direction == "Right") {
            validateRight();
            if (valid) {
                removeNut();
                setBlank();
                headLocation+=1;
                move(nutStatus);
            }
        }
    }

    private void removeNut() {
        if (this.nutStatus==true) {
            if (this.headLocation == 2 || this.headLocation==4|| this.headLocation==9 || this.headLocation==15) {
                this.nutStatus=false;
                filledHole = this.headLocation;
            }
        }
    }

    private Integer[] colourExceptions() {
        colourExceptions = obstacles;
        if (colour=="Red") {
            colourExceptions[1]=null;
            colourExceptions[2]=null;
        }
        if (colour=="Black") {
            colourExceptions[3]=null;
            colourExceptions[4]=null;
            colourExceptions[5]=null;
        }
        if (colour=="Brown") {
            colourExceptions[6]=null;
            colourExceptions[7]=null;
            colourExceptions[8]=null;
        }
        if (colour=="Grey") {
            colourExceptions[9]=null;
            colourExceptions[10]=null;
        }
        return colourExceptions;

    }

    private boolean validateLeft() {
        this.obstacles = background.getObstacles();
        this.obstacles = colourExceptions();
        if ((headLocation-1) >-1 && (headLocation-1) <16) {

            if (colour=="Black" || colour == "Brown") {
                if (getLFlowerLocation(headLocation-1)<0 || getLFlowerLocation(headLocation-1)>15) {
                    valid = false;
                    return valid;
                }
                for (int i=0; i<11;i++) 
                    if (obstacles[i] != null)
                        if (getLFlowerLocation(headLocation-1)==obstacles[i]) {
                            valid=false;
                            return valid;
                        }
                if (squirrelRotation==180)
                    if (getLFlowerLocation(headLocation-1)==3 || getLFlowerLocation(headLocation-1)==7 || getLFlowerLocation(headLocation-1)==11 || getLFlowerLocation(headLocation-1)==15) {
                        valid=false;
                        return valid;
                    }
            }

            if (squirrelRotation == 270) {
                if (headLocation-1 == 15 || headLocation-1 == 11 || headLocation-1 == 7 || headLocation-1 == 3) {
                    valid=false;
                    return valid;
                }

            }

            if (squirrelRotation == 90) {
                if (headLocation-1 == 12 || headLocation-1 == 8 || headLocation-1 == 4) {
                    valid=false;  
                    return valid;
                }
            }

            if (squirrelRotation == 0 || squirrelRotation == 180 ) {
                if (headLocation-1 == 11 || headLocation-1 == 7 || headLocation-1 == 3) {
                    valid=false;  
                    return valid;
                }
            }
 
            for (int i=0; i<11;i++) 
                if (obstacles[i] != null) 
                    if (headLocation-1 == obstacles[i] || gettailLocation((headLocation-1),squirrelRotation)==obstacles[i]) {
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

    private boolean validateRight() {
        this.obstacles = background.getObstacles();
        this.obstacles = colourExceptions();
        if ((headLocation+1) >-1 && (headLocation+1) <16) {

            if (colour=="Black" || colour == "Brown") {
                if (getLFlowerLocation(headLocation+1)<0 || getLFlowerLocation(headLocation+1)>15) {
                    valid = false;
                    return valid;
                }
                for (int i=0; i<11;i++) 
                    if (obstacles[i] != null)
                        if (getLFlowerLocation(headLocation+1)==obstacles[i]) {
                            valid=false;
                            return valid;
                        }

                if (squirrelRotation==0)
                    if (getLFlowerLocation(headLocation+1)==0 || getLFlowerLocation(headLocation+1)==4 || getLFlowerLocation(headLocation+1)==8 || getLFlowerLocation(headLocation+1)==12) {
                        valid=false;
                        return valid;
                    }
            }

            if (squirrelRotation == 270) 
                if (headLocation+1 == 15 || headLocation+1 == 11 || headLocation+1 == 7 || headLocation+1 == 3) {
                    valid=false;
                    return valid;
                }

            if (squirrelRotation == 90) 
                if (headLocation+1 == 12 || headLocation+1 == 8 || headLocation+1 == 4) {
                    valid=false;
                    return valid;
                }

            if (squirrelRotation == 0 || squirrelRotation == 180 ) 
                if (headLocation+1 == 12 || headLocation+1 == 8 || headLocation+1 == 4) {
                    valid=false;  
                    return valid;
                }

            for (int i=0; i<11;i++) 
                if (obstacles[i] != null) 
                    if (headLocation+1 == obstacles[i] || this.gettailLocation(headLocation+1,squirrelRotation)==obstacles[i]) {
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

    private boolean validateUp() {
        this.obstacles = background.getObstacles();
        this.obstacles = colourExceptions();
        if ((headLocation-4) >-1 && (headLocation-4) <16) {

            if (colour=="Black" || colour == "Brown") {
                if (getLFlowerLocation(headLocation-4)<0 || getLFlowerLocation(headLocation-4)>15) {
                    valid = false;
                    return valid;
                }
                for (int i=0; i<11;i++) 
                    if (obstacles[i] != null)
                        if (getLFlowerLocation(headLocation-4)==obstacles[i]) {
                            valid=false;
                            return valid;
                        }
            }

            if (squirrelRotation == 180 ) 
                if (headLocation-4 == 0 || headLocation-4 == 1 || headLocation-4 == 2 || headLocation-4 == 3) {
                    valid=false;  
                    return valid;
                }   
            
            for (int i=0; i<11;i++) 
                if (obstacles[i] != null) 
                    if (headLocation-4 == obstacles[i] || this.gettailLocation(headLocation-4,squirrelRotation)==obstacles[i]) {
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

    private boolean validateDown() {
        this.obstacles = background.getObstacles();
        this.obstacles = colourExceptions();
        if ((headLocation+4) >-1 && (headLocation+4) <16) {

            if (colour == "Black" || colour == "Brown") {
                if (getLFlowerLocation(headLocation+4)<0 || getLFlowerLocation(headLocation+4)>15) {
                    valid = false;
                    return valid;
                }
                for (int i=0; i<11;i++) 
                    if (obstacles[i] != null)
                        if (getLFlowerLocation(headLocation+4)==obstacles[i]) {
                            valid=false;
                            return valid;
                        }
            }

            if (squirrelRotation == 0 ) 
                if (headLocation+4 == 15 || headLocation+4 == 14 || headLocation+4 == 13 || headLocation+4 == 12) {
                    valid=false;  
                    return valid;
                }    

            for (int i=0; i<11;i++)
                if (obstacles[i] != null)
                    if (headLocation+4 == obstacles[i] || this.gettailLocation(headLocation+4,squirrelRotation)==obstacles[i]) {
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

    private void move(boolean nutStatus) {
        if (nutStatus == true)
            gridButton[headLocation].setIcon(new Picture(squirrelNutHead,squirrelRotation));
        else
            gridButton[headLocation].setIcon(new Picture(squirrelHead,squirrelRotation));

         gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture(squirrelTail,squirrelRotation));
        if (colour == "Black" || colour == "Brown")
            gridButton[this.getLFlowerLocation(headLocation)].setIcon(new Picture("icons/SquirrelFlower.png",squirrelRotation));    
    }

    public boolean getnutStatus() {
        return this.nutStatus;
    }
}
