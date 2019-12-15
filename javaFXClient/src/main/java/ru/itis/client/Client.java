package ru.itis.client;

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

public class Client extends Thread {

    private final int PORT = 1337;
    private String host = "localhost";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String userName;

    private RequestService requestService;
    private ResponseService responseService;

    public static String mainWord;

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
            jsonService = new JSONService();
            this.start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String str = reader.readLine();
                    readRequest(str);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        private void readRequest(String str) {
            try {
                switch (str) {
                    case ("login"):
//                        System.out.print("YOUR LOGIN: ");
//                        String login = reader.readLine();
//                        System.out.print("YOUR PASSWORD: ");
//                        String password = reader.readLine();
//                        writer.println(jsonService.getLoginJSON(login, password));
                        break;
                    case ("quit"):
                        stopThread();
                        break;
                }
            }
            catch (IOException e) {
                stopThread();
            }
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
                switch (object.get("header").toString()){
                    case ("main word"):
                        String mainWord = object.get("word").toString();
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
                writer.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String read() {
        String input = null;
        try {
            input = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void write(String output) {
        out.println(output);
    }

}
