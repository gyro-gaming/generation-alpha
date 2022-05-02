package com.generation_alpha.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Territory implements Location {
    private String name;
    private String description;
    private String image;
    private Map<Direction, String> map;
    private Structure start;
    private List<Structure> locations;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setMap(Map<String, Object> objectMap) {
        this.map = GamePlay.convertMap(objectMap);
    }

    @Override
    public Map<Direction, String> getMap() {
        return map;
    }

    public void setStart(Structure start) {
        this.start = start;
    }

    public Structure getStart() {
        return start;
    }

    public void setLocations(List<Map<String, Object>> locationsMapList) {
        List<Structure> newLocationList = convertToLocationList(locationsMapList);
        this.locations = newLocationList;
    }

    public List<Structure> getLocations() {
        return locations;
    }

    public List<Structure> convertToLocationList(List<Map<String, Object>> locations) {
        List<Structure>locationList = new ArrayList<>();
        GamePlay gamePlay = new GamePlay();
        for (Map<String, Object> locationMap : locations) {
            if (locationMap.get("type").equals("Building")) {
                Building building = new Building(
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        (Map<String, Object>) locationMap.get("directions"),
                        gamePlay.getCharacters(locationMap.get("character").toString()),
                        gamePlay.getItems(locationMap.get("item").toString())
                );
                locationList.add(building);
            } else if (locationMap.get("type").equals("Dojo")) {
                Dojo dojo = new Dojo(
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        (Map<String, Object>) locationMap.get("directions"),
                        gamePlay.getCharacters(locationMap.get("character").toString())
                );
                locationList.add(dojo);
            }
        }
        return locationList;
    }
}