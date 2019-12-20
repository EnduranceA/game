package itis.client;
import itis.controller.MenuFXMLController;
import itis.helper.RequestService;
import itis.helper.ResponseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String userName;
    public static ResponseService responseService;
    public static long isOver;

    //для обозначения состояние игрока
    public static boolean status;
    public static String mainWord = "";
    public static String rival = "";
    public static String nameOfWinner;
    public static boolean isCheckWord;

    public Client(){
        try {
            this.socket = new Socket("localhost", 1337);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.userName = MenuFXMLController.name;
            new RequestService(in);
            responseService = new ResponseService(out);
            start();
            } catch (IOException e1) {
            throw new IllegalArgumentException(e1);
        }
    }

    private void start(){
        responseService.sendUserNameToServer(userName);
    }
}

