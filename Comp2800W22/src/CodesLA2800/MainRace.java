package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainRace {

    static JTextField hareSpeedTF = new JTextField(20);
    static JButton hareSpeedSub;
    static JLabel hareSpeedLabel;

    static JTextField turtleSpeedTF = new JTextField(20);
    static JButton turtleSpeedSub;
    static JLabel turtleSpeedLabel;

    static JTextField hareSleepTimeTF = new JTextField(20);
    static JButton hareSleepTimeSub;
    static JLabel hareSleepTimeLabel;


    // addActionListener to button

    public static void main(String[] args){
        int HARE_DELAY_TIME = 10;        //to set the delay time
        int TURTLE_DELAY_TIME = 100;        //to set the delay time
        final int[] HARE_SLEEP_TIME = {-1};        //to set the delay time

        JFrame mainF = new JFrame("race show");

        JPanel inputP = new JPanel();
        JButton startRace = new JButton("START THE RACE");
        JButton reset = new JButton("RESET");
        JLabel winner = new JLabel("winner: to be announce");

        final RacePanel[] raceP = {new RacePanel(winner)};

        //#################################################################################
        // input panel components
        hareSpeedTF = new JTextField(20);
        hareSpeedLabel = new JLabel("** please enter hare's speed from 1-100");
        hareSpeedSub = new JButton("submit");

        turtleSpeedTF = new JTextField(20);
        turtleSpeedLabel = new JLabel("** please enter turtle's speed from 1-100");
        turtleSpeedSub = new JButton("submit");

        hareSleepTimeTF = new JTextField(20);
        hareSleepTimeLabel = new JLabel("** please enter hare's sleep time in seconds (if applicable)");
        hareSleepTimeSub = new JButton("submit");

        hareSpeedSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String s = evt.getActionCommand();
                if (s.equals("submit") && 0 <Integer.parseInt(hareSpeedTF.getText()) && Integer.parseInt(hareSpeedTF.getText()) <101) {

                    System.out.println(Integer.parseInt(hareSpeedTF.getText()));
                    raceP[0].hareT.setDelay(100/Integer.parseInt(hareSpeedTF.getText()));
                    hareSpeedLabel.setText("hare's speed is " + hareSpeedTF.getText());
                    hareSpeedTF.setText("");
                }

            }
        });

        turtleSpeedSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String s = evt.getActionCommand();
                if (s.equals("submit") && 0 <Integer.parseInt(turtleSpeedTF.getText()) && Integer.parseInt(turtleSpeedTF.getText()) <101) {

                    System.out.println(Integer.parseInt(turtleSpeedTF.getText()));
                    raceP[0].turtleT.setDelay(100/Integer.parseInt(turtleSpeedTF.getText()));

                    turtleSpeedLabel.setText("turtle's speed (in sec) is " + turtleSpeedTF.getText());
                    turtleSpeedTF.setText("");
                }

            }
        });
        hareSleepTimeSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String s = evt.getActionCommand();
                if (s.equals("submit")){

                    hareSleepTimeLabel.setText("hare's sleep time in total is " + hareSleepTimeTF.getText());
                    HARE_SLEEP_TIME[0] = Integer.parseInt(hareSleepTimeTF.getText());
                    hareSleepTimeTF.setText("");

                }

            }
        });

        startRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //start the race function

                raceP[0].hareT.start();
                raceP[0].turtleT.start();
                if(HARE_SLEEP_TIME[0]!=-1)
                    System.out.println("HARE_SLEEP_TIME[0]: " +HARE_SLEEP_TIME[0]);
                raceP[0].setHareSleepTime(HARE_SLEEP_TIME[0]);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //reset the race function
                mainF.remove(raceP[0]);
                raceP[0] = new RacePanel(winner);
                raceP[0].setBounds(350,0, 250, 900);
                raceP[0].setBackground(Color.GREEN);
                mainF.add(raceP[0]);
                SwingUtilities.updateComponentTreeUI(mainF);

                hareSpeedLabel.setText("** please enter hare's speed from 1-100");
                turtleSpeedLabel.setText("** please enter turtle's speed from 1-100");
                hareSleepTimeLabel.setText("** please enter hare's sleep time in seconds (if applicable)");
                winner.setText("winner: to be announce");
            }
        });

        //#################################################################################
        // LAYOUT
        mainF.setLayout(null);
        inputP.setLayout(null);

        mainF.setSize(900,900);
        raceP[0].setBounds(350,0, 250, 900);
        inputP.setBounds(0,0, 350, 900);

        hareSpeedTF.setBounds(10,10,300,25);
        hareSpeedSub.setBounds(10,50,100,25);
        hareSpeedLabel.setBounds(10,100,300,10);

        turtleSpeedTF.setBounds(10,200,300,25);
        turtleSpeedSub.setBounds(10,240,100,25);
        turtleSpeedLabel.setBounds(10,290,300,10);

        hareSleepTimeTF.setBounds(10,390,300,25);
        hareSleepTimeSub.setBounds(10,430,100,25);
        hareSleepTimeLabel.setBounds(10,480,300,10);


        startRace.setBounds(10,600,200,50);
        reset.setBounds(220,600,80,50);
        winner.setBounds(10,700, 400, 100);

        raceP[0].setBackground(Color.GREEN);

        mainF.add(raceP[0]);
        mainF.add(inputP);

        inputP.add(hareSpeedTF);
        inputP.add(hareSpeedSub);
        inputP.add(hareSpeedLabel);

        inputP.add(turtleSpeedTF);
        inputP.add(turtleSpeedSub);
        inputP.add(turtleSpeedLabel);

        inputP.add(hareSleepTimeTF);
        inputP.add(hareSleepTimeSub);
        inputP.add(hareSleepTimeLabel);

        inputP.add(startRace);
        inputP.add(reset);
        inputP.add(winner);


        mainF.setVisible(true);
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
