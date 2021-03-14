package TestCode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Phone implements ActionListener{

    private JFrame frame;
    private JPanel numericPanel;
    private JPanel panel;

    private JButton yesButton = new JButton("Yes");
    private JButton noButton = new JButton("No");
    private JTextField phoneDisplay = new JTextField("01524 65201");

    public Phone()
    {
        frame = new JFrame();
        frame.setTitle("Ericsson T39");
        frame.setSize(200,200);

        yesButton.addActionListener(this);
        noButton.addActionListener(this);

        numericPanel = new JPanel();
        numericPanel.setLayout(new GridLayout(4,3));

        for (int i=1; i<10; i++) {
            numericPanel.add(new JButton("" + i));
        }

        numericPanel.add(new JButton("*"));
        numericPanel.add(new JButton("0"));
        numericPanel.add(new JButton("#"));

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add("South",numericPanel);
        panel.add("West",yesButton);
        panel.add("East",noButton);
        panel.add("North",phoneDisplay);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==yesButton) {
            System.out.println("Who asked");
            phoneDisplay.setText("Yes pressed!");
        }

        if(e.getSource()==noButton) {
            System.out.println("me lol");
            phoneDisplay.setText("no pressed!");
        }
    }

    
}