package com.generation_alpha.client;

import com.generation_alpha.locations.Direction;

class Go {
    public static Direction forGo(String inputLine) {

        // from Direction Enum , variable is declared and starts off as null
        Direction direction = null;


        // Conditions - Switch statement that will assign enum to proper case
        switch (inputLine) {
            case "north":
                direction = Direction.NORTH;
                break;
            case "east":
                direction = Direction.EAST;
                break;
            case "west":
                direction = Direction.WEST;
                break;
            case "south":
                direction = Direction.SOUTH;
                break;
            default:
                System.out.println("Error: You can only go north,south,east,west");
                break;
        }

        System.out.println(direction);
        return direction;
    }
}
