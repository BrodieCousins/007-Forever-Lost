package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * Hallway east of the Scientists Lab. Leads to Locker Room A and the Server Room.
 */
public class ScientistLabEastHallway extends Room {
    private final String name = "Scientists Lab East Hallway";
    private final String description = """
            You go through the doors and are met with a hallway. In the middle of the hallway on
            the right wall is a door that leads to "Locker Room A."
            
            At the end of the hallway a dead security camera droops from the wall, deactivated. The
            door beneath it has a window; you can't see any movement. The sign next to the door reads
            "Server Room".
            """;

    public ScientistLabEastHallway(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
    }

    /**
     * Returns true as this room contains a security camera that kills the player on entry.
     * @return boolean
     */
    @Override
    public boolean hasSecurityCamera() {
        return true;
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

    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(B): " + getGoDirectionLabel(Directions.WEST));
        System.out.println("(C): " + getGoDirectionLabel(Directions.SOUTH));
        System.out.println("(D): Look through the Server Room door");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'B' -> tryEnterAdjacentRoom(Directions.WEST);
            case 'C' -> tryEnterAdjacentRoom(Directions.SOUTH);
            case 'D' -> {
                System.out.println("You peer through the window but cannot make out much from here.");
                startDialogue();
            }
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }
}
