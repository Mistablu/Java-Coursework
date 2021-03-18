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


    private JButton upArrow = new JButton(new Picture("icons/BigArrow.png",0));
    private JButton rightArrow = new JButton(new Picture("icons/Arrow.png",90));
    private JButton downArrow = new JButton(new Picture("icons/BigArrow.png",180));
    private JButton leftArrow = new JButton(new Picture("icons/Arrow.png",270));

    public Background() {
        gridButton = new JButton[16];
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
    }

    public void addBlackSquirrel(Squirrel newSquirrel) {
        this.blackSquirrel=newSquirrel;
    }

    public void addBrownSquirrel(Squirrel newSquirrel) {
        this.brownSquirrel=newSquirrel;
    }

    public void addGreySquirrel(Squirrel newSquirrel) {
        this.greySquirrel=newSquirrel;
    }

    public int getflowerLocation() {
        return flowerLocation;
    }

    private void moveSquirrel() {
            squirrel.moveSquirrel(squirrel.getnutStatus(), direction);

    }

    public void actionPerformed(ActionEvent e) {
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
        if (e.getSource()==gridButton[redSquirrel.getheadLocation()]) {
            System.out.println(redSquirrel.getheadLocation());
            this.squirrel=redSquirrel;
        }

        if (e.getSource()==gridButton[greySquirrel.getheadLocation()]) {
            System.out.println(greySquirrel.getheadLocation());
            this.squirrel=greySquirrel;
        }

        if (e.getSource()==gridButton[blackSquirrel.getheadLocation()]) {
            System.out.println(blackSquirrel.getheadLocation());
            this.squirrel=blackSquirrel;
        }

        if (e.getSource()==gridButton[brownSquirrel.getheadLocation()]) {
            System.out.println(brownSquirrel.getheadLocation());
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
