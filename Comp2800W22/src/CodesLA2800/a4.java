package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class a4 extends JFrame implements ActionListener {




    JLabel messageLabel;
    MyPanel panel;



    a4() {

        panel = new MyPanel();


        JFrame arcs = new JFrame();
        arcs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        arcs.setSize(500,500);

        arcs.add(panel);
        arcs.setLocationRelativeTo(null);
        arcs.setVisible(true);

        JPanel panel = new JPanel();





        JPopupMenu popMenu = new JPopupMenu();

        JMenuItem item1 = new JMenuItem("Move");
        item1.addActionListener(this);
        item1.setActionCommand("Move");
        popMenu.add(item1);

        JMenuItem item2 = new JMenuItem("Insert");
        item2.addActionListener(this);
        item2.setActionCommand("Insert");
        popMenu.add(item2);

        JMenuItem item3 = new JMenuItem("Delete");
        item3.addActionListener(this);
        item3.setActionCommand("Delete");
        popMenu.add(item3);




        arcs.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(arcs, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x2,y2;
                x2 =e.getX();
                y2= e.getY();

                Graphics g = arcs.getGraphics();
                g.drawArc(x2, y2,200,200, 180, 80);

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if( s.equals("Click")) {
        }
    }



    public static void main(String args[]) {
        new a4();



    }


}