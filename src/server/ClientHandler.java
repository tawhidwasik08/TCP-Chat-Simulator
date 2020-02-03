package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.Server.userCon;

public class ClientHandler extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    int clientId;
    String clientName;
    
    byte[] recieveData;

    ClientHandler(Socket s_main, DataInputStream dis_main, DataOutputStream dos_main, int clientId_main) {
        s = s_main;
        dis = dis_main;
        dos = dos_main;
        clientId = clientId_main;

    }
    
    void seeend(String m) throws IOException{
         for (int i = 0; i < userCon.size(); i++) {
                    DataOutputStream dos = new DataOutputStream(userCon.get(i).getOutputStream());
                    dos.writeBytes(m);
                }
    }
    
    
    
    @Override
    public synchronized void run() {
        Server serv = new Server();
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        try {
            clientName = br.readLine();
            System.out.println("A new client has joined the chat server:"+clientName);
            serv.broadcast(clientName + " has joined the chat server" + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                String sentenceFromClient = br.readLine();
                //System.out.println("id-" + clientId + "." + clientName + ": " + sentenceFromClient);

                String clientStream = clientName + ":" + sentenceFromClient + '\n';
                //dos.writeBytes(clientStream);

                serv.userStream.add(clientName + ":" + sentenceFromClient + '\n');
                //System.out.println("SERVER DATA:" + serv.userStream);
                //serv.broadcast(clientStream);
                seeend(clientStream);
               

            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
