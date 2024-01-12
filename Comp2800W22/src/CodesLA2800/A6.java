package CodesLA2800;
//Java program that executes other Java classes and displays class properties and it's components
//Search for executable files and JTextfield, and JLabel
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class A6 extends JPanel {

    public static void main(String[] args) {
        JFrame a6 = new JFrame("Load & Display");
        JFrame properties = new JFrame("Properties");
        a6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //a6.setLayout(new FlowLayout());

        JTextField textField = new JTextField();
        textField.setSize(240,100);



        //JPanel panel = new JPanel();
        //panel.setBackground(Color.red);
        //a6.add(panel);
        a6.add(textField);
        a6.setSize(500,500);
        properties.setSize(300,500);
        a6.setVisible(true);
        properties.setVisible(true);

        new A6();

    }

}
