package com.generation_alpha.locations;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Map;

public class JsonParserTest {
    private String jsonString;
    private String file;

    @Before
    public void init() {
        jsonString = "{\"results\":[{\"key1a\":\"value1a\",\"key2a\":{\"key2a1\":" +
                "\"value2a1\"},\"key3a\":[\"value3a1\"]},{\"key1b\":\"value1b\"," +
                "\"key2b\":{\"key2b1\":\"value2b1\"},\"key3b\": [\"value3b1\"]}]}";
        String file = "JsonObjects/mockJson.json";
    }

    @Test
    public void parseJsonAssertsStringAndFileEqual() {
        Map<String, Object> mapString = JsonParser.parseJson(jsonString);
        Map<String, Object> mapFile = JsonParser.parseJson(file);

        assertEquals(mapString, mapFile);
    }
}