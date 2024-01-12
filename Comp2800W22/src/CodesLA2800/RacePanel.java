package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class RacePanel extends JPanel {

    int IMG_DIMENSION = 100;

    Image hare = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("rabbit2.png"))).getImage();
    Image turtle = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("turtle2.png"))).getImage();

    Timer turtleT, hareT, click1T, click2T;
    JLabel winner;

    int hareLocX=150, hareLocY=700;
    int turtleLocX=0, turtleLocY=700;

    int hareSleepTime = -1;
    int[] mouseLocation = new int[4];               //[x1,y1,x2,y2]
    int[] lineCordi = {(hareLocX+IMG_DIMENSION+turtleLocX)/2,0,(hareLocX+IMG_DIMENSION+turtleLocX)/2,900};
    boolean click1 = false, click2 = false, harePaint = false, turtlePaint = false, hareUnSleep = true,hareUnSleep2 = true;

    //####################################################
    // constructor
    RacePanel(JLabel winner){
        this.winner = winner;
        //####################################################
        // setting the timer
        turtleT = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                turtleLocY -= 1;
                repaint();
            }
        });
        hareT = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hareUnSleep && hareUnSleep2)        // only if hare is not sleeping
                    hareLocY -= 1;

                repaint();
            }
        });

        click1T = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hareUnSleep = true;
            }
        });
        click2T = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hareUnSleep2 = true;
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (!click1){
                    if(inHareLine(e.getX(), e.getY())) {
                        mouseLocation[0] = e.getX();
                        mouseLocation[1] = e.getY();
                        System.out.println("x: " + e.getX() + "   y: " + e.getY());
                        click1 = true;
                        repaint();
                    }

                }
                else if(!click2){
                    if(inHareLine(e.getX(), e.getY())){
                        mouseLocation[2] = e.getX();
                        mouseLocation[3] = e.getY();
                        System.out.println("x: " + e.getX() + "   y: " + e.getY());
                        click2 = true;
                        repaint();
                    }
                }
                else
                    System.out.println("points are already chosen");
            }
        });
    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(hare, hareLocX, hareLocY, IMG_DIMENSION, IMG_DIMENSION, this);
        g.drawImage(turtle, turtleLocX, turtleLocY, IMG_DIMENSION, IMG_DIMENSION, this);

        g.drawLine(lineCordi[0],lineCordi[1],lineCordi[2],lineCordi[3]);

        if (click1) {

            g.setColor(Color.red);
            g.fillOval(mouseLocation[0]-5, mouseLocation[1]-5, 10, 10);
            if (hareLocY==mouseLocation[1]-10){
                hareUnSleep = false;
                click1T.start();

            }
        }
        if (click2){
            g.setColor(Color.red);
            g.fillOval(mouseLocation[2]-5, mouseLocation[3]-5,  10, 10);
            if (hareLocY==mouseLocation[3]-10){
                hareUnSleep2 = false;
                click2T.start();

            }
        }
        if(hareLocY == 10){
            hareT.stop();
            turtleT.stop();
            announceWinner("Hare");
        }
        else if(turtleLocY == 10){
            turtleT.stop();
            hareT.stop();
            announceWinner("Turtle");
        }
        g.dispose();

    }
    public boolean inHareLine(int x, int y) {

        if (lineCordi[0] < x && x < 250)
            return true;
        else
            return false;

    }

    public void setHareSleepTime(int hareSleepTime){
        hareSleepTime = hareSleepTime*1000;
        if (!click1 && !click2){            //if no click
            this.hareSleepTime = -1;
        }
        else if(click2 && click1){          // if both click

            this.hareSleepTime = hareSleepTime / 2;
            click1T.setDelay(this.hareSleepTime);
            click1T.setInitialDelay(this.hareSleepTime);
            click2T.setDelay(this.hareSleepTime);
            click2T.setInitialDelay(this.hareSleepTime);

        }
        else {                              // if only one click
            if (click1) {
                this.hareSleepTime = hareSleepTime;
                click1T.setInitialDelay(this.hareSleepTime);
                click1T.setDelay(this.hareSleepTime);
            }
            if (click2) {
                this.hareSleepTime = hareSleepTime;
                click2T.setInitialDelay(this.hareSleepTime);
                click2T.setDelay(this.hareSleepTime);
            }
        }

    }

    public void announceWinner(String winnerName){
        winner.setText("winner: " + winnerName);
    }
}