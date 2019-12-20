package itis.helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import itis.client.Client;
import itis.manager.SceneManager;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestService extends Thread {

    private JSONParser jsonParser;
    private BufferedReader in;

    public RequestService(BufferedReader in) {
        this.jsonParser = new JSONParser();
        this.in = in;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String request = in.readLine();
                System.out.println(request);
                readRequest(request);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public void readRequest(String json) {
        JSONObject object;
        try {
            object = (JSONObject) jsonParser.parse(json);
            JSONObject payload = (JSONObject) jsonParser.parse(object.get("payload").toString());
            switch (object.get("header").toString()) {
                case ("command"):
                    //определяем, какая команда
                    switch (payload.get("command").toString()) {
                        case ("check result"):
                            Client.isCheckWord = (boolean) payload.get("result");
                            break;
                        case ("get name of winner"):
                            Client.nameOfWinner = payload.get("name").toString();
                            SceneManager.setEndScene();
                            break;
                        case ("get main word"):
                            Client.mainWord = payload.get("word").toString();
                            System.out.println(Client.mainWord);
                            break;
                        case ("get username"):
                            Client.rival = payload.get("username").toString();
                            break;
                        case ("start game"):
                            SceneManager.setGameScene(false);
                            break;
                        case ("start game wait"):
                            SceneManager.setGameScene(true);
                            break;
                        case("your turn"):
                            SceneManager.gameController.yourTurn();
                            break;
                        case ("wait"):
                            SceneManager.gameController.waitYourTurn();
                            break;
                    }
                    break;
                case ("status"):
                    Client.status = (long) (payload.get("status")) == 1;
                    break;
                case ("isOver"):
                    Client.isOver = (long) payload.get("is over");
                    break;
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

    }
}