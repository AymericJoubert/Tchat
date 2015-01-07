package Client.Chat;

import Client.Guis.*;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTchat extends JFrame {

    public static void main(String [] args){
        String host = "localhost";
        int port = 11111;
        StringBuffer instr = new StringBuffer();
//        System.out.println("SocketClient initialized");

        Gui userInterface;

        try {
            
            // Socket connection
            InetAddress address = InetAddress.getByName(host);
//            Socket connection = new Socket(address, port);

//            userInterface = new UserGui("Tchat", connection);
            userInterface = new SignGui(address, port);


            // Close the socket connection
//            connection.close();
//            System.out.println(instr);



        }
        catch (IOException f) {
            System.out.println("IOException: " + f);
        }
        catch (Exception g) {
            System.out.println("Exception: " + g);
        }
    }
}