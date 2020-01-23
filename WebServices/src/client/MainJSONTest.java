package client;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class MainJSONTest {

    public static void main(String[] args) {
        try {

            JSONObject jsonObject = new JSONObject();
            String url = "http://localhost:8080/webservices/api/webservices/getStudentList?token=549afc76-bf71-46d5-b452-46c34b726540";

            String response = Method.sendGet(url);
            System.out.println(response);


            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(response);
            String name = (String) obj.get("name");
            System.out.println(name);

            JSONArray jsonArray = (JSONArray) obj.get("getStudentList");
            List<JSONObject> jsonObjects = new ArrayList<>(jsonArray);
            for(JSONObject jsonObject1 : jsonObjects) {
                System.out.println(jsonObject1.get("id "+" name"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
