package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.main.Room;

import java.util.HashMap;

public class PathRoom extends Room {
    private final String name = "Hallway";
    private final String description = "This is a hallway";
    // The following stores which directions the player can go from this room
    private final HashMap<Directions, Boolean> exits = new HashMap<>();

    PathRoom(Directions[] directions) {
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
}
