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
