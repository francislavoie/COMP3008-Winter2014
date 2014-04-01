package com.superultrameh.roombooking.data;

import com.superultrameh.roombooking.model.AvailableTime;
import com.superultrameh.roombooking.model.Building;
import com.superultrameh.roombooking.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by becky on 4/1/2014.
 */
public class BuildingRoomList {
    private List<Building> buildingList;
    private List<String> buildingNames;

    public BuildingRoomList(){
        buildingNames.add("Architecture Building (AA)");
        buildingNames.add("Azrieli Pavillion and Theatre (AT)");
        buildingNames.add("Carleton Technology and Training Centre (CTTC)");
        buildingNames.add("Dunton Tower (DT)");
        buildingNames.add("Herzberg Laboratories (HP)");
        buildingNames.add("Life Sciences Research Building (LS)");
        buildingNames.add("Loeb Building (LA)");
        buildingNames.add("Mackenzie Building (ME)");
        buildingNames.add("MacOdrum Library (ML)");
        buildingNames.add("Minto Centre (MC)");
        buildingNames.add("Social Sciences Research Building (SR)");
        buildingNames.add("Southam Hall (SA)");
        buildingNames.add("St. Patrick’s Building (SP)");
        buildingNames.add("Steacie Building (SC)");
        buildingNames.add("Tory Building (TB)");
        buildingNames.add("University Centre (UC)");

        //for each building generate 10 random rooms w 5 available dates
        for (int b = 0; b< buildingNames.size(); b++) {
            List<Room> rooms = new ArrayList<Room>();
            for (int i = 0; i < 10; i++) {
                Room r = new Room(ranNum(100, 600), ranNum(0,1), ranNum(0,1), ranNum(2,8), ranNum(10, 100),
                        ranNum(0, 15), ranNum(20, 150), ranNum(3, 10), ranNum(0, 4), ranNum(0, 3), 1==ranNum(0,1), 1==ranNum(0,1));

                //generate 5 random available dates
                //range between 800 to 2400 hours and between april 1 - 15
                for (int d = 0; d < 5; d++){
                    AvailableTime avTime = new AvailableTime();
                    Integer startTimeMin = ranNum(0,3)*15;
                    Integer startTimeHour = ranNum(8, 21);
                    Integer endTimeHour = ranNum(10,23);
                    Integer endTimeMin = ranNum(0,3)*15;
                    Integer day = ranNum(1, 15);
                    avTime.setStartTime(2014, 04, day, startTimeHour, startTimeMin);
                    avTime.setEndTime(2014, 04, day, endTimeHour, endTimeMin);
                    r.addAvailableTime(avTime);
                }
            }
            buildingList.add(new Building(buildingNames.get(b),rooms ));
        }

    }
    public BuildingRoomList(List<Building> buildingList, List<String> buildingNames) {
        this.buildingList = buildingList;
        this.buildingNames = buildingNames;
    }

    public Integer ranNum(int Min, int Max){
        return Min + (int)(Math.random() * ((Max - Min) + 1));
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<String> getBuildingNames() {
        return buildingNames;
    }

    public void setBuildingNames(List<String> buildingNames) {
        this.buildingNames = buildingNames;
    }

//set to null or 0 if variable doesn't matter
    public List<Building> findAll(String building, Integer roomNumber, Integer minCapacity, Integer blackboard, Integer whiteboard, Boolean tablesMove,
                                  Boolean chairsMove, Integer tv, Integer projector, Integer table, Integer chair, Integer outlet,
                                  Integer capacity, AvailableTime availableTime){
        List<Building> match = new ArrayList<Building>();
        for(int b = 0; b < buildingList.size(); b++){
            List<Room> rooms = buildingList.get(b).getRooms();
            List<Room> matchRooms = new ArrayList<Room>();
            for(int r = 0; r < rooms.size(); r++){
                if (rooms.get(r).compare(roomNumber, minCapacity, blackboard, whiteboard, tablesMove, chairsMove,
                        tv, projector, table, chair, outlet, capacity, availableTime)){
                    matchRooms.add(rooms.get(r));}
            }
            if (matchRooms.size()!= 0){
                match.add(new Building(buildingList.get(b).getName(), matchRooms));
            }
        }
        return match;
    }
}
