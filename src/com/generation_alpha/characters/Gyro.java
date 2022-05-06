package com.generation_alpha.characters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.StrengthBoost;
import com.generation_alpha.locations.Direction;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Location;
import com.generation_alpha.locations.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gyro extends Fighter {
    private String name;
    private List<String> quote;
    private String image;
    private Structure location;
    private int strength;
    private int health;
    private List<PowerItem> powers;
    private List<Item> items;

    private static Gyro instance = new Gyro();

    private Gyro() {}

    public static Gyro getInstance() {
        instance.setStrength(10);
        instance.setHealth(100);
        instance.setItems(new ArrayList<>());
        instance.setPowers(new ArrayList<>());
        return instance;
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
    public void setQuote(List<String> quote) {
        this.quote = quote;
    }

    @Override
    public List<String> says() {
        return quote;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getImage() {
        return image;
    }


    public void setLocation(Structure location) {
        this.location = location;
    }

    @Override
    public Structure getLocation() {
        return location;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setPowers(List<PowerItem> powerItems) {
        this.powers = powerItems;
    }

    public void addPower(PowerItem powerItem) {
        powers.add(powerItem);
    }

    @Override
    public void usePower(PowerItem powerItem) {
        powers.remove(powerItem);
    }

    @Override
    public List<PowerItem> getPowers() {
        return powers;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void useItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public static Structure forGo(GameBoard gameBoard, String input) {
        Gyro gyro = gameBoard.getGyro();
        Location start = gyro.getLocation();
        GamePlay gamePlay = gameBoard.getGamePlay();
        List<Structure> locations = gameBoard.getTerritory().getLocations();
        for (Structure location : locations) {
            if (location.getName().equals(start.getName())) {
                start = location;
            }
        }
        Map<Direction, String> map = start.getMap();
        Direction direction = gamePlay.getDirection(input);
        if (map.containsKey(direction)) {
            String locName = map.get(direction);
            int index = gamePlay.moveGyro(gyro, locName, locations);
            return locations.get(index);
        } else {
            System.out.println("You may not travel in that direction!\n\n");
            return null;
        }
    }

    public static String forLook(GameBoard gameBoard, String input) {
        Location start = gameBoard.getGyro().getLocation();
        GamePlay gamePlay = gameBoard.getGamePlay();
        List<Structure> locations = gameBoard.getTerritory().getLocations();
        for (Structure location : locations) {
            if (location.getName().equals(start.getName())) {
                start = location;
            }
        }
        Map<Direction, String> map = start.getMap();
        Direction direction = gamePlay.getDirection(input);
        if (map.containsKey(direction)) {
            String locName = map.get(direction);
            return gamePlay.getLocation(locName, locations);
        } else {
            return "There is nothing to see in that direction.";
        }
    }

    public static String forGet(GameBoard gameBoard, String name) {
        ObjectMapper mapper = new ObjectMapper();
        Gyro gyro = gameBoard.getGyro();
        Structure location = gyro.getLocation();

        List<Structure> locations = gameBoard.getTerritory().getLocations();

        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getName().toString().equalsIgnoreCase(location.getName())) {
                location = locations.get(i);
            }
        }

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

        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getName().equals(newStructure.getName())) {
                locations.remove(i);
                locations.add(newStructure);
            }
        }

        return String.format("%s was added to %s's bag.\n", item.getName(), gyro.getName());
    }

    public static String forInspect(GameBoard gameBoard){
        Gyro gyro = gameBoard.getGyro();
        List<Item> items = gyro.getItems();
        StringBuilder sb = new StringBuilder();
        for (Item item : items){
            sb.append(item.getName() + " " + item.getDescription() + "\n");
        }
        return sb.toString();
    }

    public static String forAsk(GameBoard gameBoard, String name) {
        Gyro gyro = gameBoard.getGyro();
        List<String> quotes = gyro.getLocation().getCharacter().says();
        int len = quotes.size();
        int index = (int)(Math.random() * len);
        return quotes.get(index);
    }

    public static String forUse (GameBoard gameBoard, String name) {
        Gyro gyro = gameBoard.getGyro();
        Item item = gameBoard.getGamePlay().getItems(name);
        if(item instanceof StrengthBoost) {
            if (gyro.getStrength() == 100) {
                return "You are already too pumped up to add more strength";
            }
            StrengthBoost strBst = (StrengthBoost)item;
            int boost = strBst.getStrengthBoost();
            gyro.setStrength(gyro.getStrength() + boost);
            List<Item> items = gyro.getItems();
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals(name)) {
                    gyro.useItem(items.get(i));
                }
            }
            return String.format("You used a %s and gained %d strength.\nYou now have %d strength.", item.getName(), ((StrengthBoost) item).getStrengthBoost(), gyro.getStrength());
        }
        else if (item instanceof HealthBoost) {
            if (gyro.getHealth() == 100) {
                return "You cannot have more than 100 health at this time";
            }
            HealthBoost hltBst = (HealthBoost)item;
            int boost = hltBst.getHealthBoost();
            gyro.setHealth(gyro.getHealth() + boost);
            List<Item> items = gyro.getItems();
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals(name)) {
                    gyro.useItem(items.get(i));
                }
            }
            return String.format("You used a %s and gained %d health.\nYou now have %d health.", item.getName(), ((HealthBoost) item).getHealthBoost(), gyro.getHealth());
        }
        return String.format("%s is not in your bag.", name);
    }
}