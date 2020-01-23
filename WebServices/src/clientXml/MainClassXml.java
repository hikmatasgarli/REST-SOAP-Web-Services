package clientXml;
// REST Client XML

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainClassXml {
    public static void main(String[] args) {
        try {
            String usr = "admin";
            String pwd = "admin";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", usr);
            jsonObject.put("password", pwd);

            String url = "http://localhost:8080/webservices/api/webservices/login";

            String response = Method.sendPost(url, jsonObject.toJSONString());
            System.out.println(response);

            InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);  //hara yukleyir

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();


            User user = (User) jaxbUnmarshaller.unmarshal(stream);
            System.out.println(user);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
