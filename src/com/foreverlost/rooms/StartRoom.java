package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * This is the room the player spawns in. They have a Locker Room to the East, and a ScientistsLab to the North
 * No special items in this class or special actions
 */
public class StartRoom extends Room {
    private final String name = "Start Room";
    private final String description = """
            Directly in front of you see a set of double doors with a small glass window
            embedded into them. You can see people in white coats moving around hurriedly
            as well as hear faint voices emanating from the gap between the floor and the doors.
            
            To your right is another pair of the same doors yet there is no movement through
            the window, and no sound emanates from them either. A sign above the doors reads
            'Locker Room'.
            """;

    public StartRoom(HashMap<Directions, Room> adjacentRooms) {
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
     * This is the dialogue that will be displayed when the player enters the room.
     * It will ask the player what they want to do.
     * The player can choose to go north to the Scientists Lab, go east through the locker room doors, or search the room.
     */
    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.NORTH));
        System.out.println("(B): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(C): Search the room");

        // Gather input
        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.NORTH);
            case 'B' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'C' -> {
                System.out.println("You search the room and find nothing.");
                startDialogue(); // Restart the dialogue
            }
            default -> {
                System.out.println("Invalid option.");
                startDialogue(); // Restart the dialogue
            }
        }
    }
}
