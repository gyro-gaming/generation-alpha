package com.generation_alpha;

import com.generation_alpha.client.TextParser;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Location;
import com.generation_alpha.locations.Territory;

class Main {
    public static void main(String[] args) {
        TextParser textParser = new TextParser();
        GamePlay gamePlay = new GamePlay();
        Territory territory = gamePlay.getTerritory("Home");
        System.out.println(territory.getMap());
        Location location = territory.getLocations().get(0);
        System.out.println(location.getMap());
    }
}
