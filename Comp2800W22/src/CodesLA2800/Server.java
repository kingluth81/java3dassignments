package CodesLA2800;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Server extends JFrame {
    //text for displaying contents
    private JTextArea jta = new JTextArea();

    public Server(){
        setTitle("Server");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);
        setVisible(true);

        try{
            //create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Server started at : "+ new Date() + "\n");
            //listen for connection request
            Socket socket = serverSocket.accept();
            //create data input and output streams
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            while(true){
                //receive IP from client
                double ip = inputFromClient.readDouble();

                //send back IP to client
                outputToClient.writeDouble(ip);
                jta.append("IP recieved from client : "+ ip + "\n");
                jta.append("IP found : "+ ip + "\n");
            }

        }
        catch(IOException ex){
            System.err.println(ex);
        }

    }

    public static void main(String[] args) {
        new Server();
    }
}
