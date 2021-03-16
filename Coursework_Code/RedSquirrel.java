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
            setBlank();
            if ((headLocation-4) >=0 && (headLocation-4) <=15) {
                headLocation-=4;
                move(nutStatus);
            }
        }

        if (direction == "Down") {
            setBlank();
            if ((headLocation+4) >=0 && (headLocation+4) <=15) {
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
            setBlank();
            if ((headLocation+1) >=0 && (headLocation+1) <=15) {
                headLocation+=1;
                move(nutStatus);
            }
        }
    }
    private boolean validateLeft() {
        if ((headLocation-1) >-1 && (headLocation-1) <16) {
            if (squirrelRotation == 270) {
                if ((headLocation-1) == 15 || headLocation-1 == 11 || headLocation-1 == 7 || headLocation-1 == 3) {
                    valid=false;
                    System.out.println("Invalid Move");
                    return valid;

                }
            }
            if (squirrelRotation == 90) {
                if ((headLocation-1) == 12 || headLocation-1 == 8 || headLocation-1 == 4 || headLocation-1 == 0) {
                    valid=false;  
                    System.out.println("Invalid Move");
                    return valid;
                }
            }  
        }
        else    
            valid=false;
        
        valid = true;
        System.out.println("Valid Move");
        return valid;
    }
    private void move(boolean nutStatus) {
        if (nutStatus == true)
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1Nut.png",squirrelRotation));
        else
            gridButton[headLocation].setIcon(new Picture("icons/RedSquirrel1.png",squirrelRotation));

         gridButton[gettailLocation()].setIcon(new Picture("icons/RedSquirrel2.png",squirrelRotation));
        System.out.println("Head is at: "+headLocation+" Tail is at: "+tailLocation);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==gridButton[headLocation])
         {
             background.setRedSquirrel(this);
         }
    }
}

class BlackSquirrel extends RedSquirrel {

    public BlackSquirrel(int headLocation, int squirrelRotation, Background bg) {
        super(headLocation, squirrelRotation, bg);
        
    }

}

class GreySquirrel extends RedSquirrel {

    public GreySquirrel(int headLocation, int squirrelRotation, Background bg) {
        super(headLocation, squirrelRotation, bg);
        
    }

}

class BrownSquirrel extends RedSquirrel {

    public BrownSquirrel(int headLocation, int squirrelRotation, Background bg) {
        super(headLocation, squirrelRotation, bg);
        
    }

}
