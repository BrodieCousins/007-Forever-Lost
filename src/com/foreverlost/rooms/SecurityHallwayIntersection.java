package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * T intersection at the end of the security room hallway.
 */
public class SecurityHallwayIntersection extends Room {
    private final String name = "Security Hallway Intersection";
    private final String description = """
            You approach the intersection. On your left around half a dozen meters away lies a single
            door; thanks to the sign above it you know it's another locker room.
            
            You notice this door doesn't have a window on it. On the right side, the hallway stretches
            further and you can see a door at the end of it.
            """;

    public SecurityHallwayIntersection(HashMap<Directions, Room> adjacentRooms) {
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
