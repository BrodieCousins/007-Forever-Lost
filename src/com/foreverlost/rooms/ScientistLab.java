package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * This is the Scientists Lab. Directly north to the entrance room, houses all the interactions for this room.
 * <p>Nothing to search, purely a navigation room. Entering this room without a labcoat will cause Bond to die</p>
 */
public class ScientistLab extends Room {
    private final String name = "Scientists Lab";
    private final String descriptionCamerasActive = """
            Upon opening the doors, the bright light and neon white walls blind you momentarily.
            Your eyes adjust quickly and it takes a second for you to take everything in.
            
            The room is a giant square with multiple rings of desks surrounding a central table
            with various science things sitting on it. In front of you the ring of desks is broken
            to create a path that leads to the centre table and branches off to a set of doors on
            every side of the room. Around two dozen scientists roam the room, talking to each other.
            
            As you walk down the centre path, you scan each door and realise behind the east door
            you can see the flickering red light of an active camera. A sign next to the west door
            indicates it leads to the admin office. The north door gives no clues as to where it leads.
            """;
    private final String descriptionCamerasDisabled = """
            Upon opening the doors, the bright light and neon white walls blind you momentarily.
            Your eyes adjust quickly and it takes a second for you to take everything in.
            
            The room is a giant square with multiple rings of desks surrounding a central table
            with various science things sitting on it. In front of you the ring of desks is broken
            to create a path that leads to the centre table and branches off to a set of doors on
            every side of the room. Around two dozen scientists roam the room, talking to each other.
            
            As you walk down the centre path, you notice a dead security camera behind the east door,
            its red light dark and silent. A sign next to the west door indicates it leads to the admin
            office. The north door gives no clues as to where it leads.
            """;

    public ScientistLab(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
    }

    /**
     * Checks if the player has a lab coat before allowing entry into the Scientists Lab.
     * @return boolean
     */
    @Override
    public boolean canEnterRoom() {
        return getPlayer().hasLabCoat();
    }

    /**
     * Returns the message shown when the player tries to enter without a lab coat.
     * @return String
     */
    @Override
    public String getEntryDeniedMessage() {
        return "You can't walk in dressed like that. You need a scientist disguise.";
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the room description based on whether the security cameras are active or disabled.
     * @return String
     */
    @Override
    public String getDescription() {
        return areCamerasActive() ? descriptionCamerasActive : descriptionCamerasDisabled;
    }

    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.NORTH));
        System.out.println("(B): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(C): " + getGoDirectionLabel(Directions.WEST));
        System.out.println("(D): Search the room");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.NORTH);
            case 'B' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'C' -> tryEnterAdjacentRoom(Directions.WEST);
            case 'D' -> {
                System.out.println("You search the room and find nothing.");
                startDialogue();
            }
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }
}
