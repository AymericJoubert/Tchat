package Server;

public class User {
    String username;
    String password;
    String id;

    public User(String id, String username, String password){
        this.username = username;
        this.password = password;
        this.id = id;
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
}
