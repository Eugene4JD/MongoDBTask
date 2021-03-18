import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonSmartSerializer implements IJsonSmartSerializer {
    private Map<String, Object> result;
    private String notParsedJson;
    private ObjectMapper objectMapper;
    private MapType type;


    public JsonSmartSerializer(String notParsedJson) {
        this.result = new LinkedHashMap<>();
        this.notParsedJson = notParsedJson;
        this.objectMapper = new ObjectMapper();
        this.type = this.objectMapper.getTypeFactory().constructMapType(
                Map.class, String.class, Object.class);
    }

    @Override
    public String smartSerialize() throws IOException {
        Map<String, Object> data = objectMapper.readValue(this.notParsedJson, this.type);
        this.recursionParsing(data, "");
        String parsedJson = this.objectMapper.writeValueAsString(this.result);
        this.reset();
        return parsedJson;
    }

    @Override
    public String smartSerializePrettyJson() throws IOException {
        Map<String, Object> data = objectMapper.readValue(this.notParsedJson, this.type);
        this.recursionParsing(data, "");
        String parsedJson = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.result);
        this.reset();
        return parsedJson;
    }

    @Override
    public void setNotParsedJson(String notParsedJson) {
        this.notParsedJson = notParsedJson;
    }

    private void recursionParsing(Map<String, Object> map, String root) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            try {
                Map<String, Object> stringObjectMap = (Map<String, Object>) entry.getValue();
                this.recursionParsing(stringObjectMap, root + entry.getKey() + ".");
            } catch (ClassCastException exception) {
                result.put(root + entry.getKey(), entry.getValue());
            }
        }
    }

    private void reset() {
        this.result.clear();
    }
}
