package Client.Chat;

import Client.Guis.SignGui;

import java.net.InetAddress;

public class Lancement {
    public static void main(String [] args) {
        boolean debugMode = false;
        for(String arg : args) {
            if (arg.equals("debug")){
                debugMode = true;
            }
        }
        String host = "localhost";
        int port = 11111;
        StringBuffer instr = new StringBuffer();

        try {
            InetAddress address = InetAddress.getByName(host);

            ClientTchat client = new ClientTchat(address, port);
            SignGui signGui  = new SignGui(address, port, client);
            client.setSignGui(signGui);
        }
        catch (Exception g) {
            System.out.println("Unable to boot ... ");
            if (debugMode)
                System.out.println(g);
            System.exit(0);
        }
    }
}
