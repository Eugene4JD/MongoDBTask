import java.io.IOException;
import java.util.Map;

public interface IJsonSmartSerializer {
    String smartSerialize() throws IOException;

    String smartSerializePrettyJson() throws IOException;

    void setNotParsedJson(String json);

}
