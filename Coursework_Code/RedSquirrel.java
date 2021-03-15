package Coursework_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RedSquirrel {
    private int headLocation;
    private int tailLocation;
    private int squirrelRotation;
    private JButton[] gridButton;
    private boolean nutStatus;


    public RedSquirrel(int headLocation,int squirrelRotation, JButton[] gridButton) {
        this.headLocation=headLocation;
        this.squirrelRotation=squirrelRotation;
        this.gridButton = gridButton;
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

    public void moveSquirrel(boolean nutStatus,String direction) {
        this.nutStatus=nutStatus;
        if (direction == null)
            move(nutStatus);

        if (direction == "Up") {
            headLocation+=-4;
            move(nutStatus);
        }

        if (direction == "Down") {
            headLocation+=4;
            move(nutStatus);
        }

        if (direction == "Left") {
            headLocation+=-1;
            move(nutStatus);
        }

        if (direction == "Right") {
            headLocation+=1;
            move(nutStatus);
        }
    }

    private void move(boolean nutStatus) {
        if (nutStatus == true)
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1Nut.png",squirrelRotation));
        else
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1.png",squirrelRotation));

         gridButton[gettailLocation()].setIcon(new Picture("icons/RedSquirrel2.png",squirrelRotation));
           
    }
}
