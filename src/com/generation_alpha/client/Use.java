package com.generation_alpha.client;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.StrengthBoost;

import java.util.List;

public class Use {

    public static String forUse (GameBoard gameBoard, String name) {
        Gyro gyro = gameBoard.getGyro();
        Item item = gameBoard.getGamePlay().getItems(name);
        if(item instanceof StrengthBoost) {
            StrengthBoost strBst = (StrengthBoost)item;
            int boost = strBst.getStrengthBoost();
            gyro.setStrength(gyro.getStrength() + boost);
            List<Item> items = gyro.getItems();
            for (Item i : items) {
                if(i.getName().equals(item.getName())) {
                    gyro.useItem(i);
                }
            }
            return String.format("You used a %s and gained %d strength.", item.getName(), ((StrengthBoost) item).getStrengthBoost());
        }
        else if (item instanceof HealthBoost) {
            HealthBoost hltBst = (HealthBoost)item;
            int boost = hltBst.getHealthBoost();
            gyro.setHealth(gyro.getHealth() + boost);
            List<Item> items = gyro.getItems();
            for (Item i : items) {
                if (i.getName().equals(item.getName())) {
                    gyro.useItem(i);
                }
            }
            return String.format("You used a %s and gained %d health.", item.getName(), ((HealthBoost) item).getHealthBoost());
        }
        return String.format("%s is not in your bag.", name);
    }
}
