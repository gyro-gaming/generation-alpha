package com.generation_alpha.locations;

import com.generation_alpha.characters.NPC;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.PowerItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Territory implements Location {
    private String name;
    private String description;
    private String image;
    private Structure start;
    private List<Location> locations;

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

    public void setStart(Structure start) {
        this.start = start;
    }

    public Structure getStart() {
        return start;
    }

    public void setLocations(List<Map<String, Object>> locationsMapList) {
        List<Location> newLocationList = convertToLocationList(locationsMapList);
        this.locations = newLocationList;
    }

    public List<Location> getLocations() {
        return locations;
    }

    private List<Location> convertToLocationList(List<Map<String, Object>> locations) {
        List<Location> locationList = new ArrayList<>();

        for (Map<String, Object> locationMap : locations) {
            if (locationMap.get("type").equals("Building")) {
                System.out.println(locationMap.toString());
                Building building = new Building(
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        (Map<String, Object>) locationMap.get("directions"),
                        new NPC(locationMap.get("character").toString()),
                        null // this needs to be changed to accept either health or strength boosts
                );
                locationList.add(building);
            } else if (locationMap.get("type").equals("Dojo")) {
                Dojo dojo = new Dojo(
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        (Map<String, Object>) locationMap.get("directions"),
                        new Villain(locationMap.get("character").toString()),
                        new PowerItem(locationMap.get("item").toString())
                );
                locationList.add(dojo);
            }
        }
        return locationList;
    }
}