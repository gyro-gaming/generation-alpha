package com.generation_alpha.client;

class Get {
    public static String forGet(String inputLine){
        String item = "";

        // Conditions - Hardcoded items based off possible inputs
        switch (inputLine) {
            case "strength":
                item = "Strength";
                break;
            case "health":
                item = "Health";
                break;
            case "power":
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
