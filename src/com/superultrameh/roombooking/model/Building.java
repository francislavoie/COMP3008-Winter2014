package com.superultrameh.roombooking.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Worked on by Becky and Francis
 *
 * Holds the name of a Building and a list of all the Rooms that can be booked.
 * Has a function to simplify retrieving a list of room numbers for the various spinners in the app.
 */

public class Building {
    private String name;
    private List<Room> rooms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    public Building(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }


    public Building(){
        name = "";
        rooms = new ArrayList<Room>();
    }

    public List<String> getRoomNumbers() {
        ArrayList<String> numbers = new ArrayList<String>();
        for(Room r : rooms) {
            numbers.add("" + r.getRoomNumber());
        }
        return numbers;
    }
}
