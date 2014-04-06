package com.superultrameh.roombooking.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.data.BuildingRoomList;
import com.superultrameh.roombooking.model.AvailableTime;

/**
 * Worked on by Becky and Francis
 *
 * Fragment which displays the various search options for finding
 * a room based on the room parameters and the available times.
 *
 * Currently does not run the search, but only has the search parameters.
 *
 */
public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String buildingName;
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
    private AvailableTime startEndTime;

    private View rootView;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        String[] listYesNo=new String[2];
        listYesNo[1] = "Yes";
        listYesNo[0] = "No";

        String[] displayedValues;

        Spinner spinnerBuilding = (Spinner) rootView.findViewById(R.id.spinnerBuilding);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, BuildingRoomList.instance().getBuildingNames());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuilding.setAdapter(dataAdapter);
        spinnerBuilding.setOnItemSelectedListener(this);

        Spinner spinnerRoomNumber = (Spinner) rootView.findViewById(R.id.spinnerRoomNumber);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, BuildingRoomList.instance().getBuildingList().get(0).getRoomNumbers());
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomNumber.setAdapter(roomAdapter);

        NumberPicker npBlackboards = (NumberPicker) rootView.findViewById(R.id.numberPickerBlackboards);
        npBlackboards.setMaxValue(5);
        npBlackboards.setMinValue(0);

        NumberPicker npWhiteboards = (NumberPicker) rootView.findViewById(R.id.numberPickerWhiteboards);
        npWhiteboards.setMaxValue(5);
        npWhiteboards.setMinValue(0);

        displayedValues = new String[16];
        for(int i=0; i<16; i++) displayedValues[i] = String.valueOf(10 * i);
        NumberPicker npCapacity = (NumberPicker) rootView.findViewById(R.id.numberPickerCapacity);
        npCapacity.setMaxValue(displayedValues.length-1);
        npCapacity.setMinValue(0);
        npCapacity.setDisplayedValues(displayedValues);

        NumberPicker npOutlets = (NumberPicker) rootView.findViewById(R.id.numberPickerOutlet);
        npOutlets.setMaxValue(20);
        npOutlets.setMinValue(0);

        displayedValues = new String[21];
        for(int i=0; i<21; i++) displayedValues[i] = String.valueOf(5 * i);
        NumberPicker npChair = (NumberPicker) rootView.findViewById(R.id.numberPickerChair);
        npChair.setMaxValue(displayedValues.length-1);
        npChair.setMinValue(0);
        npChair.setDisplayedValues(displayedValues);

        displayedValues = new String[5];
        for(int i=0; i<5; i++) displayedValues[i] = String.valueOf(5 * i);
        NumberPicker npTable = (NumberPicker) rootView.findViewById(R.id.numberPickerTable);
        npTable.setMaxValue(displayedValues.length-1);
        npTable.setMinValue(0);
        npTable.setDisplayedValues(displayedValues);

        final NumberPicker npMoveChair = (NumberPicker) rootView.findViewById(R.id.numberPickerChairsMove);
        npMoveChair.setMaxValue(listYesNo.length-1);
        npMoveChair.setMinValue(0);
        npMoveChair.setDisplayedValues(listYesNo);
        npMoveChair.setWrapSelectorWheel(false);

        final NumberPicker npMoveTable = (NumberPicker) rootView.findViewById(R.id.numberPickerTablesMove);
        npMoveTable.setMaxValue(listYesNo.length-1);
        npMoveTable.setMinValue(0);
        npMoveTable.setDisplayedValues(listYesNo);
        npMoveTable.setWrapSelectorWheel(false);

        NumberPicker npProjector = (NumberPicker) rootView.findViewById(R.id.numberPickerProjector);
        npProjector.setMaxValue(5);
        npProjector.setMinValue(0);

        NumberPicker npTV = (NumberPicker) rootView.findViewById(R.id.numberPickerTV);
        npTV.setMaxValue(5);
        npTV.setMinValue(0);

        DatePicker dpStart = (DatePicker) rootView.findViewById(R.id.datePicker);
        dpStart.setCalendarViewShown(false);

        DatePicker dpEnd = (DatePicker) rootView.findViewById(R.id.datePicker2);
        dpEnd.setCalendarViewShown(false);


        Button buttonSearch = (Button) rootView.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
              /*  buildingName = npBuilding.getDisplayedValues()[npBuilding.getValue()];
                roomNumber = Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                roomNumber = Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                minCapacity = Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                blackboard = Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                whiteboard = Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                tablesMove= (npMoveTable.getValue() == 1) ? true : false;
                chairsMove= (npMoveChair.getValue() == 1) ? true : false;
                tv= Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                projector= Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                table= Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                chair= Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                outlet= Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                capacity= Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
             //   startEndTime= Integer.getInteger(npRoomNumber.getDisplayedValues()[npRoomNumber.getValue()]);
                List<Building> list = BuildingRoomList.instance().findAll(buildingName, roomNumber, minCapacity,
                        blackboard, whiteboard, tablesMove, chairsMove, tv, projector, table, chair, outlet, capacity, startEndTime);
              //  BookingFromScheduleDialog dialog = new BookingFromScheduleDialog(SearchFragment.this.getActivity());
               // dialog.show();*/
            }
        });
        return rootView;
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner spinnerRoomNumber = (Spinner) rootView.findViewById(R.id.spinnerRoomNumber);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, BuildingRoomList.instance().getBuildingList().get(pos).getRoomNumbers());
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomNumber.setAdapter(roomAdapter);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
