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
        response.put("header", "main word");
        response.put("word", mainWord);
        return response.toJSONString();
    }

}
