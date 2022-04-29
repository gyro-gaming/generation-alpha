package com.generation_alpha;

import com.generation_alpha.client.GamePlay;
import com.generation_alpha.client.TextParser;
import com.generation_alpha.locations.Direction;
import com.generation_alpha.locations.Structure;
import com.generation_alpha.locations.Territory;

import java.util.List;

class Main {
    public static void main(String[] args) {
        GamePlay gamePlay = new GamePlay();
        Territory territory = gamePlay.getTerritory("Home");
        List<Structure> locations = territory.getLocations();
        for (Structure location : locations) {
            System.out.println(location.getMap());
        }
        TextParser tp = new TextParser();
        String input = tp.promptInput();
        System.out.println(tp.forGo(input));
    }
}
