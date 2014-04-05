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

    private TimePicker.OnTimeChangedListener TimeChangedListener =
        new TimePicker.OnTimeChangedListener() {

        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            updateDisplay(view, hourOfDay, minute);
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

        timePicker.setOnTimeChangedListener(NullTimeChangedListener);
        timePicker.setCurrentMinute(adjustedMinute);
        timePicker.setCurrentHour(adjustedHour);
        timePicker.setOnTimeChangedListener(TimeChangedListener);

    }
}
