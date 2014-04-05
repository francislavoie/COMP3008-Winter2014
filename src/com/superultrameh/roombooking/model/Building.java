package com.superultrameh.roombooking.model;

import java.util.ArrayList;
import java.util.List;

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
}
