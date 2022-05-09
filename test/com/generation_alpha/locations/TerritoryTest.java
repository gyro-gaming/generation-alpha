package com.generation_alpha.locations;

import com.generation_alpha.client.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TerritoryTest {
    Territory territory;
    List<Map<String, Object>> locationList;

    @Before
    public void init () {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/locations.json");
        List<Map<String, Object>> territories = (List) map.get("territories");
        territory = new Territory();
        for (Map<String, Object> territoryMap : territories) {
            if (territoryMap.get("name").equals("Home")) {
                territory.setName(territoryMap.get("name").toString());
                territory.setDescription(territoryMap.get("description").toString());
                territory.setImage(territoryMap.get("image").toString());
                territory.setMap((Map<String, Object>)territoryMap.get("directions"));
                locationList = (List) territoryMap.get("locations");
                territory.setLocations(locationList);
            }
        }
    }

    @Test
    public void convertToLocationListAssertsNotEqualsAsListsContainNonEqualObjects() {
        assertNotEquals(territory.getLocations(), territory.convertToLocationList(locationList));
    }

    @Test
    public void convertToLocationListAssertsEqualLists() {
        List<Structure> getLocationsList = territory.getLocations();
        List<Structure> convertedToLocationListList = territory.convertToLocationList(locationList);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < getLocationsList.size(); i++) {
            set.add(getLocationsList.get(i).getName());
            set.add(convertedToLocationListList.get(i).getName());
        }
        assertEquals(convertedToLocationListList.size(), set.size());
    }
}