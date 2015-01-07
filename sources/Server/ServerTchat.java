package Server;

import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

public class ServerTchat implements Runnable {

    private Socket connection;
    private Socket connection2;
    private int ID;
    private Map connectedUsers = new HashMap<String, String>();

    public static void main(String[] args) {
        log("Booting ServerTchat");
        try{
            log("Connecting socket ...");
            int port = 11111;
            int count = 0;
            ServerSocket socket = new ServerSocket(port);
            log("ServerTchat Initialized");
            while (true) {
                System.out.println("Attente de connection");
                Socket connection = socket.accept();
                Runnable runnable = new ServerTchat(connection);
                Thread t = new Thread(runnable);
                t.run();


//                System.out.println("1ere connection ok");
//                Socket connection2 = socket1.accept();
//                System.out.println("2ere connection ok");
//                Runnable c1to2 = new ServerTchat(connection1, connection2, ++count);
//                Runnable c2to1 = new ServerTchat(connection2, connection1, ++count);
//                Thread thread1to2 = new Thread(c1to2);
//                Thread thread2to1 = new Thread(c2to1);
//                thread1to2.start();
//                thread2to1.start();
            }
        }
        catch (Exception e) {
            log("An orror occured when booting serveur ...");
            e.printStackTrace();
        }
    }

    ServerTchat(Socket s) {
        this.connection = s;
    }

    public static void log(String message) {
        Date date = new Date();
        Timestamp timeStampDate = new Timestamp(date.getTime());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        System.out.println("[" + formatter.format(timeStampDate) + "] : " + message);
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String req = in.readLine();
            String [] request = req.split("###");
            Database db = new Database("tchat");
            if(request[0].equals("register")){
                out.println(Boolean.toString(db.register("Je dois entrer une query ici")));
            }
        }
        catch (Exception e){

        }


//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
//            PrintWriter out = new PrintWriter(connection2.getOutputStream(), true);
//            String line;
//            System.out.println("Lecture sur : " + ID + ".");
//            line=in.readLine();
//            while (!line.equals("stop")) {
//                System.out.println("J'ai reçu : " + line + " de " + ID);
//                out.println(line);
//                out.flush();
//                line = in.readLine();
//            }
//            System.out.println("Je passe ici");
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//        finally {
//            try {
//                connection1.close();
//                connection2.close();
//            }
//            catch (IOException e){}
//        }
    }

    public void login () {

    }

    public void register () {

    }
}