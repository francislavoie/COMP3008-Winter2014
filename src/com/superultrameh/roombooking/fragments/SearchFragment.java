package com.superultrameh.roombooking.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.ScrollView;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.data.BuildingRoomList;
import com.superultrameh.roombooking.dialogs.StaffDetailDialog;
import com.superultrameh.roombooking.model.AvailableTime;
import com.superultrameh.roombooking.model.Building;
import com.superultrameh.roombooking.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by becky on 4/1/2014.
 */
public class SearchFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

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

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        String[] listYesNo=new String[2];
        listYesNo[1] = "Yes";
        listYesNo[0] = "No";

        final NumberPicker npBuilding = (NumberPicker) rootView.findViewById(R.id.numberPickerBuilding);
        npBuilding.setMaxValue(9);
        npBuilding.setMinValue(0);
        npBuilding.setWrapSelectorWheel(false);

        final NumberPicker npRoomNumber = (NumberPicker) rootView.findViewById(R.id.numberPickerRoomNumber);
        npRoomNumber.setMaxValue(9);
        npRoomNumber.setMinValue(0);
        npRoomNumber.setWrapSelectorWheel(false);

        NumberPicker npBlackboards = (NumberPicker) rootView.findViewById(R.id.numberPickerBlackboards);
        npBlackboards.setMaxValue(9);
        npBlackboards.setMinValue(0);
        npBlackboards.setWrapSelectorWheel(false);

        NumberPicker npWhiteboards = (NumberPicker) rootView.findViewById(R.id.numberPickerWhiteboards);
        npWhiteboards.setMaxValue(9);
        npWhiteboards.setMinValue(0);
        npWhiteboards.setWrapSelectorWheel(false);

        NumberPicker npCapacity = (NumberPicker) rootView.findViewById(R.id.numberPickerCapacity);
        npCapacity.setMaxValue(9);
        npCapacity.setMinValue(0);
        npCapacity.setWrapSelectorWheel(false);

        NumberPicker npOutlets = (NumberPicker) rootView.findViewById(R.id.numberPickerOutlet);
        npOutlets.setMaxValue(9);
        npOutlets.setMinValue(0);
        npOutlets.setWrapSelectorWheel(false);

        NumberPicker npChair = (NumberPicker) rootView.findViewById(R.id.numberPickerChair);
        npChair.setMaxValue(9);
        npChair.setMinValue(0);
        npChair.setWrapSelectorWheel(false);

        NumberPicker npTable = (NumberPicker) rootView.findViewById(R.id.numberPickerTable);
        npTable.setMaxValue(2);
        npTable.setMinValue(0);
        npTable.setWrapSelectorWheel(false);

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
        npProjector.setMaxValue(9);
        npProjector.setMinValue(0);
        npProjector.setWrapSelectorWheel(false);

        NumberPicker npTV = (NumberPicker) rootView.findViewById(R.id.numberPickerTV);
        npTV.setMaxValue(9);
        npTV.setMinValue(0);
        npTV.setWrapSelectorWheel(false);

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
              //  StaffDetailDialog dialog = new StaffDetailDialog(SearchFragment.this.getActivity());
               // dialog.show();*/
            }
        });
        return rootView;


    }
{


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

}
