package ru.itis.helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONService {

    private JSONParser parser;

    public JSONService() {
        this.parser = new JSONParser();
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

    public String sendMoves(long moves) {
        JSONObject response = new JSONObject();
        response.put("header", "moves");
        JSONObject payload = new JSONObject();
        payload.put("moves", moves);
        response.put("payload", payload);
        return response.toJSONString();
    }

    public String sendWord(String word) {
        JSONObject response = new JSONObject();
        response.put("header", "word");
        JSONObject payload = new JSONObject();
        payload.put("word", word);
        response.put("payload", payload);
        return response.toJSONString();
    }


}
