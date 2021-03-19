package Coursework_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Background implements ActionListener{

    private JFrame frame;
    private JPanel panel;
    private JPanel grid;
    private JButton[] gridButton;
    private Squirrel squirrel;
    private String direction;
    private int flowerLocation=16; //Initialise flower to an unreachable location instead of 0
    private Squirrel redSquirrel,blackSquirrel,greySquirrel,brownSquirrel;
    private Integer[] obstacles,filledHoles;
    private Squirrel[] activeSquirrels;

    private JButton upArrow = new JButton(new Picture("icons/BigArrow.png",0));
    private JButton rightArrow = new JButton(new Picture("icons/Arrow.png",90));
    private JButton downArrow = new JButton(new Picture("icons/BigArrow.png",180));
    private JButton leftArrow = new JButton(new Picture("icons/Arrow.png",270));

    public Background() {
        activeSquirrels = new Squirrel[4];
        gridButton = new JButton[16];
        obstacles = new Integer[11];
        filledHoles = new Integer[4];
        frame = new JFrame();
        frame.setTitle("Case Noisettes");
        frame.setSize(600,600);

        grid = new JPanel();
        grid.setLayout(new GridLayout(4,4));   
        for (int i=0; i<16; i++) {
            if (i == 2 || i==4|| i==9 || i==15) 
                gridButton[i] = new JButton(new Picture("icons/Hole.png", 0));
            else
                gridButton[i] = new JButton(new Picture("icons/Empty.png", 0));

            grid.add(gridButton[i]);
            gridButton[i].addActionListener(this);
        }

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add("Center",grid);
        panel.add("South",downArrow);
        panel.add("West",leftArrow);
        panel.add("East",rightArrow);
        panel.add("North",upArrow);

        upArrow.addActionListener(this);
        downArrow.addActionListener(this);
        rightArrow.addActionListener(this);
        leftArrow.addActionListener(this);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setSquirrel(Squirrel squirrel) {
        this.squirrel=squirrel;
    }

    public void createFlower(int location) {
        gridButton[location].setIcon(new Picture("icons/Flower.png",0));
        this.flowerLocation=location;
    }

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

    public int getflowerLocation() {
        return flowerLocation;
    }

    private void moveSquirrel() {
            squirrel.moveSquirrel(squirrel.getnutStatus(), direction);
            boolean cleared = this.checkCleared();
            if (cleared == true)
                this.levelCleared();

    }

    public boolean checkCleared() {
        boolean clearedLevel = true;
        for (int i=0; i<4;i++)
            if (activeSquirrels[i] != null)
                if (activeSquirrels[i].getnutStatus()==true) {
                    clearedLevel = false;
                    return clearedLevel;
                }   
        return clearedLevel;
    }

    private void clearedBox() {
        JFrame clearedFrame = new JFrame();
        JOptionPane.showMessageDialog(clearedFrame,"Congratulations!\nlevel cleared!");
    }

    private void levelCleared() {
        LevelSelect lvsq = new LevelSelect();
        frame.setVisible(false);
        frame.dispose();
        clearedBox();

    }

    public void setFilledHoles(Integer filledHole) {
        boolean unique = true;
        if (squirrel.getColour()=="Red")
            for (int i=0;i<4;i++)
                if (filledHoles[i] != null)
                    if (filledHoles[i]==filledHole)
                        unique = false;
            if (unique) 
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

    public Integer[] getFilledHoles() {
        return filledHoles;
    }

    public Integer[] getObstacles() {
        obstacles[0]=flowerLocation;
        if (redSquirrel != null) {
            obstacles[1]=redSquirrel.getheadLocation();
            obstacles[2]=redSquirrel.gettailLocation(redSquirrel.getheadLocation(), redSquirrel.getSquirrelRotation());
        }
        if (blackSquirrel != null) {
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

    public void actionPerformed(ActionEvent e) {
        if (this.squirrel != null) {
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

        if (redSquirrel != null) {
            if (e.getSource()==gridButton[redSquirrel.getheadLocation()]) {
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

    public JButton[] getgridButton() {
        return gridButton;
    }

    public JPanel getPanel() {
        return panel;
    }

    public Background getBackground() {
        return this;
    }
}
