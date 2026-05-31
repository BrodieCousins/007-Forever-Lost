package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * Hallway east of the security intersection leading to the Utility Room and Reactor Core Hallway.
 */
public class UtilityRoomHallway extends Room {
    private final String name = "Utility Room Hallway";
    private final String description = """
            As you walk down the hallway to the door, a turn in the middle of the hallway comes into
            view. You can see that it goes straight before taking a sharp turn left.
            
            You're close enough to the door at the end you can read the sign next to it. It reads
            "Utility Room."
            """;

    public UtilityRoomHallway(HashMap<Directions, Room> adjacentRooms) {
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

    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(B): " + getGoDirectionLabel(Directions.WEST));
        System.out.println("(C): " + getGoDirectionLabel(Directions.SOUTH));

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'B' -> tryEnterAdjacentRoom(Directions.WEST);
            case 'C' -> tryEnterAdjacentRoom(Directions.SOUTH);
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }
}
