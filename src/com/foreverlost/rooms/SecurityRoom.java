package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.util.Animations;

import java.util.HashMap;

/**
 * Security monitoring room. Disabling the guard here turns off facility cameras.
 */
public class SecurityRoom extends Room {
    private final String name = "Security Room";
    private final String descriptionViaDoor = """
            You scan the level 2 access key and watch the door slide open quietly. Inside on the far
            wall is a display of monitors, each one showing a different area of the facility.
            
            A guard sits with his back to you, watching the display with headphones on. How convenient.
            """;
    private final String descriptionViaVent = """
            You use the screwdriver and take the vent off its hinges. As discretely as possible you
            climb into the vent; it's a bit tight but nothing you can't handle. After following the
            vent's path for a couple of minutes, you find yourself crawling over another vent in the
            ceiling. Looking through it, you see a guard sitting below. He's looking at the wall, and
            you can make out the faint glow of screens. He is completely clueless of your existence.
            """;

    public SecurityRoom(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
    }

    /**
     * Checks if the player has the level 2 keycard before allowing entry through the door.
     * @return boolean
     */
    @Override
    public boolean canEnterRoom() {
        return getPlayer().hasL2Keycard();
    }

    /**
     * Returns the message shown when the player tries to enter without the L2 keycard.
     * @return String
     */
    @Override
    public String getEntryDeniedMessage() {
        return "The door is locked. Level 2 access keycard required.";
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the room description based on whether the player entered via the door or the vent.
     * @return String
     */
    @Override
    public String getDescription() {
        return getPlayer().enteredSecurityRoomViaVent() ? descriptionViaVent : descriptionViaDoor;
    }

    /**
     * Starts the guard encounter. The option text changes depending on how the player entered the room.
     * If the guard has already been defeated, skips straight to the post-encounter options.
     */
    @Override
    protected void startDialogue() {
        if (!areCamerasActive()) {
            afterGuardDefeated();
            return;
        }

        if (getPlayer().enteredSecurityRoomViaVent()) {
            System.out.println("Options:\n(A): Drop from the roof and take him out");
        } else {
            System.out.println("Options:\n(A): Take out the Guard");
        }

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> {
                getMap().disableCameras();
                afterGuardDefeated();
            }
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }

    /**
     * Shows options to leave or search after the security guard is defeated.
     */
    private void afterGuardDefeated() {
        System.out.println("After defeating the guard you look around the room, scanning over the monitors you don't " +
                "see any useful information. Other than the monitors, desk and chair the room is bare.");
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(B): Search the room");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'B' -> {
                if (!isSearched()) {
                    System.out.println("You search the room but find nothing useful.");
                    setSearchedTrue();
                } else {
                    System.out.println("You have already searched this room.");
                }
                afterGuardDefeated();
            }
            default -> {
                System.out.println("Invalid option.");
                afterGuardDefeated();
            }
        }
    }
}
