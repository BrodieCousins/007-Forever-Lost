package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * Server room guarded by the brute. The player can steal the level 4 access card here.
 */
public class ServerRoom extends Room {
    private final String name = "Server Room";
    private final String description = """
            The server room is gigantic; you can't see the end of the room. Tall rectangular servers
            fill the room, organised into lanes which form aisles. You sneak inside and hide in one
            of the aisles.
            
            You see the Guard sitting in his chair at the front of the room. On the table next to him
            is a level 4 access card. The guard looked strong physically, the veins in his arms
            bulging as he grips a machete.
            """;

    public ServerRoom(HashMap<Directions, Room> adjacentRooms) {
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
     * Shows options to steal the level 4 card using the mug or by force.
     */
    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): Use the mug as a distraction to steal the card");
        System.out.println("(B): Take the card by force");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> {
                if (!getPlayer().hasMug()) {
                    System.out.println("You don't have anything to throw as a distraction.");
                    startDialogue();
                    return;
                }
                System.out.println("You throw the mug over to the other side of the room and it smashes with a sharp clang.");
                System.out.println("The Guard walks over and you swiftly snatch their level 4 card.");
                getPlayer().setL4KeycardTrue();
                afterKeycard();
            }
            case 'B' -> System.out.println("With inhuman speed, the guard lunges from his seated position. You are dead.");
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }

    /**
     * Shows navigation options after the player has stolen the level 4 keycard.
     */
    private void afterKeycard() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.WEST));
        System.out.println("(B): " + getGoDirectionLabel(Directions.SOUTH));

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.WEST);
            case 'B' -> tryEnterAdjacentRoom(Directions.SOUTH);
            default -> {
                System.out.println("Invalid option.");
                afterKeycard();
            }
        }
    }
}
