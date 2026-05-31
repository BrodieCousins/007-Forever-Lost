package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * Utility room with benches and tools used by scientists.
 */
public class UtilityRoom extends Room {
    private final String name = "Utility Room";
    private final String description = """
            Inside the walls lay mostly bare; the tools that live here are probably being used by the
            scientists and will be returned at the end of the day.
            
            The room is lined with one big, connected bench. Its surface looks heavily chipped, likely
            from the prolonged exposure of tired scientists dumping equipment there without a care in
            the world after a long day of work.
            """;

    public UtilityRoom(HashMap<Directions, Room> adjacentRooms) {
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
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.WEST));
        System.out.println("(B): Search the room for anything useful");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.WEST);
            case 'B' -> {
                if (!isSearched()) {
                    System.out.println("You search the room and find a screwdriver on the bench.");
                    getPlayer().setScrewdriverTrue();
                    setSearchedTrue();
                } else {
                    System.out.println("You have already searched this room.");
                }
                startDialogue();
            }
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }
}
