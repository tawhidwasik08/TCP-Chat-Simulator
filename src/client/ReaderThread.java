package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderThread extends Thread{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    int clientId;

    ReaderThread(Socket s_main, DataInputStream dis_main, DataOutputStream dos_main) {
        s = s_main;
        dis= dis_main;
        dos = dos_main;
    }
    @Override
    public void run(){
        while(true){
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String connection;
            try {
                connection = br.readLine();
                System.out.println(connection);
            } catch (IOException ex) {
                Logger.getLogger(ReaderThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
