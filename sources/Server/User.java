package Server;

import java.io.PrintWriter;
import java.net.Socket;

public class User {
    String username;
    String password;
    String id;
    PrintWriter printWriter;

    public User(String id, String username, String password, PrintWriter printWriter){
        this.username = username;
        this.password = password;
        this.id = id;
        this.printWriter = printWriter;
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
    public PrintWriter getPrintWriter() { return printWriter; }
}
