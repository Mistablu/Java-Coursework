package Coursework_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelSelect implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JPanel grid;

    private JButton lvl1Button = new JButton("Level 1");
    private JButton lvl2Button = new JButton("Level 2");
    private JButton lvl3Button = new JButton("Level 3");
    private JTextField welcomeDisplay = new JTextField("Welcome to Case Noisettes!");

    public LevelSelect() {
        frame = new JFrame();
        frame.setTitle("Ericsson T39");
        frame.setSize(300,150);

        lvl1Button.addActionListener(this);
        lvl2Button.addActionListener(this);
        lvl3Button.addActionListener(this);
        
        grid = new JPanel();
        grid.setLayout(new GridLayout(1,3));   
        grid.add(lvl1Button);
        grid.add(lvl2Button);
        grid.add(lvl3Button);



        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add("Center",grid);
        panel.add("North",welcomeDisplay);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buildLevel1() {
            frame.setVisible(false);
            Background bg = new Background();
            bg.createFlower(9);
            Squirrel sq = new Squirrel(5,270,bg,"Red");
            Squirrel gq = new Squirrel(10,0,bg,"Grey");
            bg.addRedSquirrel(sq);
            bg.addGreySquirrel(gq); 
        
    }

    private void buildLevel2() {

    }

    private void buildLevel3() {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==lvl1Button)
            buildLevel1();

        if (e.getSource()==lvl2Button)
            buildLevel2();

        if (e.getSource()==lvl3Button)
            buildLevel3();
        
    }
}