package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;

import java.util.HashMap;

/**
 * Hallway leading to the Reactor Core Room. A guard patrols here until dealt with.
 */
public class ReactorCoreHallway extends Room {
    private final String name = "Reactor Core Hallway";
    private final String description = """
            You walk down the hallway; hugging the wall, you peek your head around the corner and see
            a Guard on patrol. He doesn't look very alert but he is holding a gun, meaning one shot
            from that thing and your cover's blown.
            
            A dead security camera hangs above the corridor, deactivated and dark.
            """;

    public ReactorCoreHallway(HashMap<Directions, Room> adjacentRooms) {
        super(adjacentRooms);
    }

    /**
     * Returns true as this room contains a security camera that kills the player on entry.
     * @return boolean
     */
    @Override
    public boolean hasSecurityCamera() {
        return true;
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
     * Shows navigation and guard encounter options while the guard is still on patrol.
     */
    @Override
    protected void startDialogue() {
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.NORTH));
        System.out.println("(B): " + getGoDirectionLabel(Directions.WEST));
        System.out.println("(C): Lure him towards the corner before disarming him and knocking him out");
        System.out.println("(D): Pretend to be a scientist then knock him out");

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.NORTH);
            case 'B' -> tryEnterAdjacentRoom(Directions.WEST);
            case 'C' -> {
                System.out.println("You stomp your foot to get his attention. Once he gets in range you spring into " +
                        "action and knock him out.");
                afterGuardDefeated();
            }
            case 'D' -> System.out.println("The guard raises his weapon. Your cover's blown. The fight lasted less " +
                    "than 10 seconds. You didn't stand a chance.");
            default -> {
                System.out.println("Invalid option.");
                startDialogue();
            }
        }
    }

    /**
     * Shows full navigation options after the guard has been knocked out.
     */
    private void afterGuardDefeated() {
        System.out.println("You go down the hallway, the guard still lying motionless where you knocked him out. " +
                "You can hear Jane Sever's voice coming from inside the Reactor Core Room.");
        System.out.println("Options:\n(A): " + getGoDirectionLabel(Directions.NORTH));
        System.out.println("(B): " + getGoDirectionLabel(Directions.EAST));
        System.out.println("(C): " + getGoDirectionLabel(Directions.WEST));

        char response = getOptionFromUser();

        switch (response) {
            case 'A' -> tryEnterAdjacentRoom(Directions.NORTH);
            case 'B' -> tryEnterAdjacentRoom(Directions.EAST);
            case 'C' -> tryEnterAdjacentRoom(Directions.WEST);
            default -> {
                System.out.println("Invalid option.");
                afterGuardDefeated();
            }
        }
    }
}
