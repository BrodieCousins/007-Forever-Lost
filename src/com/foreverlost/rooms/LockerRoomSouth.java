package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.main.Room;

import java.util.Scanner;

/**
 * This is the locker room to the right of the entrance room. This class includes all the descriptions, names, exits
 * to interact with.
 *
 */
public class LockerRoomSouth extends Room {
    private final String name = "Locker Room";
    private final String description = """
            Upon entering the locker room your eyes are immediately drawn to the hundreds of small cubic lockers lining 
            the walls of the room, a few benches lay next to each other in the centre of the room. After looking around 
            for a second you see one of the lockers is still ajar, to the east is another set of double doors with 
            windows however it appears to lead into a hallway that takes a sharp left turn. If you 
            squint through the glass, you can also see the flickering red light of an active security camera. To the
            north is another hallway. To the west appears to be the room you started in.
            """;
    private final String search_description = "You find a scientific lab coat in the ajar locker, for a Patricia. You" +
            "decide to wear this as a disguise, maybe this means you can get through the Scientific Lab unnoticed. You" +
            "also find and pick up a Mug, not sure what this could possibly be used for though";
    private boolean searched = false;

    public LockerRoomSouth() {
        Directions[] directions = {Directions.NORTH, Directions.EAST, Directions.WEST};
        super(directions);
    }

    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): Go north to a hallway");
        System.out.println("(B): Go east to a hallway");
        System.out.println("(C): Go west back to the entrance room");
        System.out.println("(D): Search the room");

        // take user input
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        scanner.close();

        switch (response) {
            case "A":
                System.out.println("You go north.");
                //TODO: Handle navigation
                break;
            case "B":
                System.out.println("You go east.");
                //TODO: Handle navigation
                break;
            case "C":
                System.out.println("You go west.");
                //TODO: Handle navigation
                break;
            case "D":
                search();
                break;
            default:
                System.out.println("Invalid option.");
                startDialogue();
        }
    }

    private void search() {
        if (!searched) {
            System.out.println(search_description);
            searched = true;
            //TODO: Add items to inventory
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

    @Override
    public String getDescription() {
        return this.description;
    }
}
