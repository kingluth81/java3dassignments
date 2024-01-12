package CodesLA2800;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//#############################################################################################################
// pop up menu - right click
class ClickingListener extends MouseAdapter {
    DrawingPanel p;
    JRadioButton drawLine, move, remove;
    JLabel instructionHelper;
    ClickingListener(DrawingPanel p, JRadioButton drawLine, JRadioButton move, JRadioButton remove, JLabel instructionHelper){
        this.p = p;
        this.drawLine = drawLine;
        this.move = move;
        this.remove = remove;
        this.instructionHelper = instructionHelper;
    }
    public void mousePressed(MouseEvent e) {

        if(e.getButton() == MouseEvent.BUTTON3) {
            if (e.isPopupTrigger())
                doPop(e);
        }
    }

    public void mouseReleased(MouseEvent e) {

        if(e.getButton() == MouseEvent.BUTTON3) {
            if (e.isPopupTrigger())
                doPop(e);
        }
    }

    private void doPop(MouseEvent e) {
        PopUpOnLine menuOnLine = new PopUpOnLine(p, e.getX(), e.getY());
        PopUpNotLine menuNotLine = new PopUpNotLine(p, drawLine, move, remove, instructionHelper);

        if(p.isOnLine(e.getX(),e.getY()) != -1){    //on line
            menuOnLine.show(e.getComponent(), e.getX(), e.getY());
        }

        else{
            menuNotLine.show(e.getComponent(), e.getX(), e.getY());

        }

    }
}
//#############################################################################################################
// menu when right click is on line
class PopUpOnLine extends JPopupMenu {      // on line menu
    JMenuItem anItem;
    public PopUpOnLine(DrawingPanel drawingP, int x, int y) {
        anItem = new JMenuItem("remove line");
        anItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int lindInx = drawingP.isOnLine(x,y);
                drawingP.removeLine(lindInx);
                System.out.println("DEBUG -> number of lines: " + drawingP.points.size() + " | lindIndex: " + lindInx);
                repaint();

            }
        });
        add(anItem);
    }
}

//#############################################################################################################
// menu when right click is NOT on line
class PopUpNotLine extends JPopupMenu {     // not on line menu
    JMenuItem anItem,anItem2, anItem3;

    // constructor
    public PopUpNotLine(DrawingPanel drawingP,  JRadioButton drawLine, JRadioButton move, JRadioButton remove, JLabel instructionHelper){
        anItem = new JMenuItem("change selection to add line");
        anItem2 = new JMenuItem("change selection to move line");
        anItem3 = new JMenuItem("change selection to remove line");

        //#############################################################################################################
        // action for right click when it's NOT on any line
        anItem.addActionListener(new ActionListener() {     // add
            public void actionPerformed(ActionEvent e) {
                drawLine.setSelected(true);
                drawingP.actionSelected = "add";
                instructionHelper.setText("add edge by pressing and dragging your mouse");
            }
        });

        anItem2.addActionListener(new ActionListener() {    // move
            public void actionPerformed(ActionEvent e) {
                move.setSelected(true);
                drawingP.actionSelected = "move";
                instructionHelper.setText("move a line by pressing on the line and dragging your mouse");
            }
        });

        anItem3.addActionListener(new ActionListener() {    // remove
            public void actionPerformed(ActionEvent e) {
                remove.setSelected(true);
                drawingP.actionSelected = "delete";
                instructionHelper.setText("delete a line by clicking on the line");
            }
        });


        add(anItem);
        add(anItem2);
        add(anItem3);
    }
}
