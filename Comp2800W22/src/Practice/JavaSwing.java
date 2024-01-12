package Practice;

import javax.swing.*;
import java.awt.*;

public class JavaSwing extends JFrame {
    JFrame frame = new JFrame("Practice Sheet");
    JPanel panel = new JPanel();

    JavaSwing() {
        panel.setBackground(Color.red);
        panel.setBounds(0,0,500,300);
        panel.setSize(500,300);
        panel.setVisible(true);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }







    public static void main (String[] args) {
        new JavaSwing();

    }
}
