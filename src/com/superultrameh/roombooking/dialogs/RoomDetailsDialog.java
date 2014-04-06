package com.superultrameh.roombooking.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.data.BuildingRoomList;
import com.superultrameh.roombooking.model.Room;

/**
 * Worked on by Becky and Francis
 *
 * Displays the properties of one specific room. Takes a building ID (position in the ArrayList)
 * and room ID (position in the ArrayList of rooms in the building) to get the relevant room, then
 * displays the information using the room_details_dialog layout.
 *
 */

public class RoomDetailsDialog extends Dialog {

    private int buildingId, roomId;

	public RoomDetailsDialog(Context context, int buildingId, int roomId) {
		super(context);

        this.buildingId = buildingId;
        this.roomId = roomId;

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.room_details_dialog);

        Room room = BuildingRoomList.instance().getBuildingList().get(buildingId).getRooms().get(roomId);

        String buildingName = BuildingRoomList.instance().getBuildingList().get(buildingId).getName();
        Integer roomNumber = BuildingRoomList.instance().getBuildingList().get(buildingId).getRooms().get(roomId).getRoomNumber();

        TextView roomName = (TextView) findViewById(R.id.roomName);
        TextView capacity = (TextView) findViewById(R.id.roomCapacity);
        TextView chairs = (TextView) findViewById(R.id.roomChairs);
        TextView tables = (TextView) findViewById(R.id.roomTables);
        TextView projectors = (TextView) findViewById(R.id.roomProjectors);
        TextView tvs = (TextView) findViewById(R.id.roomTvs);
        TextView outlets = (TextView) findViewById(R.id.roomOutlets);
        TextView chairsmove = (TextView) findViewById(R.id.roomChairsMove);
        TextView tablesmove = (TextView) findViewById(R.id.roomTablesMove);
        TextView blackboards = (TextView) findViewById(R.id.roomBlackboard);
        TextView whiteboards = (TextView) findViewById(R.id.roomWhiteboard);


        roomName.setText(buildingName + " - " + roomNumber);
        capacity.setText("" + room.getCapacity());
        chairs.setText((room.getChair().equals(0)) ? "" + room.getChair() : "None");
        tables.setText(room.getTable() != 0 ? "" + room.getTable() : "None");
        projectors.setText(room.getProjector() != 0 ? "" + room.getProjector() : "None");
        tvs.setText(room.getTv() != 0 ? "" + room.getTv() : "None");
        outlets.setText(room.getOutlet() != 0 ? "" + room.getOutlet() : "None");
        chairsmove.setText((room.getChairsMove()) ? "Yes" : "No");
        tablesmove.setText((room.getTablesMove()) ? "Yes" : "No");
        blackboards.setText(room.getBlackboard() != 0 ? "" + room.getBlackboard() : "None");
        whiteboards.setText(room.getWhiteboard() != 0 ? "" + room.getWhiteboard() : "None");
	}
}
