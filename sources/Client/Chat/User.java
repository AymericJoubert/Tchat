package Client.Chat;

import java.net.Socket;

public class User {
    String username;
    String password;
    String id;
    Socket socket;

    public User(String id, String username, String password, Socket socket){
        this.username = username;
        this.password = password;
        this.id = id;
        this.socket = socket;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getId(){
        return id;
    }
    public Socket getSocket() { return socket; }
}
