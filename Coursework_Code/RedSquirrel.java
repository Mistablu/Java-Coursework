package Coursework_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RedSquirrel implements ActionListener{
    private int headLocation;
    private int tailLocation;
    private int squirrelRotation;
    private JButton[] gridButton;
    private boolean nutStatus;
    private Background background;
    private boolean valid;

    public RedSquirrel(int headLocation,int squirrelRotation, Background bg) {
        this.headLocation=headLocation;
        this.squirrelRotation=squirrelRotation;
        this.background = bg;
        this.gridButton=background.getgridButton();
        moveSquirrel(true,null);
        gridButton[headLocation].addActionListener(this);
    }

    public int gettailLocation() {
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

    public void setBlank() {
        if (headLocation == 2 || headLocation==4|| headLocation==9 || headLocation==15)
            gridButton[headLocation].setIcon(new Picture("icons/Hole.png",squirrelRotation));  
        else
            gridButton[headLocation].setIcon(new Picture("icons/Empty.png",squirrelRotation));
        if (gettailLocation()==2 || gettailLocation()==4|| gettailLocation()==9 || gettailLocation()==15)
            gridButton[gettailLocation()].setIcon(new Picture("icons/Hole.png",squirrelRotation));    
        else
            gridButton[gettailLocation()].setIcon(new Picture("icons/Empty.png",squirrelRotation));
    }

    public void moveSquirrel(boolean nutStatus,String direction) {
        this.nutStatus=nutStatus;
        if (direction == null)
            move(nutStatus);

        if (direction == "Up") {
            validateUp();
            if (valid) {
                setBlank();
                headLocation-=4;
                move(nutStatus);
            }
        }

        if (direction == "Down") {
            validateDown();
            if (valid) {
                setBlank();
                headLocation+=4;
                move(nutStatus);
            }
        }

        if (direction == "Left") {
            validateLeft();
            if (valid) {
                setBlank();
                headLocation-=1;
                move(nutStatus);
            }
        }

        if (direction == "Right") {
            validateRight();
            if (valid) {
                setBlank();
                headLocation+=1;
                move(nutStatus);
            }
        }
    }
    private boolean validateLeft() {
        if ((headLocation-1) >-1 && (headLocation-1) <16) {

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

        valid = true;
        return valid; 
        }

        else    
            valid=false;
            return valid;
    }

    private boolean validateRight() {
        if ((headLocation+1) >-1 && (headLocation+1) <16) {

            if (squirrelRotation == 270) {
                if (headLocation+1 == 15 || headLocation+1 == 11 || headLocation+1 == 7 || headLocation+1 == 3) {
                    valid=false;
                    return valid;
                }
            }

            if (squirrelRotation == 90) {
                if (headLocation+1 == 12 || headLocation+1 == 8 || headLocation+1 == 4) {
                    valid=false;
                    return valid;
                }
            }

            if (squirrelRotation == 0 || squirrelRotation == 180 ) {
                if (headLocation+1 == 12 || headLocation+1 == 8 || headLocation+1 == 4) {
                    valid=false;  
                    return valid;
                }
            }

        valid = true;
        return valid; 
        }

        else    
            valid=false;
            return valid;

    }

    private boolean validateUp() {
        if ((headLocation-4) >-1 && (headLocation-4) <16) {
            if (squirrelRotation == 180 ) {
                if (headLocation-4 == 0 || headLocation-4 == 1 || headLocation-4 == 2 || headLocation-4 == 3) {
                    valid=false;  
                    return valid;
                }    
            }
            valid = true;
            return valid; 
        }

        else    
            valid=false;
            return valid;
    }

    private boolean validateDown() {
        if ((headLocation+4) >-1 && (headLocation+4) <16) {
            if (squirrelRotation == 0 ) {
                if (headLocation+4 == 15 || headLocation+4 == 14 || headLocation+4 == 13 || headLocation+4 == 12) {
                    valid=false;  
                    return valid;
                }    
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
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1Nut.png",squirrelRotation));
        else
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1.png",squirrelRotation));

         gridButton[gettailLocation()].setIcon(new Picture("icons/RedSquirrel2.png",squirrelRotation));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==gridButton[headLocation])
         {
             background.setRedSquirrel(this);
         }
    }
}
