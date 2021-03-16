package Coursework_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Background implements ActionListener{

    private JFrame frame;
    private JPanel panel;
    private JPanel grid;
    private JButton[] gridButton;
    private RedSquirrel redSquirrel;
    private BlackSquirrel blackSquirrel;
    private GreySquirrel greySquirrel;
    private BrownSquirrel brownSquirrel;
    private boolean nutStatus;
    private String direction;


    private JButton upArrow = new JButton(new Picture("icons/BigArrow.png",0));
    private JButton rightArrow = new JButton(new Picture("icons/Arrow.png",270));
    private JButton downArrow = new JButton(new Picture("icons/BigArrow.png",180));
    private JButton leftArrow = new JButton(new Picture("icons/Arrow.png",90));

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
        }

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add("Center",grid);
        panel.add("South",downArrow);
        panel.add("West",rightArrow);
        panel.add("East",leftArrow);
        panel.add("North",upArrow);

        upArrow.addActionListener(this);
        downArrow.addActionListener(this);
        rightArrow.addActionListener(this);
        leftArrow.addActionListener(this);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void setRedSquirrel(RedSquirrel squirrel) {
        this.redSquirrel=squirrel;
        this.blackSquirrel=null;
        this.brownSquirrel=null;
        this.greySquirrel=null;
    }

    public void setBlackSquirrel(BlackSquirrel squirrel) {
        this.redSquirrel=null;
        this.blackSquirrel=squirrel;
        this.brownSquirrel=null;
        this.greySquirrel=null;
    }

    public void setGreySquirrel(GreySquirrel squirrel) {
        this.redSquirrel=null;
        this.blackSquirrel=null;
        this.brownSquirrel=null;
        this.greySquirrel=squirrel;
    }

    public void setBrownSquirrel(BrownSquirrel squirrel) {
        this.redSquirrel=null;
        this.blackSquirrel=null;
        this.brownSquirrel=squirrel;
        this.greySquirrel=null;
    }

    private void moveSquirrel() {
        if (redSquirrel != null)
            redSquirrel.moveSquirrel(nutStatus, direction);
        if (blackSquirrel != null)
            blackSquirrel.moveSquirrel(nutStatus, direction);
        if (greySquirrel != null)
            greySquirrel.moveSquirrel(nutStatus, direction);
        if (brownSquirrel != null)
            brownSquirrel.moveSquirrel(nutStatus, direction);

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==upArrow) {
            direction = "Up";
            moveSquirrel();
        }

        if (e.getSource()==downArrow) {
            direction = "Down";
            moveSquirrel();
        }

        if (e.getSource()==rightArrow) {
            direction = "Right";
            moveSquirrel();
        }

        if (e.getSource()==leftArrow) {
            direction = "Left";
            moveSquirrel();
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
