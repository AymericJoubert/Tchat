package Server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    String database;
    Connection connection;

    public Database (String database) {
        this.database = database;
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
}