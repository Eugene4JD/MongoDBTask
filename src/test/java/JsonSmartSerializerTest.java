import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonSmartSerializerTest {

    private IJsonSmartSerializer jsonSmartSerializer;

    public JsonSmartSerializerTest()
    {
        this.jsonSmartSerializer = new JsonSmartSerializer("");
    }

    @org.junit.jupiter.api.Test
    void smartSerializeTest1() throws IOException {
        jsonSmartSerializer.setNotParsedJson("{\n" +
                "    \"name\": \"John\",\n" +
                "    \"age\": 30,\n" +
                "    \"cars\": {\n" +
                "        \"abd\": {\n" +
                "            \"oda\" : \"yes\"\n" +
                "        },\n" +
                "        \"car1\": \"Ford\",\n" +
                "        \"car2\": \"BMW\",\n" +
                "        \"car3\": \"Fiat\"\n" +
                "    }\n" +
                "}");

        assertEquals("{\"name\":\"John\",\"age\":30,\"cars.abd.oda\":\"yes\",\"cars.car1\":\"Ford\",\"cars.car2\":\"BMW\",\"cars.car3\":\"Fiat\"}",jsonSmartSerializer.smartSerialize());
    }


    @org.junit.jupiter.api.Test
    void smartSerializeTest2() throws IOException {
        jsonSmartSerializer.setNotParsedJson(" {\n" +
                "    \"a\": 1,\n" +
                "    \"b\": true,\n" +
                "    \"c\": {\n" +
                "        \"d\": 3,\n" +
                "        \"e\": \"test\"\n" +
                "    }\n" +
                " }");
        assertEquals("{\"a\":1,\"b\":true,\"c.d\":3,\"c.e\":\"test\"}",jsonSmartSerializer.smartSerialize());
    }
}