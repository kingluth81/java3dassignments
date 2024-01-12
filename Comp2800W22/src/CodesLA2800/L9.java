package CodesLA2800;

import java.awt.*;
import javax.swing.JFrame;

public class L9 extends Canvas{

    public void paint(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("rabbit2.png");


        g.drawImage(i, 120,100,this);
        g.drawImage(i, 120,100,this);
        g.drawImage(i, 120,100,this);
        g.drawImage(i, 120,100,this);


    }
    public static void main(String[] args) {
        L9 m=new L9();
        JFrame f=new JFrame();
        f.add(m);
        f.setSize(400,400);
        f.setVisible(true);
    }

}
