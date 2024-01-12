package CodesLA2800;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainA3{
    public static void main(String[] args){

        //#############################################################################################################
        // CREATING FRAME AND PANELS
        JFrame mainF = new JFrame("Graph drawer");
        DrawingPanel drawingP = new DrawingPanel();
        JPanel optionP = new JPanel();
        JRadioButton addRB = new JRadioButton("add edge", true);
        JRadioButton moveRB = new JRadioButton("move edge");
        JRadioButton deleteRB = new JRadioButton("delete edge");
        ButtonGroup jRadioGroup = new ButtonGroup( );

        JLabel instructionHelper = new JLabel("add edge by pressing and dragging your mouse");        // provides instruction when selecting buttons


        jRadioGroup.add(addRB);
        jRadioGroup.add(moveRB);
        jRadioGroup.add(deleteRB);

        mainF.add(drawingP, BorderLayout.NORTH);
        mainF.add(drawingP);
        mainF.add(optionP);

        optionP.add(addRB);
        optionP.add(moveRB);
        optionP.add(deleteRB);
        optionP.add(instructionHelper);

        drawingP.addMouseListener(new ClickingListener(drawingP, addRB, moveRB, deleteRB, instructionHelper));      // for the right click menu
        //#############################################################################################################
        // adding action listener
        addRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingP.actionSelected = "add";
                instructionHelper.setText("add edge by pressing and dragging your mouse");
            }
        });
        moveRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingP.actionSelected = "move";
                instructionHelper.setText("move a line by pressing on the line and dragging your mouse");

            }
        });
        deleteRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingP.actionSelected = "delete";
                instructionHelper.setText("delete a line by clicking on the line");

            }
        });

        //#############################################################################################################
        // LAYOUT
        mainF.setLayout(null);
        optionP.setLayout(null);
        mainF.setSize(900, 900);
        drawingP.setBounds(0,0,900,600);
        optionP.setBounds(0,600,900,300);

        addRB.setBounds(50,10,100,50);
        moveRB.setBounds(50,50,200,50);
        deleteRB.setBounds(50,90,200,50);
        instructionHelper.setBounds(50,130,600,50);

        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainF.setVisible(true);
    }
}
