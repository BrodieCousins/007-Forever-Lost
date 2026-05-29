package com.foreverlost.main;

import com.foreverlost.enums.Directions;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Abstract class that defines characteristics that each com.foreverlost.main.Room needs to inherit and use
 */
public abstract class Room {
    private final HashSet<Directions> exits = new HashSet<>();

    public Room(Directions[] directions) {
        exits.addAll(Arrays.asList(directions)); // Add all valid directions to exits
    }
    /**
     * Checks if the player meets the requirements to enter the room
     * @return True if the player can enter the room, false otherwise
     */
    public abstract boolean canEnterRoom();

    public Directions[] getExits() {
        // Convert the HashSet to an array, new Directions[0] is just defining the type of the array practically
        return exits.toArray(new Directions[0]);
    }

    /**
     * @return The name of the room
     */
    public abstract String getName();

    /**
     * @return A description of the room
     */
    public abstract String getDescription();

    /**
     * Required method to be called when the player enters the room
     * Prints room entered and room description to the console
     */
    public final void onEnter() {
        System.out.println("-----------------------------");
        System.out.println("You have entered the " + this.getName() + ".");
        System.out.println(this.getDescription());
        System.out.println("-----------------------------");
    }
}
