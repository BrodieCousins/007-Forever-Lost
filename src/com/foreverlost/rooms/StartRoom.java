package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.main.Room;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This is the room the player spawns in. They have a Locker Room to the East, and a ScientistsLab to the North
 * No special items in this class or special actions
 */
public class StartRoom extends Room {
    private final String name = "Start Room";
    private final String description = " Directly in front of you see a set of double doors with a small glass window " +
            "embedded into them. You can see people in white coats moving around hurriedly as well as hear faint voices " +
            "emanating from the gap between the floor and the doors. To your right is another pair of the same doors " +
            "yet there is no movement through the window, and no sound emanates from them either. A sign above the " +
            "doors reads 'Locker Room'";
    private final HashSet<Directions> exits = new HashSet<>();

    /**
     * When creating this object, it will have two exits, East and North
     */
    StartRoom() {
        Directions[] directions = {Directions.EAST, Directions.NORTH};
        super(directions);
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
        System.out.println("Options:\n(A): Go north to the Scientists Lab");
        System.out.println("(B): Go east through the locker room doors");
        System.out.println("(C): Search the room");

        // Gather input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        if (input.equals("A")) {
            // Enter scientists' lab
        } else if (input.equals("B")) {
            // Enter locker room
        } else if (input.equals("C")) {
            System.out.println("You search the room and find nothing.");
            startDialogue(); // Restart the dialogue
        } else {
            System.out.println("Invalid option.");
            startDialogue(); // Restart the dialogue
        }
    }
}
