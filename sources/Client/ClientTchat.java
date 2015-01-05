package Client;

import Client.Gui;

import java.net.*;
import java.io.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientTchat extends JFrame {

    public static void main(String [] args){
        String host = "localhost";
        int port = 11111;
        StringBuffer instr = new StringBuffer();
        String TimeStamp;
        System.out.println("SocketClient initialized");

        Gui gui;
/*
        try {
            
            // Socket connection
            InetAddress address = InetAddress.getByName(host);
            Socket connection = new Socket(address, port);

            // Instantiate a BufferedOutputStream object
            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());

            // Instantiate an OutputStreamWriter object with the optional character encoding
            OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");

            TimeStamp = new java.util.Date().toString();
            String process = "Calling the Socket Server on "+ host + " port " + port +
                    " at " + TimeStamp +  (char) 13;

            // Write across the socket connection and flush the buffer
            osw.write(process);
            osw.flush();

            // Instantiate a BufferedInputStream object for reading incoming socket streams
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());

            // Instantiate an InputStreamReader with the optional character encoding
            InputStreamReader isr = new InputStreamReader(bis, "UTF-8");

            // Read the socket's InputStream and append to a StringBuffer
            int c;
            while ( (c = isr.read()) != 13) {
                instr.append((char) c);
            }

            // Close the socket connection
            connection.close();
            System.out.println(instr);*/

            gui = new Gui("Tchat");
/*
        }
        catch (IOException f) {
            System.out.println("IOException: " + f);
        }
        catch (Exception g) {
            System.out.println("Exception: " + g);
        }*/
    }
}