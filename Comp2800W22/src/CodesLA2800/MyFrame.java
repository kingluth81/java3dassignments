package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JPanel implements ActionListener {

    MyFrame() {
        JFrame frame = new JFrame("Load & Display");
        JFrame a6 = new JFrame("Properties");
        JPanel panel = new JPanel();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        a6.setLayout(new FlowLayout());

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(240,40));
        frame.add(panel);
        panel.add(textField);
        frame.setSize(500,500);
        a6.setSize(400,600);
        a6.setVisible(true);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        new MyFrame();
    }
}
