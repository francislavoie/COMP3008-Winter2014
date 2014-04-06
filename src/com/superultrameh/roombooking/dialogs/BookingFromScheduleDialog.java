package com.superultrameh.roombooking.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TimePicker;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.model.AvailableTime;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Worked on by Becky and Francis
 *
 * This dialog allows for picking a time range within the available time range.
 * For example, an available time is between 2:30pm and 7:30pm, the user could pick to book
 * from 2:30pm to 5:00pm, or 4:00pm to 6:00pm, etc. If the user attempts to choose a time outside
 * of the allowed range defined by the given AvailableTime object, it will reset back to the
 * limit. The start of the AvailableTime becomes the minimum and the end becomes the maximum.
 */

public class BookingFromScheduleDialog extends Dialog {

    private AvailableTime time;

	public BookingFromScheduleDialog(Context context, AvailableTime time) {
		super(context);

        this.time = time;

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.booking_dialog);
        if(time != null) {
            TimePicker startPicker = (TimePicker) findViewById(R.id.timePicker);
            TimePicker endPicker = (TimePicker) findViewById(R.id.timePicker2);

            Calendar start = GregorianCalendar.getInstance(), end = GregorianCalendar.getInstance();
            start.setTime(time.getStartDateTime());
            end.setTime(time.getEndDateTime());

            startPicker.setCurrentHour(start.get(Calendar.HOUR_OF_DAY));
            startPicker.setCurrentMinute(start.get(Calendar.MINUTE));

            endPicker.setCurrentHour(end.get(Calendar.HOUR_OF_DAY));
            endPicker.setCurrentMinute(end.get(Calendar.MINUTE));

            startPicker.setOnTimeChangedListener(TimeChangedListener);
            endPicker.setOnTimeChangedListener(TimeChangedListener);
        }
	}

    private TimePicker.OnTimeChangedListener TimeChangedListener =
        new TimePicker.OnTimeChangedListener() {

        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            if(time != null) {updateDisplay(view, hourOfDay, minute); }
        }
    };

    private TimePicker.OnTimeChangedListener NullTimeChangedListener =
        new TimePicker.OnTimeChangedListener() {

        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

        }
    };

    private void updateDisplay(TimePicker timePicker, int hourOfDay, int minute) {

        int adjustedMinute = minute, adjustedHour = hourOfDay;

        Calendar start = GregorianCalendar.getInstance(), end = GregorianCalendar.getInstance();
        start.setTime(time.getStartDateTime());
        end.setTime(time.getEndDateTime());
        if(end.get(Calendar.HOUR_OF_DAY) <= hourOfDay) {
            adjustedHour = end.get(Calendar.HOUR_OF_DAY);
            if(end.get(Calendar.MINUTE) < minute) {
                adjustedMinute = end.get(Calendar.MINUTE);
            }
        }

        if(start.get(Calendar.HOUR_OF_DAY) >= hourOfDay) {
            adjustedHour = start.get(Calendar.HOUR_OF_DAY);
            if(start.get(Calendar.MINUTE) > minute) {
                adjustedMinute = start.get(Calendar.MINUTE);
            }
        }

        // Prevent infinite loop of events by removing the listener before adjusting
        timePicker.setOnTimeChangedListener(NullTimeChangedListener);
        timePicker.setCurrentMinute(adjustedMinute);
        timePicker.setCurrentHour(adjustedHour);
        timePicker.setOnTimeChangedListener(TimeChangedListener);

    }
}
