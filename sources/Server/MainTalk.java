package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class MainTalk extends Thread {

    private static List<User> connectedUsers;
    private BufferedReader in;
    private static ServerSocket connection;
    private Socket client;
    
    
    public MainTalk(ServerSocket connection) {
        MainTalk.connection = connection;
        connectedUsers = new LinkedList<User>();
        final ServerSocket conn = connection;
    }
    
    public void acceptConnections() {
        while (true) {
            try {
                Socket client = connection.accept();
                Runnable runnable = new MainTalk(client);
                Thread t = new Thread(runnable);
                t.run();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }        
    }
    
    public MainTalk (Socket client) {
        this.client = client;
    }
    

    
    public void addConnectedUser(User user){
        connectedUsers.add(user);        
    }
    public void removeConnectedUser(User user) {
        connectedUsers.remove(user);        
    }

    public void run () {
        while (true) {
            try {
                final Socket client = connection.accept();
                System.out.println("Connection d'un client");
                Thread tt = new Thread() {
                    public void run() {
                        MainTalk mt = new MainTalk(client);
                        mt.begin();
                    }
                };
                tt.run();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void begin(){
        PrintWriter out;
        String line;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Je Passe Ici");
            while((line=in.readLine()) != null) {
                System.out.println("J'ai recu : " + line);
                for (User currentUser : connectedUsers) {
                    out = currentUser.getPrintWriter();
                    out.println(line);
                    out.flush();
                    System.out.println("J'ai envoyé " + line + " a " + currentUser.getUsername());
                }
            }
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
    }
    
    public void updateTalks() {

    }
    
}