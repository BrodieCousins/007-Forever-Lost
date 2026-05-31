package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Abstract class that defines characteristics that each com.foreverlost.rooms.Room needs to inherit and use
 *
 * @author BrodieCousins
 */
public abstract class Room {
    private final HashMap<Directions, Room> adjacentRooms = new HashMap<>();
    private boolean searched = false;

    public Room(HashMap<Directions, Room> adjacentRooms) {
        this.adjacentRooms.putAll(adjacentRooms);
    }
    /**
     * Checks if the player meets the requirements to enter the room
     * @return True if the player can enter the room, false otherwise
     */
    public abstract boolean canEnterRoom();

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
    public final void enter() {
        System.out.println("-----------------------------");
        System.out.println("You have entered the " + this.getName() + ".");
        System.out.println(this.getDescription());
        System.out.println("-----------------------------");
        startDialogue();
    }

    /**
     * This method will be immediately called after the "You have entered {room}" output.
     * It is used to start the dialogue of the room and will call methods for actions etc.
     */
    protected abstract void startDialogue();

    /**
     * Whether the player has used the 'search' action on this room yet
     * @return boolean
     */
    public boolean isSearched() {
        return searched;
    }

    /**
     * Sets the searched value to true, as there shouldn't be a scenario where you set search to false
     */
    protected void setSearchedTrue() {
        this.searched = true;
    }

    /**
     * This will return a HashMap of all adjacent rooms to this room.
     * @return
     */
    public HashMap<Directions, Room> getAdjacentRooms() {
        return this.adjacentRooms;
    }

    protected char getOptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        scanner.close();
        response = response.toUpperCase();
        return response.charAt(0);
    }
}
