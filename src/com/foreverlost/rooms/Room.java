package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.main.Map;
import com.foreverlost.main.Player;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Abstract class that defines characteristics that each com.foreverlost.rooms.Room needs to inherit and use
 *
 * @author BrodieCousins
 */
public abstract class Room {
    private static final Scanner INPUT = new Scanner(System.in);
    private static Player player;
    private static Map gameMap;

    private final HashMap<Directions, Room> adjacentRooms = new HashMap<>();
    private boolean searched = false;

    /**
     * Constructor for the Room class
     *
     * All it does is add the adjacent rooms to the instance's adjacentRooms HashMap.
     * @param adjacentRooms HashMap of adjacent rooms
     */
    public Room(HashMap<Directions, Room> adjacentRooms) {
        this.adjacentRooms.putAll(adjacentRooms);
    }

    /**
     * Setters for the player field
     * @param player Player object
     */
    public static void setPlayer(Player player) {
        Room.player = player;
    }

    /**
     * Setter for the gameMap field
     * @param map Map object
     */
    public static void setMap(Map map) {
        Room.gameMap = map;
    }

    /**
     * Getter for the player field
     * @return Player
     */
    protected static Player getPlayer() {
        return player;
    }

    /**
     * Getter for the gameMap field
     * @return Map
     */
    protected static Map getMap() {
        return gameMap;
    }

    /**
     * Checks if any cameras are active in the game. Uses the value defined in the Map class.
     * @return boolean
     */
    protected boolean areCamerasActive() {
        return getMap().isCameraActive();
    }
    /**
     * Checks if the player meets the requirements to enter the room
     * @return True if the player can enter the room, false otherwise
     */
    public abstract boolean canEnterRoom();

    /**
     * @return Message shown when {@link #canEnterRoom()} returns false
     */
    public String getEntryDeniedMessage() {
        return "You cannot enter the " + getName() + ".";
    }

    /**
     * Attempts to enter another room after checking its entry requirements.
     * If entry is denied, the current room's options are shown again.
     */
    protected void tryEnterRoom(Room target) {
        if (target == null) {
            System.out.println("You can't go that way.");
            startDialogue();
            return;
        }
        if (target.canEnterRoom()) {
            target.enter();
        } else {
            System.out.println(target.getEntryDeniedMessage());
            startDialogue();
        }
    }

    /**
     * Attempts to enter the adjacent room in the given direction, checking entry requirements first.
     * @param direction The direction to move in
     */
    protected void tryEnterAdjacentRoom(Directions direction) {
        tryEnterRoom(getAdjacentRooms().get(direction));
    }

    /**
     * Enters the adjacent room in the given direction without checking entry requirements.
     * Used for alternate entry paths such as the vent into the security room.
     * @param direction The direction to move in
     */
    protected void enterAdjacentRoom(Directions direction) {
        enterRoom(getAdjacentRooms().get(direction));
    }

    /**
     * Enters the target room directly without checking whether the player can enter.
     * @param target The room to enter
     */
    protected void enterRoom(Room target) {
        if (target == null) {
            System.out.println("You can't go that way.");
            startDialogue();
            return;
        }
        target.enter();
    }

    /**
     * Builds the navigation option label for the adjacent room in the given direction.
     * @param direction The direction of the adjacent room
     * @return The formatted option label
     */
    protected String getGoDirectionLabel(Directions direction) {
        Room target = getAdjacentRooms().get(direction);
        return "Go " + directionToLabel(direction) + " to the " + target.getName();
    }

    /**
     * Converts a Directions enum value to its lowercase label for use in dialogue text. Because I was too tired
     * running .lower() on all strings haha - Brodie
     * @param direction The direction to convert
     * @return The lowercase direction label
     */
    private static String directionToLabel(Directions direction) {
        return switch (direction) {
            case NORTH -> "north";
            case EAST -> "east";
            case WEST -> "west";
            case SOUTH -> "south";
        };
    }

    /**
     * @return True if this room is monitored by a security camera
     */
    public boolean hasSecurityCamera() {
        return false;
    }

    /**
     * @return The name of the room
     */
    public abstract String getName();

    /**
     * @return A description of the room
     */
    public abstract String getDescription();

    /**
     * Required method to be called when the player enters the room
     * Prints room entered and room description to the console
     */
    public final void enter() {
        System.out.println("-----------------------------");
        System.out.println("You have entered the " + this.getName() + ".");

        if (hasSecurityCamera() && getMap() != null && getMap().isCameraActive()) {
            printCameraDeathMessage();
            System.out.println("-----------------------------");
            System.exit(0);
        }

        System.out.println(this.getDescription());
        System.out.println("-----------------------------");
        startDialogue();
    }

    /**
     * Prints the death message when Bond is caught on camera and exits the game.
     */
    private void printCameraDeathMessage() {
        System.out.println("""
                
                You step into the %s. Before you can blink, the lights around you flicker to red.
                An obnoxious blaring siren sounds throughout the entire facility. The doors around
                you close and lock, reinforced metal doors slamming shut right in front of your face.
                Your fate is sealed.
                
                You draw your pistol to start firing at the walls, the door, anything to find some
                sort of weakness. But there isn't one. Desperation consumes you, banging on the walls.
                As time passes you find yourself too exhausted to continue, gas fills the room.
                James Bond... is dead.
                """.formatted(this.getName()));
    }

    /**
     * This method will be immediately called after the "You have entered {room}" output.
     * It is used to start the dialogue of the room and will call methods for actions etc.
     */
    protected abstract void startDialogue();

    /**
     * Whether the player has used the 'search' action on this room yet
     * @return boolean
     */
    public boolean isSearched() {
        return searched;
    }

    /**
     * Sets the searched value to true, as there shouldn't be a scenario where you set search to false
     */
    protected void setSearchedTrue() {
        this.searched = true;
    }

    /**
     * This will return a HashMap of all adjacent rooms to this room.
     * @return
     */
    public HashMap<Directions, Room> getAdjacentRooms() {
        return this.adjacentRooms;
    }

    /**
     * Links an adjacent room after all rooms on the map have been created.
     */
    public void linkAdjacentRoom(Directions direction, Room room) {
        this.adjacentRooms.put(direction, room);
    }

    /**
     * Reads a line of input from the player and returns the character
     * @return Player's input in uppercase
     */
    protected char getOptionFromUser() {
        // this is an issue at the moment that I can't be bothered to fix, as it only returns the first character
        // not gamebreaking so i'll leave it
        String response = INPUT.nextLine().trim().toUpperCase();
        return response.charAt(0);
    }
}
