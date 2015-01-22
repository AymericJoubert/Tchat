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

    Gui signGui;
    Gui tchatGui;
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

    public void setSignGui(SignGui signGui){
        this.signGui = signGui;
    }

    public void signIn(String username, String password, String email) {
        String [] ret;
        try {
            out.println("register###" + username + "###" + password + "###" + email);
            out.flush();
            ret = in.readLine().split("###");
            if (ret[0].equals("successRegister")){
                signGui.stopWaiting(true);
                signGui.popupMessage("You are now registered ! Log in to chat as a fifou !");
//                this.tchatGui = new UserGui("Fifou", connection, this);
            }
            else{
                signGui.stopWaiting(false);
                signGui.popupMessage("Inscription impossible, nom d'utilisateur indisponible.");
            }
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

    public void logIn(String username, String password){
        String [] ret;
        try {
            out.println("login###" + username + "###" + password);
            out.flush();
            String tmp = in.readLine();
            ret = tmp.split("###");
            System.out.println(tmp);
            if (ret[0].equals("success")){
                User user = new User(ret[1], ret[2], ret[3]);
                System.out.println("changement de gui");
                signGui.close();
                this.tchatGui = new UserGui("Fifou", connection, this, user);
            }
            else{
                signGui.stopWaiting(false);
                signGui.popupMessage("Connection Impossible.");
            }
        }
        catch(Exception e){
            System.out.println("An error occured during connection");
            try {
                connection.close();
            }
            catch(Exception ee){}
            System.out.println("tout cassé");
        }
    }
}