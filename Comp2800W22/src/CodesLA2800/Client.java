package CodesLA2800;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

public class Client extends JFrame {
    //text field to receiving radius
    private JTextField jtf = new JTextField();
    //Text area to display contents
    private JTextArea jta = new JTextArea();
    //input output streams
    private DataInputStream fromServer;// = new DataInputStream(socket.getInputStream());
    private DataOutputStream toServer;// = new DataOutputStream(socket.getOutputStream());

    public Client() {
        setTitle("Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);


        //panel to hold the label and text filed
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("Server IP :"), BorderLayout.WEST);
        p.add(jtf, BorderLayout.CENTER);
        jtf.setHorizontalAlignment(JTextField.RIGHT);

        setLayout(new BorderLayout());
        add(p, BorderLayout.NORTH);
        add(new JScrollPane(jta), BorderLayout.CENTER);
        jtf.addActionListener(new TextFieldListener());


        try {
            //create socket to connect to the server
            Socket socket = new Socket("10.142.132.204", 8000);
            //create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());
            //create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private class TextFieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                //get ip from the text field
                double ip = Double.parseDouble(jtf.getText().trim());
                //send ip to server
                toServer.writeDouble(ip);
                toServer.flush();



                jta.append("IP is : " + ip + "\n");

            } catch (IOException ex) {
                System.err.println(ex);
            }
        }

    }


    public static void main(String[] args) {
        new Client();


    }
}
