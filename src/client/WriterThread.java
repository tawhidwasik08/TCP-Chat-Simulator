package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterThread extends Thread{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    int clientId;

    WriterThread(Socket s_main, DataInputStream dis_main, DataOutputStream dos_main) {
        s = s_main;
        dis = dis_main;
        dos= dos_main;
    }
    @Override
    public void run(){
        while(true){
            Scanner sc=new Scanner(System.in);
            String clientRes=sc.nextLine();
            try {
                dos.writeBytes(clientRes+'\n');
            } catch (IOException ex) {
                Logger.getLogger(WriterThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
