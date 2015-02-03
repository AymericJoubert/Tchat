package Server;

import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

public class ServerTchat implements Runnable {

    private Socket connection;
    private static Database db;
    private static MainTalk mainTalk = null;
    private static int port = 11111;
    private static int mainChatPort = 12345;

    public static void main(String[] args) {
        log("Booting ServerTchat");
        try{
            log("Connecting socket ...");
            int count = 0;
            ServerSocket socket = new ServerSocket(port);
            log("Connecting Database ...");
            db = new Database("tchat");
            log("Creating main chat ...");
            ServerSocket mainChatSocket = new ServerSocket(mainChatPort);
            mainTalk = new MainTalk(mainChatSocket);
            mainTalk.start();
            log("ServerTchat Initialized");
            while (true) {
                Socket connection = socket.accept();
                Runnable runnable = new ServerTchat(connection);
                Thread t = new Thread(runnable);
                t.run();
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
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        System.out.println("[" + formatter.format(new Timestamp(new Date().getTime())) + "] : " + message);
    }


    public void run() {
        try {
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String req;
            while((req=in.readLine()) != null) {
                String[] request = req.split("###");
                if (request[0].equals("register")) {
//                    register();
                    if (db.register(request[1], request[2])) {
                        out.println("successRegister");
                        out.flush();
                    }
                    else{
                        out.println("fail");
                        out.flush();
                    }
                }
                if (request[0].equals("login")) {
//                    login();
                    String log = db.login(request[1], request[2]);
                    out.println(log+"###"+mainChatPort);
//                    out.println(log);
                    out.flush();
                    System.out.println(log+"###"+mainChatPort + "fifou");
                    if(!log.equals("fail")) {
                        String [] rrr = log.split("###");
                        User user = new User(rrr[1], rrr[2], rrr[3], out);
                        System.out.println("Ajout de l'utilisateur " + user.getUsername() + " dans la discussion");
                        mainTalk.addConnectedUser(user);
                        break;
                    }
                    else {
                        System.out.println("Je passe dans le else de FIFOU");
                    }
                }
            }
//            in.close();
//            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login () {

    }

    public void register () {

    }
}