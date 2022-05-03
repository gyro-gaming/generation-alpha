package com.generation_alpha.client;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.items.Item;

import java.util.List;

class Inspect {

    public static String forInspect(GameBoard gameBoard){
        Gyro gyro = gameBoard.getGyro();
        List<Item> items = gyro.getItems();
        StringBuilder sb = new StringBuilder();
        for (Item item : items){
            sb.append(item.getName() + " " + item.getDescription() + "\n");
        }
        return sb.toString();
    }
}
