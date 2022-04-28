package com.generation_alpha.client;

import com.generation_alpha.locations.Structure;
import com.generation_alpha.locations.Territory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JsonParserTest {

    @Before
    public void init() throws Exception {
    }

    @Test
    public void readJson() {
        String name = "home";
        Map<String, Object> map = JsonParser.locationReader(name);

        List<Map<String, Object>> territories = (List) map.get("territories");
        String start = "";
        Territory terr = new Territory();

        for (Map<String, Object> territory : territories) {
            if (territory.get("name").equals(name)) {
                start = territory.get("start").toString();

                terr.setName(territory.get("name").toString());
                terr.setDescription(territory.get("description").toString());
                terr.setImage(territory.get("description").toString());
                terr.setStart(new Structure(territory.get("start").toString()));
                terr.setLocations((List)territory.get("locations"));
            }
        }
        System.out.println(terr.getLocations());
    }
}