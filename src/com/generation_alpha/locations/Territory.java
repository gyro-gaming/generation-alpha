package com.generation_alpha.locations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.generation_alpha.characters.Villain;

class Territory implements Location {
    private String name;
    private String description;
    private Map<Direction, String> map;
    private Villain villain;

    public Territory(String name) {
        setName(name);
        Territory territory = jsonReader(name);
        setDescription(territory.getDescription());
        setMap(territory.getMap());
        setVillain(territory.getVillain());
    }

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

    public void setMap(Map<Direction, String> map) {
        this.map = map;
    }

    public Map<Direction, String> getMap() {
        return map;
    }

    public void setVillain(Villain villain) {
        this.villain = villain;
    }

    public Villain getVillain() {
        return villain;
    }

    private Territory jsonReader(String name) {
        String file = "";
        ObjectMapper mapper = new ObjectMapper();
        List<Territory> territories = new ArrayList<>();

        try {
            territories = mapper.readValue(new File(file), new TypeReference<List<Territory>>(){});
        } catch (IOException e) {
            System.out.println(e);
        }

        for (Territory territory : territories) {
            if (territory.getName().equals(name)) {
                return territory;
            }
        }
        return null;
    }
}