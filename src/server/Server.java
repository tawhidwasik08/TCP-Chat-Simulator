package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    static List<String> userStream = new CopyOnWriteArrayList<String>();
    static List<Socket> userCon = new CopyOnWriteArrayList<Socket>();
    List <Socket> ghew= new ArrayList<>();
    
    public static void main(String[] args) throws IOException {

        ServerSocket welcomeSocket = new ServerSocket(6788);

        int i = 0;
        while (true) {
            Socket connectionSocket = null;

            connectionSocket = welcomeSocket.accept();
            userCon.add(connectionSocket);

            //System.out.println("A new client is connected : " + connectionSocket);
            System.out.println("Total clients " + userCon.size());

            DataInputStream dis = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(connectionSocket.getOutputStream());

            Thread t = new ClientHandler(connectionSocket, dis, dos, ++i);;
            t.start();

        }

    }

    synchronized void broadcast(String m) throws IOException {
        //System.out.println("BROADCASTING");
        //System.out.println(m);
        for (int i = 0; i < userCon.size(); i++) {
            DataOutputStream dos = new DataOutputStream(userCon.get(i).getOutputStream());
                dos.writeBytes(m);
        }

    }
}
