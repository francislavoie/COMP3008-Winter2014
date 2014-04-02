package com.superultrameh.roombooking.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.superultrameh.roombooking.R;

/**
 * Created by becky on 4/1/2014.
 */
public class SearchFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

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
        NumberPicker npBuilding = (NumberPicker) rootView.findViewById(R.id.numberPickerBuilding);
        npBuilding.setMaxValue(9);
        npBuilding.setMinValue(0);
        npBuilding.setWrapSelectorWheel(false);

        NumberPicker npRoomNumber = (NumberPicker) rootView.findViewById(R.id.numberPickerRoomNumber);
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

        NumberPicker npMoveChair = (NumberPicker) rootView.findViewById(R.id.numberPickerChairsMove);
        npMoveChair.setMaxValue(listYesNo.length-1);
        npMoveChair.setMinValue(0);
        npMoveChair.setDisplayedValues(listYesNo);
        npMoveChair.setWrapSelectorWheel(false);

        NumberPicker npMoveTable = (NumberPicker) rootView.findViewById(R.id.numberPickerTablesMove);
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
        return rootView;


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
