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

    public String getCommandStartGame() {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "start game");
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


    public String sendNameOfWinner(String userName) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("name", userName);
        payload.put("command", "get name of winner");
        response.put("payload", payload);
        return response.toJSONString();
    }
}
