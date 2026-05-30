package com.foreverlost.main;

import com.foreverlost.enums.Directions;
import com.foreverlost.rooms.LockerRoom;

import java.util.Arrays;
import java.util.HashMap;

/**
 * com.foreverlost.main.Map Class
 * Houses methods for:
 * <ul>
 *      Information about the current com.foreverlost.main.Map
 *      Creating a new map
 * </ul>
 */
public class Map {
    private Room[][] map;
    private int[] playerPosition = {0, 0};

    public Map() {
        this.newMap();
    }

    private void newMap() {
        this.map = new Room[][]{
                {new LockerRoom(new Directions[]{Directions.EAST}), new LockerRoom(new Directions[]{Directions.WEST})}
        };
        System.out.println(Arrays.deepToString(this.map));
    }

    /**
     * Returns a HashMap of adjacent rooms for any given room.
     * @param room This is the room you want to get the adjacent rooms for
     * @param validDirections The valid directions from this room
     * @return A HashMap mapping the valid directions to the adjacent rooms
     */
    public HashMap<Directions, Room> getAdjacentRooms(Room room, Directions[] validDirections) {
        // This is going to store the Direction as a Key, to return the Map as a value
        HashMap<Directions, Room> adjacentRooms = new HashMap<>();

        int[] index = getIndexByRoom(room);
        int row = index[0];
        int column = index[1];

        for (Directions direction : validDirections) {
            switch (direction) {
                case Directions.NORTH:
                    // add the room ABOVE this room
                    adjacentRooms.put(direction, this.map[row - 1][column]);
                    break;
                case Directions.EAST:
                    // add the room RIGHT of this room
                    adjacentRooms.put(direction, this.map[row][column + 1]);
                    break;
                case Directions.SOUTH:
                    // add the room BELOW this room
                    adjacentRooms.put(direction, this.map[row + 1][column]);
                    break;
                case Directions.WEST:
                    // add the room LEFT of this room
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
        // Giving them initial 0 values to avoid errors
        int row = 0;
        int column = 0;

        // Get the index for the room in the map
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
}
