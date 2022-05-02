package com.generation_alpha;

import com.generation_alpha.client.TextParser;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Territory;

class Main {
    public static void main(String[] args) {
        TextParser textParser = new TextParser();
        GamePlay gamePlay = new GamePlay();
        Territory territory = gamePlay.getTerritory("Home");
        System.out.println(territory.getMap());
        territory = gamePlay.getTerritory("Talon");
        System.out.println(territory.getMap());
        // territory = gamePlay.getTerritory("Hunter X");
        // System.out.println(territory.getMap());
        territory = gamePlay.getTerritory("Troll");
        System.out.println(territory.getMap());
        // territory = gamePlay.getTerritory("Master Yamamoto");
        // System.out.println(territory.getMap());
    }
}
