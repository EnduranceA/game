package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final int PORT = 1337;
    private String host = "localhost";
    private Socket socket;
    private BufferedReader br;
    private PrintWriter os;

    //для обозначения состояния игрока(текущего хода)
    //true - его ход
    //false - ход противника
    public static boolean status ;

    public Client() {
        try {
            this.socket = new Socket(host, PORT);
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.os = new PrintWriter(socket.getOutputStream(), true);
            //this.status = Integer.parseInt(br.readLine()) == 1;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String read() {
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void write(String output) {
        os.println(output);
    }

}
