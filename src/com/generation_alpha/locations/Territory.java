package com.generation_alpha.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Territory implements Location {
    private String type;
    private String name;
    private String description;
    private String image;
    private Map<Direction, String> map;
    private Structure start;
    private List<Structure> locations;

    // getters and setters
    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

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
    // end getters and setters

    /**
     * method to convert a List of Map of String keys and Object values from Jackson
     * processing into a more usable List of Locations
     * @param locations
     * @return List<Structure>
     */
    public List<Structure> convertToLocationList(List<Map<String, Object>> locations) {
        List<Structure> locationList = new ArrayList<>();
        GamePlay gamePlay = new GamePlay();
        for (Map<String, Object> locationMap : locations) {
            String name = null;
            String itemName = null;
            String itemLoc = null;
            try {
                name = ((Map<String, Object>)locationMap.get("npc")).get("name").toString();
            } catch (NullPointerException e) {}
            try {
                name = ((Map<String, Object>)locationMap.get("villain")).get("name").toString();
            } catch (NullPointerException e) {}
            try {
                itemName = ((Map<String, Object>)locationMap.get("item")).get("name").toString();
                itemLoc = ((Map<String, Object>)((Map<String, Object>)locationMap.get("item")).get("location")).get("name").toString();
            } catch (ClassCastException e) {

            } catch (NullPointerException e) {}

            if (locationMap.get("type").equals("Building")) {
                Building building = new Building(
                        locationMap.get("type").toString(),
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        ((Map<String, Object>) locationMap.get("directions") != null) ? (Map<String, Object>) locationMap.get("directions") : (Map<String, Object>) locationMap.get("map"),
                        (name == null) ? gamePlay.getCharacters(locationMap.get("character").toString()) : gamePlay.getCharacters(name),
                        (itemName == null) ? gamePlay.getItems(locationMap.get("item").toString()) : (itemLoc.equals(locationMap.get("name").toString())) ? gamePlay.getItems(itemName) : null
                );
                locationList.add(building);
            } else if (locationMap.get("type").equals("Dojo")) {
                Dojo dojo = new Dojo(
                        locationMap.get("type").toString(),
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        ((Map<String, Object>) locationMap.get("directions") != null) ? (Map<String, Object>) locationMap.get("directions") : (Map<String, Object>) locationMap.get("map"),
                        (name == null) ? gamePlay.getCharacters(locationMap.get("character").toString()) : gamePlay.getCharacters(name)
                );
                locationList.add(dojo);
            }
        }
        return locationList;
    }
}