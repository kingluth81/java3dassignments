package CodesLA2800;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ForFun extends JFrame {



    public static void main(String[] args) {
        ImageIcon image = new ImageIcon("rabbit.png");
        Border border = BorderFactory.createLineBorder(Color.blue,3);

        JLabel label = new JLabel();
        label.setText("Aamari is a Bitch");
        label.setIcon(image);
        label.setForeground(Color.PINK);
        label.setFont(new Font("MV Boli",Font.BOLD,30));
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        //label.setBounds(100,100,250,250);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        //frame.setLayout(null);
        frame.setVisible(true);
        frame.add(label);




        new ForFun();
    }

}
