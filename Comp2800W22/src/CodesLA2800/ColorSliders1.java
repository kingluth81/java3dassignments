package CodesLA2800;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorSliders1 extends JPanel {
    // defining important variables required
    private JSlider red, green, blue;
    private JLabel redTxt, greenTxt, blueTxt;
    private ColorDisplayPanel colorDisplayPanel;

    // constructor to initialize the frame
    public ColorSliders1() {
        // passing a title to super class constructor
        //super("Slider For Colors");
        JFrame frame = new JFrame();

        // initializing a panel to hold sliders and output text field
        JPanel sliderPanel = new JPanel();
        // initializing sliders to have 0 as min value, 255 as max value, 0 as default
        // value
        red = new JSlider(0, 255, 0);
        green = new JSlider(0, 255, 0);
        blue = new JSlider(0, 255, 0);

        // also initializing labels to display current selection
        redTxt = new JLabel("0");
        greenTxt = new JLabel("0");
        blueTxt = new JLabel("0");

        // defining a panel, adding label "Red", red slider, and label
        JPanel row1 = new JPanel();
        row1.add(new Label("Red"));
        row1.add(red);
        row1.add(redTxt);

        // repeating for green slider
        JPanel row2 = new JPanel();
        row2.add(new Label("Green"));
        row2.add(green);
        row2.add(greenTxt);

        // repeating for blue slider
        JPanel row3 = new JPanel();
        row3.add(new Label("Blue"));
        row3.add(blue);
        row3.add(blueTxt);

        // setting sliderPanel's layout to have 1 column and unspecified number of rows
        sliderPanel.setLayout(new GridLayout(0, 1));
        // adding rows to sliderPanel
        sliderPanel.add(row1);
        sliderPanel.add(row2);
        sliderPanel.add(row3);

        // adding slider panel to the bottom of the window
        add(sliderPanel, BorderLayout.PAGE_END);

        // initializing colorDisplayPanel
        colorDisplayPanel = new ColorDisplayPanel();
        colorDisplayPanel.setPreferredSize(new Dimension(350, 300));

        // adding colorDisplayPanel to the center of the frame
        add(colorDisplayPanel);

        // creating a ChangeListener object to simply repaint the colorDisplayPanel when
        // an event occur.
        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                colorDisplayPanel.repaint();
            }
        };
        // adding this as listener for all three sliders
        red.addChangeListener(listener);
        green.addChangeListener(listener);
        blue.addChangeListener(listener);

        // settting up, displaying the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.add(colorDisplayPanel);
        frame.setVisible(true);
    }

    // main method to initialize the frame
    public static void main(String[] args) {
        new ColorSliders();
    }

    // a simple nested class to represent a Jpanel used to display the selected
    // color
    class ColorDisplayPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // fetching current values from the sliders to create a color
            g.setColor(new Color(red.getValue(), green.getValue(), blue.getValue()));
            // filling the panel with the color
            g.fillRect(0, 0, getWidth(), getHeight());
            // also updating the slider labels
            redTxt.setText(String.valueOf(red.getValue()));
            greenTxt.setText(String.valueOf(green.getValue()));
            blueTxt.setText(String.valueOf(blue.getValue()));
        }
    }
}
