package Server;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Database {
    String database;
    Connection connection;

    public Database (String databaseName) {
        this.database = databaseName;
        connection = null;
        // test if postgresql class is found
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
        }
        // try to get the connection
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/"+database, "postgres", "postgres");
        } catch (SQLException e) {
            log("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public Object ExecuteSelect (String query) {
        return null;
    }

    public int ExecuteUpdate (String query) {
        return 0;
    }

    public int ExecuteDelete (String query) {
        return 0;
    }

    public boolean register(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES ('" + username + "','" + password + "');";
        log(query);
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            int nbLines = stmt.executeUpdate(query);
            if (nbLines > 0) return true;
        }
        catch(Exception e){
            try {
                stmt.close();
            }
            catch(Exception ee){}
            return false;
        }
        return false;
    }

    public String login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';";
        log(query);
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            if(rs.next()){
                return "success###" + rs.getString(1) + "###" + rs.getString(2) + "###" + rs.getString(3);
            }
        }
        catch(Exception e){
            try {
                stmt.close();
            }
            catch(Exception ee){}
            return "fail###";
        }
        return "fail###";
    }

    public void close(){
        try {
            connection.close();
        }
        catch(Exception e){}
    }

    public String getContacts(String id){
        String query = "SELECT id, username FROM users WHERE id IN (SELECT idUser2 from contacts WHERE idUser1 = " + id + ");";
        log(query);
        String result = "";
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                result += rs.getString(1) + "%%%" + rs.getString(2) + "###";
            }
            if(!result.equals("")){
                return result;
            }
            else{
                return "empty";
            }
        }
        catch(Exception e){
            try {
                stmt.close();
            }
            catch(Exception ee){}
            return "empty";
        }
    }

    public boolean createContact(String id1, String id2){
        return false;
    }

    public static void log(String message) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        System.out.println("[" + formatter.format(new Timestamp(new java.util.Date().getTime())) + "] : " + message);
    }
}