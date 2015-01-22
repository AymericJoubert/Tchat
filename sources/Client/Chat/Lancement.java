package Client.Chat;

import Client.Guis.SignGui;

import java.io.IOException;
import java.net.InetAddress;

public class Lancement {
    public static void main(String [] args) {
        String host = "localhost";
        int port = 11111;
        StringBuffer instr = new StringBuffer();
//        System.out.println("SocketClient initialized");

        try {

            // Socket connection
            InetAddress address = InetAddress.getByName(host);
//            Socket connection = new Socket(address, port);

//            userInterface = new UserGui("Tchat", connection);



            ClientTchat client = new ClientTchat(address, port);
            SignGui signGui  = new SignGui(address, port, client);
            client.setGui(signGui);



            // Close the socket connection
//            connection.close();
//            System.out.println(instr);



        }
        catch (IOException f) {
            System.out.println("IOException: " + f);
        }
        catch (Exception g) {
            System.out.println("Exception: " + g);
        }
    }
}
