package com.generation_alpha.client;

import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.StrengthBoost;

class Get {
    public static String forGet(String inputLine){
        String item = "";
        // Conditions - Hardcoded items based off possible inputs
        switch (inputLine) {
            case "strength":
                StrengthBoost sb = new StrengthBoost();
                sb.setStrengthBoost(1);
                item = "Strength";
                break;
            case "health":
                HealthBoost hb = new HealthBoost();
                hb.setHealthBoost(1);
                item = "health";
                break;
            case "power":
                PowerItem pow = new PowerItem();
                pow.setName("Power");
                item = "Power";
                break;
            default:
                System.out.println("No such item yet");
                break;
        }
        System.out.println(item);
        return item;
    }
}
