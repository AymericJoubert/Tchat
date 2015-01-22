package Client.Chat;

import Client.Guis.*;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTchat {

    // Connection infos
    InetAddress address;
    int port;

    Gui gui;
    Socket connection = null;
    PrintWriter out = null;
    BufferedReader in = null;
    public ClientTchat(InetAddress address, int port) {
        this.address = address;
        this.port = port;

        try {
            this.connection = new Socket(address, port);
            this.out = new PrintWriter(connection.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        catch(Exception e){
            System.out.println("Unable to connect to server");
        }
    }

    public void setGui(SignGui gui){
        this.gui = gui;
    }

    public void signIn(String username, String password, String email) {
        try {
            System.out.println("Je Passe ici");
            out.println("register###"+username+"###"+password+"###"+email);
            out.flush();
            System.out.println(in.readLine().split("###").toString());
//            return null;
        }
        catch(Exception e){
            System.out.println("An error occured during registration");
            try {
                connection.close();
            }
            catch(Exception ee){}
            System.out.println("tout cassé");
        }
    }
}