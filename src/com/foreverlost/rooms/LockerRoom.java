package com.foreverlost.rooms;

import com.foreverlost.enums.Directions;
import com.foreverlost.main.Room;

import java.sql.SQLOutput;

public class LockerRoom extends Room {
    private final String name = "Locker Room";
    private final String description = "This is a locked room";

    public LockerRoom(Directions[] directions) {
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
