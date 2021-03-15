package Coursework_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RedSquirrel {
    private int headLocation;
    private int tailLocation;
    private int squirrelRotation;
    private JButton[] gridButton;

    private JButton squirrelNut = new JButton(new Picture("icons/RedSquirrel1Nut.png",0));
    private JButton squirrelHead = new JButton(new Picture("icons/RedSquirrel1.png",0));
    private JButton squirrelTail = new JButton(new Picture("icons/RedSquirrel2.png",0));

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

    public void moveSquirrel(boolean nutStatus) {
        if (nutStatus == true)
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1Nut.png",squirrelRotation));
        else
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1.png",squirrelRotation));

         gridButton[gettailLocation()].setIcon(new Picture("icons/RedSquirrel2.png",squirrelRotation));
        
    }
}
