package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.util.Animations;

import java.util.HashMap;

/**
 * Locker Room North — a trap room with a guard eating a sandwich. License to Kill encounter.
 */
public class LockerRoomNorth extends Room {
    private final String name = "Locker Room North";
    private final String description = """
            You open the door and slip inside; it has the exact same layout as the other locker room.
            However, instead of a lone mug on the benches in the centre, you find a startled guard
            eating a sandwich. He immediately drops the sandwich and goes to draw his gun.
            """;

    public LockerRoomNorth(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
    }

    @Override
    public boolean canEnterRoom() {
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Starts the License to Kill encounter. Prompts the player to draw their gun or tackle the guard.
     */
    @Override
    protected void startDialogue() {
        Animations.printLicenceToKill();
        System.out.println("Options:\n(A): Attempt to draw your gun faster than him");
        System.out.println("(B): Tackle him and brawl it out before he can shoot");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> afterWin();
            case 'B' -> System.out.println("The guard draws his pistol before you can cross the distance. Bond has failed.");
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }

    /**
     * Called after the player wins the gun draw. Shows options to leave or search the room.
     */
    private void afterWin() {
        System.out.println("Your training kicks in and you draw your gun in a split second. You pull the trigger and " +
                "with the soft ping of your silenced pistol, the guard collapses on the benches.");
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(B): Search the room");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'B' -> {
                if (!isSearched()) {
                    System.out.println("You search the room.");
                    setSearchedTrue();
                } else {
                    System.out.println("You have already searched this room.");
                }
                afterWin();
            }
            default -> {
                System.out.println("Invalid option.");
                afterWin();
            }
        }
    }
}
