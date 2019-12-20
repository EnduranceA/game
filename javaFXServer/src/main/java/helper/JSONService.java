package helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONService {

    private JSONParser parser;

    public JSONService() {
        this.parser = new JSONParser();
    }

    public String sendMainWord(String mainWord) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "get main word");
        payload.put("word", mainWord);
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendResultOfCheckWord(boolean result) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "check result");
        payload.put("check", result);
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendUsername(String name) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "get username");
        payload.put("username", name);
        response.put("payload", payload);
        return response.toJSONString();
    }


    public String getCommandStartGameWithYourTurn() {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "start game");
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String getCommandStartGameWithWait() {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "start game wait");
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String getCommandWait() {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "wait");
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String getCommandYourTurn() {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "your turn");
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendMessage(String message) {
        JSONObject response = new JSONObject();
        response.put("header", "message");
        JSONObject payload = new JSONObject();
        payload.put("text", message);
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendCommand(boolean value) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("the end", value);
        payload.put("command", "the end of game");
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendStatus(int status) {
        JSONObject response = new JSONObject();
        response.put("header", "status");
        JSONObject payload = new JSONObject();
        payload.put("status", status);
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendIsOver(int isOver) {
        JSONObject response = new JSONObject();
        response.put("header", "isOver");
        JSONObject payload = new JSONObject();
        payload.put("is over", isOver);
        response.put("payload", payload);
        return response.toJSONString();
    }


    public String sendNameOfWinner(String userName) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("name", userName);
        payload.put("command", "get name of winner");
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendRating(int rating) {
        JSONObject response = new JSONObject();
        response.put("header", "rating");
        JSONObject payload = new JSONObject();
        payload.put("rating", rating);
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendRatingOfRival(int rating) {
        JSONObject response = new JSONObject();
        response.put("header", "rival rating");
        JSONObject payload = new JSONObject();
        payload.put("rating", rating);
        response.put("payload", payload);
        return response.toJSONString();
    }
}
