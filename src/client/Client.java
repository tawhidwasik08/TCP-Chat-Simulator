package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 6788);

        System.out.println("connected to server");

        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));

        System.out.print("Enter Name: ");
        Scanner sc = new Scanner(System.in);
        String client_name = sc.nextLine();
        dos.writeBytes(client_name + '\n');

        ReaderThread r = new ReaderThread(clientSocket, dis, dos);
        WriterThread w = new WriterThread(clientSocket, dis, dos);
        r.start();
        w.start();

    }

}
