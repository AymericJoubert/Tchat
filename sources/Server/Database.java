package Server;

import java.sql.*;

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
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
        }
        // try to get the connection
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/"+database, "postgres", "postgres");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
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
        System.out.println(query);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            if(rs.next()){
                System.out.println("j'ai un resultat");
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
}