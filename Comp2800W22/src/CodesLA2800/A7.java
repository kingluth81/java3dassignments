package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class A7 extends JPanel {
    JPanel panel;
    JTextField textField = new JTextField();
    private Object object;
    private Class<?> myclass;
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JFrame frame = new JFrame();




    A7() {
//        final Class[] myclass = {null};
//        try {
//            myclass[0] = Class.forName("A7");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Constructor con = null;
//        try {
//            con = myclass[0].getConstructor();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        try {
//            panel.add((Component)con.newInstance());
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        revalidate();


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
        JFrame frame = new JFrame("Load & Display");
        JFrame a7 = new JFrame("Properties");
        JPanel panel = new JPanel();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new FlowLayout());
        // a7.setLayout(new FlowLayout());

        JTextField textField = new JTextField();
        //JTextField textField1 = new JTextField();
        textField.setPreferredSize(new Dimension(100, 40));
        //textField1.setPreferredSize(new Dimension(120,40));
        panel.setBounds(0,0,300,300);
        panel.add(textField, BorderLayout.PAGE_START);
        frame.add(panel);
        //panel.add(textField1, BorderLayout.PAGE_END);



        frame.setSize(500,500);
        a7.setSize(400,600);
        a7.setVisible(true);
        frame.setVisible(true);

        new A7();
    }
}
