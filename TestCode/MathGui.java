package TestCode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MathGui implements ActionListener{

    private JFrame frame;
    private JPanel panel;
    private JPanel grid;
    private String answer;

    private JButton lesserButton = new JButton("<");
    private JButton greaterButton = new JButton(">");
    private JButton equalsButton = new JButton("=");
    private JButton askButton = new JButton("Ask me a question!");
    private JTextField phoneDisplay = new JTextField("Welcome to the maths tutor!");

    public MathGui()
    {
        frame = new JFrame();
        frame.setTitle("Ericsson T39");
        frame.setSize(300,150);

        lesserButton.addActionListener(this);
        greaterButton.addActionListener(this);
        equalsButton.addActionListener(this);
        askButton.addActionListener(this);
        
        grid = new JPanel();
        grid.setLayout(new GridLayout(1,3));   
        grid.add(lesserButton);
        grid.add(equalsButton);
        grid.add(greaterButton);



        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add("Center",grid);
        panel.add("North",phoneDisplay);
        panel.add("South",askButton);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public int randomNo() {
        double i = Math.random();
        int randomInt = (int) (i*10);
        return randomInt;
    }

    public String question() {
        int a = randomNo();
        int b = randomNo();
        int c = randomNo();
        int d = randomNo();

        if (a+b == c+d)
            answer = "=";

        if (a+b < c+d)
            answer = "<";
        
        if (a+b > c+d)
            answer = ">";

        phoneDisplay.setText("Is "+a+ " + "+b+" lesser than, equal to or greater than "+c+" + "+d);


        return answer;

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==askButton) {
            question();
        }

        if(e.getSource()==lesserButton) {
            if (answer=="<") {
                phoneDisplay.setText("Correct!");
            }

            else {
                phoneDisplay.setText("Incorrect, the answer is "+answer);
            }
        }

        if(e.getSource()==greaterButton) {
            if (answer==">") {
                phoneDisplay.setText("Correct!");
            }

            else {
                phoneDisplay.setText("Incorrect, the answer is "+answer);
            }
        }

        if(e.getSource()==equalsButton) {
            if (answer=="=") {
                phoneDisplay.setText("Correct!");
            }

            else {
                phoneDisplay.setText("Incorrect, the answer is "+answer);
            }
        }

    }

    
}