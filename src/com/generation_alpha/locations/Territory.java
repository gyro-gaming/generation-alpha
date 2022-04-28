package com.generation_alpha.locations;

import java.util.List;
import java.util.Map;

public class Territory extends Location {
    private String name;
    private String description;
    private String image;
    private Structure start;
    private List<Map<String, Object>> locations;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setStart(Structure start) {
        this.start = start;
    }

    public Structure getStart() {
        return start;
    }

    public void setLocations(List<Map<String, Object>> locations) {
        this.locations = locations;
    }

    public List<Map<String, Object>> getLocations() {
        return locations;
    }
}