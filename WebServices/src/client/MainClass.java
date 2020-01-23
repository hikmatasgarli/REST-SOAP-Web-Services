package client;

// Rest Client JSON
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainClass {
    public static void main(String[] args) {
        try {
            String user = "admin";
            String pwd = "admin";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", user);
            jsonObject.put("password", pwd);

            String url = "http://localhost:8080/webservices/api/webservices/login";

            String response = Method.sendPost(url, jsonObject.toJSONString());
            System.out.println(response);

            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(response);
            String userId = (String) obj.get("userId");
            String username = (String) obj.get("username");
            String companyName = (String) obj.get("companyName");
            String token = (String) obj.get("token");
            JSONObject objStatus = (JSONObject) obj.get("status");
            String statusCode = (String) objStatus.get("statusCode");
            String statusMessage = (String) objStatus.get("statusMessage");
            // System.out.println(statusCode +" -- "+statusMessage );
            Long code = Long.valueOf(statusCode.trim());
            System.out.println(companyName + " -- " + token);

            if (code == 1) {
                System.out.println(companyName + " -- " + token);
                System.out.println(username);
                System.out.println("Success");
            } else if (code == 109) {
                System.out.println("Session is already exist!");
            } else {
                System.out.println("Internal Exception");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
