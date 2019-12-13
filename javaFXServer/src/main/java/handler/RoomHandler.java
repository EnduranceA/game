package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RoomHandler extends Thread{

    private Socket client1;
    private Socket client2;

    //ввод и вывод данных для 1 клиента
    private PrintWriter out1;
    private BufferedReader in1;

    //ввод и вывод данных для 2 клиента
    private PrintWriter out2;
    private BufferedReader in2;

    public RoomHandler(Socket client1, Socket client2) {
       try {
           this.client1 = client1;
           this.client2 = client2;
           this.out1 = new PrintWriter(client1.getOutputStream(), true);
           this.in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));

           this.out2 = new PrintWriter(client1.getOutputStream(), true);
           this.in2 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
           this.start();
       }
       catch (IOException e) {
           throw new IllegalArgumentException(e);
       }
    }


    @Override
    public void run() {

    }
}
