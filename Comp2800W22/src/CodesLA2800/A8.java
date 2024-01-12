package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class A8 extends JFrame{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField textField = new JTextField();
    private Class<?> myclass;
    private Object object;
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();


    A8() {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    myclass = Class.forName(textField.getText());
                    object = myclass.getConstructor().newInstance();
                    panel1 = (JPanel) object;
                    frame.add(panel1);
                    frame.setVisible(true);

                    for (Field f: myclass.getFields()) {

                    }

                } catch (Exception e) {
                    System.out.print("Invalid Class");
                }

            }
        });
    }




    public static void main(String[] args) {

        JFrame frame = new JFrame("My Simple Applet");
        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100,40));
        panel.setPreferredSize(new Dimension(300,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(textField);
        frame.add(panel);

        frame.setSize(500,500);
        frame.setVisible(true);

        new A8();
    }
}
