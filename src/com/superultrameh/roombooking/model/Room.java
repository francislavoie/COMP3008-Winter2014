package com.superultrameh.roombooking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by becky on 3/31/2014.
 */
public class Room {
    private Integer roomNumber;
    private Integer minCapacity;
    private Integer blackboard;
    private Integer whiteboard;
    private Boolean tablesMove;
    private Boolean chairsMove;
    private Integer tv;
    private Integer projector;
    private Integer table;
    private Integer chair;
    private Integer outlet;
    private Integer capacity;
    private List<AvailableTime> availableTimes;

    public Room(Integer roomNumber, Integer minCapacity, Integer blackboard, Integer whiteboard, Boolean tablesMove,
                Boolean chairsMove, Integer tv, Integer projector, Integer table, Integer chair, Integer outlet,
                Integer capacity, List<AvailableTime> availableTimes) {
        this.roomNumber = roomNumber;
        this.minCapacity = minCapacity;
        this.blackboard = blackboard;
        this.whiteboard = whiteboard;
        this.tablesMove = tablesMove;
        this.chairsMove = chairsMove;
        this.tv = tv;
        this.projector = projector;
        this.table = table;
        this.chair = chair;
        this.outlet = outlet;
        this.capacity = capacity;
        this.availableTimes = availableTimes;
    }
    public Room(Integer roomNumber, Integer tv, Integer projector,
                Integer table, Integer chair, Integer outlet, Integer capacity,
                Integer minCapacity, Integer blackboard, Integer whiteboard,
                Boolean tablesMove, Boolean chairsMove) {
        this.roomNumber = roomNumber;
        this.tv = tv;
        this.projector = projector;
        this.table = table;
        this.chair = chair;
        this.outlet = outlet;
        this.capacity = capacity;
        this.minCapacity = minCapacity;
        this.blackboard = blackboard;
        this.whiteboard = whiteboard;
        this.tablesMove = tablesMove;
        this.chairsMove = chairsMove;
        this.availableTimes = new ArrayList<AvailableTime>();
    }
    public Room() {
        this.roomNumber = 0;
        this.tv = 0;
        this.projector = 0;
        this.table = 0;
        this.chair = 0;
        this.outlet = 0;
        this.capacity = 0;
        this.minCapacity = 0;
        this.blackboard = 0;
        this.whiteboard = 0;
        this.tablesMove = false;
        this.chairsMove = false;
        this.availableTimes = new ArrayList<AvailableTime>();
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getTv() {
        return tv;
    }

    public void setTv(Integer tv) {
        this.tv = tv;
    }

    public Integer getProjector() {
        return projector;
    }

    public void setProjector(Integer projector) {
        this.projector = projector;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Integer getChair() {
        return chair;
    }

    public void setChair(Integer chair) {
        this.chair = chair;
    }

    public Integer getOutlet() {
        return outlet;
    }

    public void setOutlet(Integer outlet) {
        this.outlet = outlet;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(Integer minCapacity) {
        this.minCapacity = minCapacity;
    }

    public Integer getBlackboard() {
        return blackboard;
    }

    public void setBlackboard(Integer blackboard) {
        this.blackboard = blackboard;
    }

    public Integer getWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(Integer whiteboard) {
        this.whiteboard = whiteboard;
    }

    public Boolean getTablesMove() {
        return tablesMove;
    }

    public void setTablesMove(Boolean tablesMove) {
        this.tablesMove = tablesMove;
    }

    public Boolean getChairsMove() {
        return chairsMove;
    }

    public void setChairsMove(Boolean chairsMove) {
        this.chairsMove = chairsMove;
    }

    public List<AvailableTime> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<AvailableTime> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public void addAvailableTime(Date start, Date end) {
        this.availableTimes.add(new AvailableTime(start, end));
    }

    public void addAvailableTime(AvailableTime availTime) {
        this.availableTimes.add(availTime);
    }

    public void removeAvailableTime(AvailableTime availTime){
        this.availableTimes.remove(availTime);
    }

    public Boolean compare(Integer roomNumber, Integer minCapacity, Integer blackboard, Integer whiteboard, Boolean tablesMove,
                           Boolean chairsMove, Integer tv, Integer projector, Integer table, Integer chair, Integer outlet,
                           Integer capacity, AvailableTime availableTime){
        Boolean matchesCriteria = true;

        //if attribute is 0 or null dont check it, if value passed make sure that it equal or more then requested
        if (roomNumber != null && roomNumber != 0){if (roomNumber != this.roomNumber){return false;}}
        if (minCapacity != null && minCapacity != 0){if (minCapacity > this.minCapacity){return false;}}
        if (blackboard != null && blackboard != 0){if (blackboard > this.blackboard){return false;}}
        if (whiteboard != null && whiteboard != 0){if (whiteboard > this.whiteboard){return false;}}
        if (tablesMove != null){if (tablesMove != this.tablesMove){return false;}}
        if (chairsMove != null){if (chairsMove != this.chairsMove){return false;}}
        if (tv != null && tv != 0){if (tv > this.tv){return false;}}
        if (projector != null && projector != 0){if (projector > this.projector){return false;}}
        if (table != null && table != 0){if (table > this.table){return false;}}
        if (chair != null && chair != 0){if (chair > this.chair){return false;}}
        if (outlet != null && outlet != 0){if (outlet > this.outlet){return false;}}
        if (capacity != null && capacity != 0){if (capacity > this.capacity){return false;}}

        //check if there is an available time for this room
        Boolean avTime = false;
        for (int i = 0; i < this.getAvailableTimes().size(); i++) {
            if (availableTime.getStartDateTime() != null && availableTime.getEndDateTime() != null) {
                if (getAvailableTimes().get(i).getStartDateTime().before(availableTime.getStartDateTime()) &&
                        getAvailableTimes().get(i).getEndDateTime().after(availableTime.getEndDateTime())){
                    avTime = true;
                }

            } else if (availableTime.getStartDateTime() != null) {
                if (getAvailableTimes().get(i).getStartDateTime().before(availableTime.getStartDateTime())){
                    avTime = true;
                }

            } else if (availableTime.getEndDateTime() != null) {
                if (getAvailableTimes().get(i).getEndDateTime().after(availableTime.getEndDateTime())){
                    avTime = true;
                }
            }
            else {return matchesCriteria;}
        }
        return matchesCriteria && avTime;
    }
}
