package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.util.Animations;

import java.util.HashMap;

/**
 * Final room. Jane Sever confronts Bond at the nuclear core.
 */
public class ReactorCoreRoom extends Room {
    private final String name = "Reactor Core Room";
    private final String description = """
            You slip inside using the level 4 card. You're met with a giant tube in the centre of the
            room, full of nuclear energy. Jane Sever stands in front of it, watching you.
            
            You notice guards standing around the perimeter of the room, except for behind her and
            the tube where a giant window with a 20 meter drop into a river.
            
            Jane dips her glasses and you see her blue eyes lock with yours. "James Bond is here,
            what a surprise!" she smiles coldly before waving her pistol in the air.
            """;

    public ReactorCoreRoom(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
    }

    /**
     * Checks if the player has the level 4 keycard before allowing entry.
     * @return boolean
     */
    @Override
    public boolean canEnterRoom() {
        return getPlayer().hasL4Keycard();
    }

    /**
     * Returns the message shown when the player tries to enter without the L4 keycard.
     * @return String
     */
    @Override
    public String getEntryDeniedMessage() {
        return "The door is locked. Level 4 access keycard required.";
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
     * Starts the final confrontation with Jane Sever. Shows the winning and losing options.
     */
    @Override
    protected void startDialogue() {
        Animations.printLicenceToKill();
        System.out.println("Options:\n(A): Shoot her");
        System.out.println("(B): Talk with her");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> System.out.println("You draw your pistol and fire. You charge through the window and plunge " +
                    "into the river below. Eventually you wash up on a riverbank. \"Pick me up, the mission was a " +
                    "success, I severed Jane\" You chuckle to yourself.");
            case 'B' -> System.out.println("\"Very we-\" you never get to finish your sentence as a guard shoots you " +
                    "clean in the head. You die instantly.");
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }
}
