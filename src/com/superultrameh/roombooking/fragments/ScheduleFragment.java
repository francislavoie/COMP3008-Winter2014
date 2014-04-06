package com.superultrameh.roombooking.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.data.BuildingRoomList;
import com.superultrameh.roombooking.dialogs.BookingFromScheduleDialog;
import com.superultrameh.roombooking.model.AvailableTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Worked on by Becky and Francis
 *
 * Displays the AvailableTimes for a Room. Time blocks are added dynamically to the layout
 * using the relevant AvailableTime objects. Includes a Building and Room spinner to choose
 * which room to display. Clicking on an AvailableTime block will allow the user to book room.
 * See BookingFromScheduleDialog.
 *
 * The size of each block and the position is done by calculating the dp (density-independent pixel)
 * based on the duration and start time of an AvailableTime object. 60dp = 60 minutes or 1 hour.
 * The dayArray list holds each of the columns defining a day.
 *
 * Currently only displays a static week because implementing paging between weeks was conflicting
 * with our Tabs navigation (swiping left/right) and the idea of swiping to go to the next or prev
 * week. Could be implemented just using next/prev week buttons instead.
 */
public class ScheduleFragment extends Fragment {

    private View rootView;
    private RelativeLayout[] dayArray;
    private TextView[] dayHeaders;
    private Button[] dayButtons;

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
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

        Spinner spinnerBuilding = (Spinner) rootView.findViewById(R.id.spinnerBuilding);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, BuildingRoomList.instance().getBuildingNames());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuilding.setAdapter(dataAdapter);
        spinnerBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinnerRoomNumber = (Spinner) rootView.findViewById(R.id.spinnerRoomNumber);
                ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, BuildingRoomList.instance().getBuildingList().get(position).getRoomNumbers());
                roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRoomNumber.setAdapter(roomAdapter);

                Time t = new Time();
                t.setToNow();
                Log.d(null, "BOOKING BUILDING CHANGED: " + t.format("%H:%M:%S") + ", BLD: " + BuildingRoomList.instance().getBuildingList().get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerRoomNumber = (Spinner) rootView.findViewById(R.id.spinnerRoomNumber);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, BuildingRoomList.instance().getBuildingList().get(0).getRoomNumbers());
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomNumber.setAdapter(roomAdapter);
        spinnerRoomNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BuildingRoomList roomlist = BuildingRoomList.instance();

                Spinner spinnerBuilding = (Spinner) rootView.findViewById(R.id.spinnerBuilding);
                int buildingID = spinnerBuilding.getSelectedItemPosition();

                clearBookings();

                int min = 1000000;

                for (AvailableTime booking : roomlist.getBuildingList().get(buildingID).getRooms().get(position).getAvailableTimes()) {
                    Calendar startTime = new GregorianCalendar(), endTime = new GregorianCalendar();
                    startTime.setTime(booking.getStartDateTime());
                    endTime.setTime(booking.getEndDateTime());

                    int dayOfWeek = startTime.get(Calendar.DAY_OF_WEEK) - 1;
                    int start, duration;

                    start = time(startTime.get(Calendar.HOUR_OF_DAY), startTime.get(Calendar.MINUTE));
                    duration = minutesDiff(startTime.getTime(), endTime.getTime());
                    min = Math.min(min, makeBooking(dayOfWeek, start / 2, duration / 2, booking));
                }

                Time t = new Time();
                t.setToNow();
                Log.d(null, "BOOKING ROOM CHANGED: " + t.format("%H:%M:%S") + ", ROOM: " + BuildingRoomList.instance().getBuildingList().get(buildingID).getRooms().get(position).getRoomNumber());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        v.findViewById(R.id.bookingBorder).setBackgroundColor(0xFF000000);
        v.findViewById(R.id.bookingBody).setBackgroundColor(0xFFAA2020);

        // set button listener
        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingFromScheduleDialog dialog = new BookingFromScheduleDialog(getActivity(), booking);
                dialog.show();


                Calendar startTime = new GregorianCalendar(), endTime = new GregorianCalendar();
                startTime.setTime(booking.getStartDateTime());
                endTime.setTime(booking.getEndDateTime());

                Time t = new Time();
                t.setToNow();
                Log.d(null, "BOOKING DIALOG OPENED: " + t.format("%H:%M:%S") + ", AVAILABLETIME: Day " + startTime.get(Calendar.DAY_OF_MONTH) + ", Hour: " + startTime.get(Calendar.HOUR));
            }
        });

        // add shift view
        dayArray[day].addView(v);

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
}
