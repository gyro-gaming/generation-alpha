package com.generation_alpha.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class JsonParser {

    public static Map<String, Object> parseJson(String file) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        try {
            map = mapper.readValue(new File(file), new TypeReference<Map<String, Object>>(){});
        } catch (IOException e) {
            System.out.println(e);
        }
        return map;
    }
}
