package ru.itis.client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.itis.controller.MenuFXMLController;
import ru.itis.helper.JSONService;

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
    private JSONService jsonService;

    //для обозначения состояние игрока
    public boolean status;
    public long movesOfRival;
    public static String mainWord;
    public int moves;
    public String rival;
    public String nameOfWinner;
    private Stage primaryStage;

    public Client(){
        try {
            this.socket = new Socket("localhost", 1337);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.userName = MenuFXMLController.name;
            this.moves = 3;
            this.status = Integer.parseInt(in.readLine()) == 1;
            this.jsonService = new JSONService();
            this.primaryStage = MainApp.primaryStage;
            mainWord = getMainWord();
            //отправить имя игрока на сервер
            send(userName);
            rival = getResponse();
            //отправить количество ходов на сервер
            send(String.valueOf(moves));
            movesOfRival = Integer.parseInt(getResponse());
            getStartGame();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void send(String str) {
        out.println(str);
    }

    public String getResponse() {
        String str;
        try {
            str = in.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return str;
    }

    private void getStartGame() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/room.fxml"));
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Game");
                primaryStage.show();
            }
        });
    }

    public String getMainWord() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

//    public class RequestService extends Thread{
//        private JSONParser jsonParser;
//
//        public RequestService() {
//            this.jsonParser = new JSONParser();
//            this.start();
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    readResponse(in.readLine());
//                } catch (IOException e) {
//                    throw new IllegalArgumentException(e);
//                }
//            }
//        }
//
//        public void readResponse(String json) {
//            JSONObject object;
//            try {
//                object = (JSONObject) jsonParser.parse(json);
//                JSONObject payload = (JSONObject) jsonParser.parse(object.get("payload").toString());
//                switch (object.get("header").toString()){
//                    case ("command"):
//                        //определяем, какая команда
//                        switch (payload.get("command").toString()) {
//                            case ("get name of winner"):
//                                nameOfWinner = payload.get("name").toString();
//                                break;
//                            case ("the end of game") :
//                                isOver = (boolean) payload.get("the end");
//                                break;
//                            case ("get main word"):
//                                mainWord = payload.get("word").toString();
//                                System.out.println(mainWord);
//                                break;
//                            case ("get username"):
//                                rival = payload.get("username").toString();
//                                break;
//                        }
//                        break;
//                    case ("status"):
//                        status = ((long) payload.get("status")) == 1;
//                        break;
//                    case ("moves"):
//                        movesOfRival = (long) payload.get("moves");
//                        break;
//                    case ("message"):
//                        switch (payload.get("text").toString()) {
//                            case ("successfully"):
//                                message = 1;
//                                break;
//                            case ("unsuccessfully"):
//                                message = 0;
//                                break;
//                        }
//                        break;
//                }
//            } catch (ParseException e){
//                throw new IllegalArgumentException(e);
//            }
//
//        }
//    }
}

