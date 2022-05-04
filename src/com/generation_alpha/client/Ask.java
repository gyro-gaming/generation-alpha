package com.generation_alpha.client;

import com.generation_alpha.characters.Gyro;

import java.util.List;

class Ask {

    public static String forAsk(GameBoard gameBoard, String name) {
        Gyro gyro = gameBoard.getGyro();
        List<String> quotes = gyro.getLocation().getCharacter().says();
        int len = quotes.size();
        int index = (int)(Math.random() * len);
        return quotes.get(index);
    }
}
