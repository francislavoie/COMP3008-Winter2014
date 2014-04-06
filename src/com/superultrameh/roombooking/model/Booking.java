package com.superultrameh.roombooking.model;

/**
 * Worked on by Becky and Francis
 *
 * Holds the information pertaining to a room booking. Has the building, room, and time range
 * that the room is booked.
 */
public class Booking {
    private Building building;
    private Room room;
    private AvailableTime bookedTime;

    public Booking() {
        building = new Building();
        room = new Room();
        bookedTime = new AvailableTime();
    }

    public Booking(Building building, Room room, AvailableTime bookedTime) {
        this.building = building;
        this.room = room;
        this.bookedTime = bookedTime;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public AvailableTime getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(AvailableTime bookedTime) {
        this.bookedTime = bookedTime;
    }


}
