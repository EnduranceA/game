package ru.itis.helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONService {

    private JSONParser parser;

    public JSONService() {
        this.parser = new JSONParser();
    }

    public String getUsername(String name) {
        JSONObject response = new JSONObject();
        response.put("header", "command");
        JSONObject payload = new JSONObject();
        payload.put("command", "get username");
        payload.put("username", name);
        response.put("payload", payload);
        return response.toJSONString();
    }
}
