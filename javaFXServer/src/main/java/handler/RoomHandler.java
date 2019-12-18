package handler;

import helper.JSONService;
import models.Word;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.WordService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class RoomHandler extends Thread{

    private JSONParser parser;
    private ArrayList<Word> composedWords;

    private WordService wordService;
    private JSONService jsonService;

    //ввод и вывод данных для 1 клиента
    private PrintWriter out1;
    private BufferedReader in1;

    //ввод и вывод данных для 2 клиента
    private PrintWriter out2;
    private BufferedReader in2;

    public RoomHandler(Socket client1, Socket client2) {
       try {
           Socket client11 = client1;
           Socket client21 = client2;
           this.out1 = new PrintWriter(client1.getOutputStream(), true);
           this.in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));

           this.out2 = new PrintWriter(client2.getOutputStream(), true);
           this.in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
           this.composedWords = new ArrayList<>();
           this.parser = new JSONParser();
           this.jsonService = new JSONService();
           this.wordService = new WordService();
           this.start();
       }
       catch (IOException e) {
           throw new IllegalArgumentException(e);
       }
    }

    @Override
    public void run() {
        try {
            //генерируем главное слово для игры
            String mainWord = wordService.getMainWord().getName();
            //отправляем игрокам сгенерированное слово
            String jsonMainWord = jsonService.sendMainWord(mainWord);
            out1.println(jsonMainWord);
            out2.println(jsonMainWord);

            //отправляем игрокам имена соперников
            out2.println(jsonService.sendUsername(readJSONReq(in1.readLine())));
            out1.println(jsonService.sendUsername(readJSONReq(in2.readLine())));

            //отправляем информацию о начале игры(запуск room.fxml на стороне клиента)
            String jsonStartGame = jsonService.getCommandStartGame();
            out1.println(jsonStartGame);
            out2.println(jsonStartGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readJSONReq(String json) {
        try {
            JSONObject object = (JSONObject) parser.parse(json);
            JSONObject payload = (JSONObject) parser.parse(object.get("payload").toString());
            switch (object.get("header").toString()) {
                case ("command"):
                    switch (payload.get("command").toString()) {
                        case ("get username"):
                            return payload.get("username").toString();
                    }
                    break;
            }
            return null;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
