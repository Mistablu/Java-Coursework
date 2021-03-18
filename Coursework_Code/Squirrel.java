package Coursework_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Squirrel {
    private int headLocation,tailLocation,squirrelRotation,filledHole;
    private JButton[] gridButton;
    private boolean nutStatus=true,valid;
    private Background background;
    private String squirrelHead,squirrelNutHead,squirrelTail,colour;
    private int[] allHeadLocations;

    public Squirrel(int headLocation, int squirrelRotation, Background bg, String colour) {
        allHeadLocations = new int[4];
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
        if (headLocation == 2 || headLocation==4|| headLocation==9 || headLocation==15) {
            gridButton[headLocation].setIcon(new Picture("icons/Hole.png",0)); 
            if (headLocation==filledHole)
                gridButton[headLocation].setIcon(new Picture("icons/HoleNut.png",0));
        }  
        else
            gridButton[headLocation].setIcon(new Picture("icons/Empty.png",0));

        if (gettailLocation()==2 || gettailLocation()==4|| gettailLocation()==9 || gettailLocation()==15) {
            gridButton[gettailLocation()].setIcon(new Picture("icons/Hole.png",0)); 
            if (gettailLocation()==filledHole)
                gridButton[gettailLocation()].setIcon(new Picture("icons/HoleNut.png",0));  
        }  
        else
            gridButton[gettailLocation()].setIcon(new Picture("icons/Empty.png",0));
        



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
                if (headLocation-1 == background.getflowerLocation()+1) {
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

            if (squirrelRotation == 180) {
                if (headLocation-1 == background.getflowerLocation()+4) {
                    valid=false;  
                    return valid; 
                }
            }

            if (squirrelRotation == 0) {
                if (headLocation-1 == background.getflowerLocation()-4) {
                    valid=false;  
                    return valid; 
                }
            }  

            if (headLocation-1 == background.getflowerLocation()) {
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
        if ((headLocation+1) >-1 && (headLocation+1) <16) {

            if (squirrelRotation == 270) {
                if (headLocation+1 == 15 || headLocation+1 == 11 || headLocation+1 == 7 || headLocation+1 == 3) {
                    valid=false;
                    return valid;
                }
                if (headLocation+1 == background.getflowerLocation()-1) {
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

            if (squirrelRotation == 180) {
                if (headLocation+1 == background.getflowerLocation()+4) {
                    valid=false;  
                    return valid; 
                }
            }

            if (squirrelRotation == 0) {
                if (headLocation+1 == background.getflowerLocation()-4) {
                    valid=false;  
                    return valid; 
                } 
            }         

            if (headLocation+1 == background.getflowerLocation()) {
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
        if ((headLocation-4) >-1 && (headLocation-4) <16) {
            if (squirrelRotation == 180 ) {
                if (headLocation-4 == 0 || headLocation-4 == 1 || headLocation-4 == 2 || headLocation-4 == 3) {
                    valid=false;  
                    return valid;
                }    
                if (headLocation-4 == background.getflowerLocation()+4) {
                    valid=false;  
                    return valid;
                } 
            }

            if (squirrelRotation == 270 ) {
                if (headLocation-4 == background.getflowerLocation()-1) {
                    valid=false;  
                    return valid;  
                }   
            }
            if (squirrelRotation == 90 ) {    
                if (headLocation-4 == background.getflowerLocation()+1) {
                    valid=false;  
                    return valid;  
                }           
            }
            if (headLocation-4 == background.getflowerLocation()) {
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
        if ((headLocation+4) >-1 && (headLocation+4) <16) {
            if (squirrelRotation == 0 ) {
                if (headLocation+4 == 15 || headLocation+4 == 14 || headLocation+4 == 13 || headLocation+4 == 12) {
                    valid=false;  
                    return valid;
                }    
                if (headLocation+4 == background.getflowerLocation()-4) {
                    valid=false;  
                    return valid;  
                }         
            }

            if (squirrelRotation == 270 ) {
                if (headLocation+4 == background.getflowerLocation()-1) {
                    valid=false;  
                    return valid;  
                }                
            } 

            if (squirrelRotation == 90 ) {    
                if (headLocation+4 == background.getflowerLocation()+1) {
                    valid=false;  
                    return valid;  
                }    
            }

            if (headLocation+4 == background.getflowerLocation()) {
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

         gridButton[gettailLocation()].setIcon(new Picture(squirrelTail,squirrelRotation));

        // background.giveHeadLocation(headLocation);
        
    }

    public boolean getnutStatus() {
        return this.nutStatus;
    }

    //public void actionPerformed(ActionEvent e) {
    //    if (e.getSource()==gridButton[this.headLocation])
    //        background.setSquirrel(this);        
    //}
}
