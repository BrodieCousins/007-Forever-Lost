package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * This is the locker room to the right of the entrance room. This class includes all the descriptions, names, exits
 * to interact with.
 */
public class LockerRoomSouth extends Room {
    private final String name = "Locker Room";
    private final String descriptionBase = """
            Upon entering the locker room your eyes are immediately drawn to the hundreds of small
            cubic lockers lining the walls of the room. A few benches lay next to each other in
            the centre of the room.
            
            After looking around for a second you see one of the lockers is still ajar. To the east
            is another set of double doors with windows; however it appears to lead into a hallway
            that takes a sharp left turn.
            """;
    private final String descriptionCameraActive = """
            
            If you squint through the glass, you can also see the flickering red light of an active
            security camera. There is also a flickering red light in the hallway to the north.
            """;
    private final String descriptionCameraDisabled = """
            
            If you squint through the glass, you can see a dead security camera in the hallway,
            dark and silent, the hallway to the north is also dark and silent.
            """;
    private final String descriptionEnd = """
            
            To the west appears to be the room you started in.
            """;
    private final String search_description = """
            You find a scientific lab coat in the ajar locker, for a Patricia. You decide to wear
            this as a disguise, maybe this means you can get through the Scientific Lab unnoticed.
            You also find and pick up a Mug, not sure what this could possibly be used for though.
            """;

    public LockerRoomSouth(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
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
            case 'D' -> search();
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }

    /**
     * Handles the search action. Gives the player the lab coat and mug on first search.
     */
    private void search() {
        if (!isSearched()) {
            System.out.println(search_description);
            setSearchedTrue();
            getPlayer().setLabCoatTrue();
            getPlayer().setMugTrue();
            startDialogue();
        } else {
            System.out.println("You have already searched this room.");
            startDialogue();
        }
    }

    @Override
    public boolean canEnterRoom() {
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the room description based on whether the security cameras are active or disabled.
     * @return String
     */
    @Override
    public String getDescription() {
        String cameraLine = areCamerasActive() ? descriptionCameraActive : descriptionCameraDisabled;
        return descriptionBase + cameraLine + descriptionEnd;
    }
}
