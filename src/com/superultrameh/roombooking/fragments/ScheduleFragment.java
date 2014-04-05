package com.superultrameh.roombooking.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.data.BuildingRoomList;
import com.superultrameh.roombooking.dialogs.BookingFromScheduleDialog;
import com.superultrameh.roombooking.model.AvailableTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;
    private RelativeLayout[] dayArray;
    private TextView[] dayHeaders;
    private Button[] dayButtons;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ScheduleFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);


        init();

        return rootView;
    }

    public void init() {
        dayArray = new RelativeLayout[7];
        for (int i = 0; i < 7; i++) {
            dayArray[i] = (RelativeLayout) rootView
                    .findViewById(getResources().getIdentifier("dayLayout" + i,
                            "id", "com.superultrameh.roombooking"));
        }
        dayHeaders = new TextView[7];
        for (int i = 0; i < 7; i++) {
            dayHeaders[i] = (TextView) rootView.findViewById(getResources()
                    .getIdentifier("textDayName" + i, "id",
                            "com.superultrameh.roombooking"));
        }
        dayButtons = new Button[7];
        for (int i = 0; i < 7; i++) {
            dayButtons[i] = (Button) rootView.findViewById(getResources()
                    .getIdentifier("buttonDayName" + i, "id",
                            "com.superultrameh.roombooking"));
        }

        BuildingRoomList roomlist = BuildingRoomList.instance();

        clearBookings();

        int min = 1000000;

        for (AvailableTime booking : roomlist.getBuildingList().get(0).getRooms().get(0).getAvailableTimes()) {
            Calendar startTime = new GregorianCalendar(), endTime = new GregorianCalendar();
            startTime.setTime(booking.getStartDateTime());
            endTime.setTime(booking.getEndDateTime());

            int dayOfWeek = startTime.get(Calendar.DAY_OF_WEEK) - 1;
            int start, duration;

            start = time(startTime.get(Calendar.HOUR_OF_DAY), startTime.get(Calendar.MINUTE));
            duration = minutesDiff(startTime.getTime(), endTime.getTime());
            min = Math.min(min, makeBooking(dayOfWeek, start / 2, duration / 2, booking));
        }

        rootView.findViewById(R.id.scrollView).post(new Runnable() {
            public void run() {
                rootView.findViewById(R.id.scrollView).scrollTo(0, 540);
            }
        });
    }

    private int makeBooking(int day, int startTime, int duration, final AvailableTime booking) {

        LayoutInflater vi = (LayoutInflater) getActivity()
                .getApplicationContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.booking_item, null);

        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                duration, getResources().getDisplayMetrics());
        int offset = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, startTime, getResources()
                        .getDisplayMetrics());
        int sidemargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, getResources()
                        .getDisplayMetrics());

        RelativeLayout newShift = (RelativeLayout) v.findViewById(R.id.booking_item);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, size);
        params.setMargins(sidemargin, offset, sidemargin, 0);
        newShift.setLayoutParams(params);

        // set the border and body color of the shift item
//        v.findViewById(R.id.shiftBorder).setBackgroundColor(
//                booking.getState().getOwnBorderPaint().getColor());
//        v.findViewById(R.id.shiftBody).setBackgroundColor(
//                booking.getState().getOwnBackgroundPaint().getColor());

        v.findViewById(R.id.shiftBorder).setBackgroundColor(0xFF000000);
        v.findViewById(R.id.shiftBody).setBackgroundColor(0xFFAA2020);

        // set button listener
        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingFromScheduleDialog dialog = new BookingFromScheduleDialog(getActivity(), booking);
                dialog.show();
            }
        });

        // add shift view
        ((ViewGroup) dayArray[day]).addView(v);

        return offset;
    }

    private int time(int hour, int minute) {
        return hour * 60 + minute;
    }

    private static int minutesDiff(Date earlierDate, Date laterDate) {
        if (earlierDate == null || laterDate == null)
            return 0;
        return (int) ((laterDate.getTime() / 60000) - (earlierDate.getTime() / 60000));
    }

    private void clearBookings() {
        for (int i = 0; i < 7; i++)
            if (dayArray[i] != null)
                dayArray[i].removeAllViews();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
