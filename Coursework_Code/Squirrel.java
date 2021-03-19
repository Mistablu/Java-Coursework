package Coursework_Code;
import javax.swing.*;

public class Squirrel {
    private int headLocation,tailLocation,squirrelRotation,operator;
    private JButton[] gridButton;
    private boolean nutStatus=true,valid;
    private Background background;
    private String squirrelHead,squirrelNutHead,squirrelTail,colour;
    private Integer[] obstacles,colourExceptions,filledHoles;

    public Squirrel(int headLocation, int squirrelRotation, Background bg, String colour) {
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
            for(int i=0;i<4;i++)
                if (filledHoles[i] != null)
                    if (headLocation==filledHoles[i])
                        gridButton[headLocation].setIcon(new Picture("icons/HoleNut.png",0));
        }  
        else
            gridButton[headLocation].setIcon(new Picture("icons/Empty.png",0));

        if (gettailLocation(headLocation,squirrelRotation)==2 || gettailLocation(headLocation,squirrelRotation)==4|| gettailLocation(headLocation,squirrelRotation)==9 || gettailLocation(headLocation,squirrelRotation)==15) {
            gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture("icons/Hole.png",0)); 
            for(int i=0;i<4;i++)
                if (filledHoles[i] != null) {
                    if (gettailLocation(headLocation, squirrelRotation)==filledHoles[i])
                        gridButton[gettailLocation(headLocation, squirrelRotation)].setIcon(new Picture("icons/HoleNut.png",0));
                }
        }  
        else
            gridButton[gettailLocation(headLocation,squirrelRotation)].setIcon(new Picture("icons/Empty.png",0));

        if (colour == "Black" || colour == "Brown")
            if (getLFlowerLocation(headLocation) == 2  || getLFlowerLocation(headLocation)==4|| getLFlowerLocation(headLocation)==9 || getLFlowerLocation(headLocation)==15) {
                gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/Hole.png",0));
                for(int i=0;i<4;i++)
                    if (filledHoles[i] != null)
                        if (getLFlowerLocation(headLocation)==filledHoles[i])
                            gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/HoleNut.png",0));
            }
            else
                gridButton[getLFlowerLocation(headLocation)].setIcon(new Picture("icons/Empty.png",0));
    }

    public void moveSquirrel(boolean nutStatus,String direction) {
        this.nutStatus=nutStatus;
        if (direction == null)
            move();

        if (direction == "Up") {
            operator=-4;
            validateDirection(operator);
            if (valid) {
                setBlank();
                headLocation-=4;
                move();
            }
        }

        if (direction == "Down") {
            operator=4;
            validateDirection(operator);
            if (valid) {
                setBlank();
                headLocation+=4;
                move();
            }
        }

        if (direction == "Left") {
            operator=-1;
            validateDirection(-1);
            if (valid) {
                setBlank();
                headLocation-=1;
                move();
            }
        }

        if (direction == "Right") {
            operator=1;
            validateDirection(operator);
            if (valid) {
                setBlank();
                headLocation+=1;
                move();
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

    private void checkNutStatus() {
        filledHoles = background.getFilledHoles();
        if (headLocation+operator==2 || headLocation+operator==4 || headLocation+operator==9 || headLocation+operator==15) {
            nutStatus=false;
        
        for (int i=0;i<4;i++) {
            if (filledHoles[i] != null)
                if (headLocation+operator==filledHoles[i])
                    nutStatus = true;
        if (headLocation+operator==background.getObstacles()[0])
            nutStatus=true;
        }
            background.setFilledHoles(headLocation+operator);
        }
    }

    private boolean validateDirection(int operator) {
        if (nutStatus)
            checkNutStatus();

        obstacles = background.getObstacles();
        obstacles = colourExceptions();
        if ((headLocation+operator) >-1 && (headLocation+operator) <16) {
            if (colour=="Black" || colour == "Brown") {
                if (getLFlowerLocation(headLocation+operator)<0 || getLFlowerLocation(headLocation+operator)>15) {
                    valid = false;
                    return valid;
                }
                for (int i=0; i<11;i++) 
                    if (obstacles[i] != null)
                        if (getLFlowerLocation(headLocation+operator)==obstacles[i]) {
                            valid=false;
                            return valid;
                        }

                if (operator==-1)
                    if (squirrelRotation==180)
                        if (getLFlowerLocation(headLocation+operator)==3 || getLFlowerLocation(headLocation+operator)==7 || getLFlowerLocation(headLocation+operator)==11 || getLFlowerLocation(headLocation+operator)==15) {
                            valid=false;
                            return valid;
                        }

                if (operator == 1)
                    if (squirrelRotation==0)
                        if (getLFlowerLocation(headLocation+operator)==0 || getLFlowerLocation(headLocation+operator)==4 || getLFlowerLocation(headLocation+operator)==8 || getLFlowerLocation(headLocation+operator)==12) {
                        valid=false;
                        return valid;
                        }
            }

            if (operator == 1 || operator == -1)
                if (squirrelRotation == 270) 
                    if (headLocation+operator == 15 || headLocation+operator == 11 || headLocation+operator == 7 || headLocation+operator == 3) {
                        valid=false;
                        return valid;
                    }
            if (operator == 1 || operator == -1)
                if (squirrelRotation == 90) 
                    if (headLocation+operator == 12 || headLocation+operator == 8 || headLocation+operator == 4) {
                        valid=false;  
                        return valid;
                    }
            if (operator == 4)
                if (squirrelRotation == 0 ) 
                    if (headLocation+operator == 15 || headLocation+operator == 14 || headLocation+operator == 13 || headLocation+operator == 12) {
                        valid=false;  
                        return valid;
                    }
            if (operator == 1)
                if (squirrelRotation == 0 || squirrelRotation == 180 ) 
                    if (headLocation+operator == 12 || headLocation+operator == 8 || headLocation+operator == 4) {
                        valid=false;  
                        return valid;
                    }
            if (operator == -1)
                if (squirrelRotation == 0 || squirrelRotation == 180 ) 
                    if (headLocation+operator == 11 || headLocation+operator == 7 || headLocation+operator == 3) {
                        valid=false;  
                        return valid;
                    }
            if (operator == -4)
                if (squirrelRotation == 180 ) 
                    if (headLocation+operator == 0 || headLocation+operator == 1 || headLocation+operator == 2 || headLocation+operator == 3) {
                        valid=false;  
                        return valid;
                    }   
 
            for (int i=0; i<11;i++) 
                if (obstacles[i] != null) 
                    if (headLocation+operator == obstacles[i] || gettailLocation((headLocation+operator),squirrelRotation)==obstacles[i]) {
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

    private void move() {
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
