package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserService extends Thread {

    private String id;
    private Socket connection;
    private User user;
    private String req;
    private String [] reqTab;

    public UserService(Socket connection, String [] userInfos){
        this.connection = connection;
        try {
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            user = new User(userInfos[1], userInfos[2], userInfos[3]);
            out.println(getConnectedContacts());
            out.flush();
            while (true) {
                String req = in.readLine();
                if (req != null){
                    reqTab = req.split("###");
                    if(reqTab[0].equals("quit")) {
                        break;
                    }
                    if(reqTab[0].equals("getContacts")){
                        out.println(getConnectedContacts());
                        out.flush();
                    }
                }
            }
            out.close();
            in.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getConnectedContacts(){
        Database db = new Database("tchat");
        String contacts = db.getContacts(user.getId());
        db.close();
        return contacts;
    }

    public boolean setPassword(){
        return false;
    }

    public boolean setUsername(){
        return false;
    }

    public void updateUser(){

    }
}
