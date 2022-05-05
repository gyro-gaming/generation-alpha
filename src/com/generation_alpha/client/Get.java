package com.generation_alpha.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.StrengthBoost;
import com.generation_alpha.locations.Structure;

import java.util.List;
import java.util.Map;

class Get {
    public static String forGet(GameBoard gameBoard, String name) {
        ObjectMapper mapper = new ObjectMapper();
        Gyro gyro = gameBoard.getGyro();
        Structure location = gyro.getLocation();

        if (name.equals("null")) {
            return "There is no item at this location.\n\n";
        }

        Item item = gyro.getLocation().getItem();

        if (!item.getName().equals(name)) {
            return "That item is not in this building.\n\n";
        }

        if (item instanceof StrengthBoost || item instanceof HealthBoost) {
            gyro.addItem(item);
            location.setItem(new StrengthBoost("null"));
        } else if (item instanceof PowerItem) {
            gyro.addPower((PowerItem)item);
            location.setItem(new PowerItem("null"));
        }

        Map<String, Object> map = mapper.convertValue(location, Map.class);
        map.remove("item");
        Structure newStructure = gameBoard.getGamePlay().removeItemFromStructure(map, location);
        gyro.setLocation(newStructure);

        List<Structure> locations = gameBoard.getTerritory().getLocations();
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getName().equals(newStructure.getName())) {
                locations.remove(i);
                locations.add(newStructure);
            }
        }

        return String.format("%s was added to %s's bag.\n", item.getName(), gyro.getName());
    }
}
