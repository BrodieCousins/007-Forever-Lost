package com.foreverlost.main;

import com.foreverlost.enums.Directions;
import com.foreverlost.util.Animations;

import java.util.HashMap;

/**
 * Entrypoint to the game
 */
public class Main {

    /**
     * This just creates the map and then calls the startRoom enter method to begin the game
     * @param args
     */
    public static void main(String[] args) {
        Map map = new Map();
        Animations.printDoubleOSevenArt();

        map.getStartRoom().enter();
    }
}
