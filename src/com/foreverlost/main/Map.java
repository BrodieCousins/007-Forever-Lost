package com.foreverlost.main;

import com.foreverlost.enums.Directions;
import com.foreverlost.rooms.*;

import java.util.HashMap;

/**
 * com.foreverlost.main.Map Class
 *
 * My little text rendition of the map layout lol
 * (0,0) LockerRoomNorth       (0,1) SecurityHallwayIntersection  (0,2) UtilityRoomHallway  (0,3) UtilityRoom
 * (1,0) SecurityRoom         (1,1) SecurityRoomHallway          (1,2) ReactorCoreHallway  (1,3) ReactorCoreRoom
 * (2,0) AdminRoom            (2,1) ScientistLab                  (2,2) ScientistLabEastHallway (2,3) ServerRoom
 * (3,0) null                 (3,1) StartRoom                     (3,2) LockerRoomSouth     (3,3) LHallway
 *
 * This class handles everything about the map, including the creation of rooms & linking adjacent rooms
 */
public class Map {
    private Room[][] map;
    private boolean cameraActive = true;
    private StartRoom startRoom;
    private final Player player = new Player();

    /**
     * Constructor for this class.
     * Sets the player and map to current fields of this class, then calls the newMap method to create the map.
     */
    public Map() {
        Room.setPlayer(this.player);
        Room.setMap(this);
        this.newMap();
    }

    /**
     * Getter method for the player field
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Creates the map, fills the map 2d array with rooms. Uses an empty hashmap initially to store adjacent rooms
     * before linking the rooms based on their grid neighbours.
     */
    private void newMap() {
        this.map = new Room[4][4];

        map[0][0] = new LockerRoomNorth(new HashMap<>());
        map[0][1] = new SecurityHallwayIntersection(new HashMap<>());
        map[0][2] = new UtilityRoomHallway(new HashMap<>());
        map[0][3] = new UtilityRoom(new HashMap<>());

        map[1][0] = new SecurityRoom(new HashMap<>());
        map[1][1] = new SecurityRoomHallway(new HashMap<>());
        map[1][2] = new ReactorCoreHallway(new HashMap<>());
        map[1][3] = new ReactorCoreRoom(new HashMap<>());

        map[2][0] = new AdminRoom(new HashMap<>());
        map[2][1] = new ScientistLab(new HashMap<>());
        map[2][2] = new ScientistLabEastHallway(new HashMap<>());
        map[2][3] = new ServerRoom(new HashMap<>());

        map[3][1] = new StartRoom(new HashMap<>());
        map[3][2] = new LockerRoomSouth(new HashMap<>());
        map[3][3] = new LHallway(new HashMap<>());

        setAdjacentNeighbours();

        this.startRoom = (StartRoom) map[3][1];
    }

    /**
     * Sets the adjacent neighbours for each room in the map. It calls the Room's linkAdjacentRooms method
     * to adjust the instance's adjacentRooms HashMap.
     * North will be one row above, East the index to the right in the array, West the index to the left in the array,
     * South the row below
     */
    private void setAdjacentNeighbours() {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                Room room = map[row][column];
                // This null check is important, as grid (3,0) is null intentionally
                if (room == null) {
                    continue;
                }

                if (row > 0 && map[row - 1][column] != null) {
                    room.linkAdjacentRoom(Directions.NORTH, map[row - 1][column]);
                }
                if (row < map.length - 1 && map[row + 1][column] != null) {
                    room.linkAdjacentRoom(Directions.SOUTH, map[row + 1][column]);
                }
                if (column > 0 && map[row][column - 1] != null) {
                    room.linkAdjacentRoom(Directions.WEST, map[row][column - 1]);
                }
                if (column < map[row].length - 1 && map[row][column + 1] != null) {
                    room.linkAdjacentRoom(Directions.EAST, map[row][column + 1]);
                }
            }
        }
    }

    /**
     * Returns the start room of the game.
     * @return startRoom
     */
    public StartRoom getStartRoom() {
        return startRoom;
    }

    /**
     * Returns a HashMap of adjacent rooms for any given room.
     * @param room This is the room you want to get the adjacent rooms for
     * @param validDirections The valid directions from this room
     * @return A HashMap mapping the valid directions to the adjacent rooms
     */
    public HashMap<Directions, Room> getAdjacentRooms(Room room, Directions[] validDirections) {
        HashMap<Directions, Room> adjacentRooms = new HashMap<>();

        int[] index = getIndexByRoom(room);
        int row = index[0];
        int column = index[1];

        for (Directions direction : validDirections) {
            switch (direction) {
                case Directions.NORTH:
                    adjacentRooms.put(direction, this.map[row - 1][column]);
                    break;
                case Directions.EAST:
                    adjacentRooms.put(direction, this.map[row][column + 1]);
                    break;
                case Directions.SOUTH:
                    adjacentRooms.put(direction, this.map[row + 1][column]);
                    break;
                case Directions.WEST:
                    adjacentRooms.put(direction, this.map[row][column - 1]);
                    break;
                default:
                    System.out.println("NOT A DIRECTION");
                    break;
            }
        }

        return adjacentRooms;
    }

    /**
     * This function allows you to get the room based off an index on the map
     * @param row int
     * @param column int
     * @return The room at the given index;
     */
    public Room getRoomByIndex(int row, int column) {
        return this.map[row][column];
    }

    /**
     * This function allows you to find out the index of a room within the map
     * @param room Include the room
     * @return int[row, column] index
     */
    public int[] getIndexByRoom(Room room) {
        int row = 0;
        int column = 0;

        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                if (this.map[i][j] == room) {
                    row = i;
                    column = j;
                }
            }
        }

        return new int[]{row, column};
    }

    /**
     * This function allows rooms to understand whether the cameras are on or not, to change dialogue and results
     * @return boolean
     */
    public boolean isCameraActive() {
        return cameraActive;
    }

    /**
     * This method is called by the SecurityRoom when the Security Guard is taken out. There should be no reason
     * to re-enable the cameras so this is a one time method.
     */
    public void disableCameras() {
        this.cameraActive = false;
    }

}
