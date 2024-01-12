package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class A9 extends JFrame {
    JFrame frame;
    JPanel panel;
    JTextField textField = new JTextField();
    private Class<?> myclass;
    private Object object;


    A9() {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    myclass = Class.forName(textField.getText());
                    object = myclass.getConstructor().newInstance();
                    panel = (JPanel) object;
                    frame.add(panel);
                    frame.setVisible(true);

                    for (Field f: myclass.getFields()) {

                    }

                } catch (Exception e) {
                    System.out.print("Failed to Compile");

                }
            }
        });


    }



    public static void main(String[] args) {

        JFrame frame = new JFrame("Auto Grading");
        JPanel panel = new JPanel(/*new BorderLayout()*/);


        JLabel label = new JLabel("Server IP: ");

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200,40));
        panel.setPreferredSize(new Dimension(300,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(textField);
        panel.add(label, BorderLayout.EAST);
        frame.add(panel);

        frame.setSize(500,500);
        frame.setVisible(true);

        new A9();
    }


}
