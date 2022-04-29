package com.generation_alpha.locations;

import com.generation_alpha.client.GamePlay;

import java.util.List;
import java.util.Map;

public class Territory implements Location {
    private String name;
    private String description;
    private String image;
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

    public void setStart(Structure start) {
        this.start = start;
    }

    public Structure getStart() {
        return start;
    }

    public void setLocations(List<Map<String, Object>> locationsMapList) {
        GamePlay gamePlay = new GamePlay();
        List<Structure> newLocationList = gamePlay.convertToLocationList(locationsMapList);
        this.locations = newLocationList;
    }

    public List<Structure> getLocations() {
        return locations;
    }
}