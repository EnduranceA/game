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

    public String sendUsername(String name) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "get username");
        payload.put("username", name);
        response.put("payload", payload);
        return response.toJSONString();
    }

}
