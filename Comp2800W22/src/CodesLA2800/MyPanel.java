package CodesLA2800;


import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    MyPanel() {
        this.setPreferredSize(new Dimension(500,500));




    }


    public void paint(Graphics g) {

        g.drawArc(20,100,250,250, 80, 20);
        g.drawArc(200,150,300,300, 50, 80);
        g.drawArc(250,200,300,200, 35, 40);
        g.drawArc(30, 210,260, 320,60, 120);
    }

}