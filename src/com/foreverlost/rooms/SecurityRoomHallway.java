package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * Hallway north of the Scientists Lab leading to the Security Room and the intersection beyond.
 */
public class SecurityRoomHallway extends Room {
    private final String name = "Security Room Hallway";
    private final String description = """
            You leave the science lab and beyond the double doors lies a barren hallway. On the
            opposite side the hallway creates a T intersection.
            
            However, before you reach the intersection you notice a door on the left wall. The sign
            above it reads "Security room, level 2 access required." A bit to the left of the sign
            you also see a vent that likely leads into the room.
            """;

    public SecurityRoomHallway(HashMap<Directions, Room> adjacentRooms) {
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
     * Shows navigation options and the choices to enter the security room via the door or vent.
     */
    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.NORTH));
        System.out.println("(B): " + getGoDirectionLabel(Directions.SOUTH));
        System.out.println("(C): Scan the L2 card");
        System.out.println("(D): Climb into the vent");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.NORTH);
            case 'B' -> tryEnterAdjacentRoom(Directions.SOUTH);
            case 'C' -> enterSecurityRoomViaDoor();
            case 'D' -> enterSecurityRoomViaVent();
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }

    /**
     * Attempts to enter the security room through the door using the L2 keycard.
     */
    private void enterSecurityRoomViaDoor() {
        if (!getPlayer().hasL2Keycard()) {
            System.out.println("You don't have a level 2 access keycard to scan.");
            startDialogue();
            return;
        }
        getPlayer().setEnteredSecurityRoomViaVent(false);
        tryEnterAdjacentRoom(Directions.WEST);
    }

    /**
     * Attempts to enter the security room through the vent using the screwdriver.
     */
    private void enterSecurityRoomViaVent() {
        if (!getPlayer().hasScrewdriver()) {
            System.out.println("Hmm, I need a tool to unscrew the vent to get through");
            startDialogue();
            return;
        }
        getPlayer().setEnteredSecurityRoomViaVent(true);
        enterAdjacentRoom(Directions.WEST);
    }
}
