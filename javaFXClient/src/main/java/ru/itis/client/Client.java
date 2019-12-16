package ru.itis.client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.itis.controller.MenuFXMLController;
import ru.itis.helper.JSONService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client  {

    private final int PORT = 1337;
    private String host = "localhost";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String userName;

    private RequestService requestService;
    private ResponseService responseService;

    public static String mainWord;
    private Stage primaryStage;

    //для обозначения состояния игрока(текущего хода)
    public static boolean status ;

    public Client() {
        try {
            this.socket = new Socket(host, PORT);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.userName = MenuFXMLController.name;
            this.requestService = new RequestService();
            this.responseService = new ResponseService();
            this.primaryStage = MainApp.primaryStage;
            //1 означает, что игрок ходит первым
//            status = Integer.parseInt(in.readLine()) == 1;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    //СЕРВИС ДЛЯ ФОРМИРОВАНИЯ ЗАПРОСОВ НА СЕРВЕР
    public class RequestService extends Thread {

        private JSONService jsonService;

        public RequestService() {
            this.jsonService = new JSONService();
            this.start();
        }

        @Override
        public void run() {
            //TODO: отправить имя игрока на сервер
//            try {
//                while (true) {
//                    String str = reader.readLine();
//                    readRequest(str);
//                }
//            } catch (IOException e) {
//                throw new IllegalArgumentException(e);
//            }
        }

        private void readRequest(String str) {
//            try {
//                switch (str) {
//                    case ("login"):
//
//                }
//            }

        }
    }

    //СЕРВИС ДЛЯ ЧТЕНИЯ ОТВЕТОВ ОТ СЕРВЕРА
    public class ResponseService extends Thread {

        private JSONParser jsonParser;

        public ResponseService() {
            jsonParser = new JSONParser();
            this.start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String str = in.readLine();
                    readResponse(str);
                }
            }
            catch (IOException e) {
                throw new IllegalArgumentException(e);
            }

        }

        public void readResponse(String json) {
            JSONObject object;
            try {
                object = (JSONObject) jsonParser.parse(json);
                JSONObject payload = (JSONObject) jsonParser.parse(object.get("payload").toString());
                switch (object.get("header").toString()){
                    case ("command"):
                        //определяем, какая команда
                        switch (payload.get("command").toString()) {
                            case ("get main word"):
                                mainWord = payload.get("word").toString();
                                break;
                            case ("start game"):
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
                                        primaryStage.setTitle("Waiting");
                                        primaryStage.show();
                                    }
                                });
                                break;

                        }
                        break;
                }
            } catch (ParseException e){
                throw new IllegalArgumentException(e);
            }

        }
    }

    public void stopThread() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
