package com.generation_alpha.client;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JsonParserTest {

    @Before
    public void init() {
    }

    @Test
    public void readJson() {
        String name = "home";
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/locations.json");

        List<Map<String, Object>> territories = (List) map.get("territories");

        System.out.println(territories);
    }
}