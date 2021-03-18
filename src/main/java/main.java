import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            if (true)
            {
                FilePermission permission = new FilePermission(args[0], "read");
                Object object = parser.parse(new FileReader(args[0]));
                JSONObject jsonObject = (JSONObject) object;
                IJsonSmartSerializer jsonSmartSerializer = new JsonSmartSerializer(jsonObject.toJSONString());
                System.out.println(jsonSmartSerializer.smartSerializePrettyJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
