package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * L-shaped hallway between Locker Room A and the Server Room.
 */
public class LHallway extends Room {
    private final String name = "L Hallway";
    private final String description = """
            As seen from the window in the door, the hallway takes a sharp left turn and reveals
            another doorway. Above it a dead security camera hangs idly from the ceiling, its red
            light dark and silent. Below the camera rests a sign. It reads "Server Room."
            
            You peek through the window on the door before quickly ducking down. There was somebody inside.
            """;

    public LHallway(HashMap<Directions, Room> adjacentRooms) {
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
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.NORTH));
        System.out.println("(B): " + getGoDirectionLabel(Directions.WEST));

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.NORTH);
            case 'B' -> tryEnterAdjacentRoom(Directions.WEST);
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }
}
